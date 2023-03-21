package com.example.fxgraphcheck.cell;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Cell extends Pane {

    String cellId;
    List<Cell> children = new ArrayList<>();
    List<Cell> parents = new ArrayList<>();
    Node view;
    BooleanProperty active = new SimpleBooleanProperty(false);

    private final Rectangle activeIndicator;
    private final int CELL_SIZE = 25;

    public Cell(String cellId) {
        this.cellId = cellId;
        activeIndicator = new Rectangle(CELL_SIZE, CELL_SIZE);
        this.registerEventHandlers();
    }

    public void setPosition(double x, double y) {
    }

  /*  public void setX(double x) {
        this.setLayoutX(x);
    }

    public void setY(double y) {
        this.setLayoutY(y);
    }

    public void setPosition(double x, double y) {
        this.setX(x);
        this.setY(y);
    }*/

    public int getCellSize() {
        return this.CELL_SIZE;
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public void addCellChild(Cell cell) {
        children.add(cell);
    }

    public List<Cell> getCellChildren() {
        return children;
    }

    public void addCellParent(Cell cell) {
        parents.add(cell);
    }

    public List<Cell> getCellParents() {
        return parents;
    }

    public void removeCellChild(Cell cell) {
        children.remove(cell);
    }

    public void setView(Node view) {
        this.view = view;
        getChildren().add(view);
    }

    public Node getView() {
        return this.view;
    }

    public String getCellId() {
        return cellId;
    }


    private void registerEventHandlers() {

        this.active.addListener(event -> {
            if (isActive()) {
                this.activeIndicator.setFill(Color.RED);
                getChildren().add(this.activeIndicator);
            } else {
                this.activeIndicator.setFill(Color.TRANSPARENT);
                getChildren().remove(this.activeIndicator);
            }
        });

        this.setOnMouseClicked(event -> {
                    if (event.getButton().equals(MouseButton.PRIMARY)) {
                        if (event.getClickCount() == 2) {
                            this.setActive(!this.isActive());
                        }
                    }
                }
        );
    }


}