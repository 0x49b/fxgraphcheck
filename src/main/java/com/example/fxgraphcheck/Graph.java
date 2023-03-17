package com.example.fxgraphcheck;

import com.example.fxgraphcheck.cell.Cell;
import com.example.fxgraphcheck.cell.CellLayer;
import com.example.fxgraphcheck.edge.Edge;
import com.example.fxgraphcheck.model.Model;
import com.example.fxgraphcheck.station.Station;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class Graph {

    private final Model model;
    private Group canvas;
    private final ZoomableScrollPane scrollPane;
    MouseGestures mouseGestures;

    /**
     * the pane wrapper is necessary or else the scrollpane would always align
     * the top-most and left-most child to the top and left eg when you drag the
     * top child down, the entire scrollpane would move down
     */
    CellLayer cellLayer;

    ContextMenu contextMenu;
    MenuItem delete;

    public Graph() {

        this.setupContextMenu();
        this.registerActionHandlers();

        this.model = new Model();

        canvas = new Group();
        cellLayer = new CellLayer();

        canvas.getChildren().add(cellLayer);

        mouseGestures = new MouseGestures(this);

        scrollPane = new ZoomableScrollPane(canvas);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public Pane getCellLayer() {
        return this.cellLayer;
    }

    public Model getModel() {
        return model;
    }

    private void setupContextMenu() {
        contextMenu = new ContextMenu();
        delete = new MenuItem("delete");
        contextMenu.getItems().add(delete);
    }

    private void registerActionHandlers() {
        delete.setOnAction(event -> {
            List<Edge> edgesForCell = model.getEdgesForCell((Cell) contextMenu.getOwnerNode());
            for (Edge e : edgesForCell) {
                model.removeEdge(e);
            }
            model.removeCell((Cell) contextMenu.getOwnerNode());
        });
    }

    public void beginUpdate() {
    }

    public void endUpdate() {

        // add components to graph pane
        getCellLayer().getChildren().addAll(model.getAddedEdges());
        getCellLayer().getChildren().addAll(model.getAddedCells());
        getCellLayer().getChildren().addAll(model.getAddedStations());

        // remove components from graph pane
        getCellLayer().getChildren().removeAll(model.getRemovedCells());
        getCellLayer().getChildren().removeAll(model.getRemovedEdges());
        getCellLayer().getChildren().removeAll(model.getRemovedStations());

        // enable dragging of cells
        for (Cell cell : model.getAddedCells()) {
            mouseGestures.makeDraggable(cell);

            cell.setOnContextMenuRequested(event -> {
                contextMenu.show(cell, event.getScreenX(), event.getScreenY());
            });
        }

        for(Station station: model.getAllStations()){
            mouseGestures.makeDraggable(station);
        }

        // every cell must have a parent, if it doesn't, then the graphParent is
        // the parent
        getModel().attachOrphansToGraphParent(model.getAddedCells());

        // remove reference to graphParent
        getModel().disconnectFromGraphParent(model.getRemovedCells());



        // merge added & removed cells with all cells
        getModel().merge();

    }

    public double getScale() {
        return this.scrollPane.getScaleValue();
    }
}
