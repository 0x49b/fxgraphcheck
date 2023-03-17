package com.example.fxgraphcheck.model;

import com.example.fxgraphcheck.cell.*;
import com.example.fxgraphcheck.edge.Edge;
import com.example.fxgraphcheck.station.Station;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Model {

    Cell graphParent;

    List<Cell> allCells;
    List<Cell> addedCells;
    List<Cell> removedCells;

    List<Edge> allEdges;
    List<Edge> addedEdges;
    List<Edge> removedEdges;

    List<Station> allStations;
    List<Station> addedStations;
    List<Station> removedStations;


    Map<String, Cell> cellMap; // <id,cell>

    public Model() {

        graphParent = new Cell("_ROOT_");

        // clear model, create lists
        clear();
    }

    public void clear() {

        allCells = new ArrayList<>();
        addedCells = new ArrayList<>();
        removedCells = new ArrayList<>();

        allEdges = new ArrayList<>();
        addedEdges = new ArrayList<>();
        removedEdges = new ArrayList<>();

        allStations = new ArrayList<>();
        addedStations = new ArrayList<>();
        removedStations = new ArrayList<>();


        cellMap = new HashMap<>(); // <id,cell>

    }

    public void clearAddedLists() {
        addedCells.clear();
        addedEdges.clear();
        addedStations.clear();
    }

    public List<Cell> getAddedCells() {
        return addedCells;
    }

    public List<Cell> getRemovedCells() {
        return removedCells;
    }

    public List<Cell> getAllCells() {
        return allCells;
    }

    public List<Edge> getAddedEdges() {
        return addedEdges;
    }

    public List<Edge> getRemovedEdges() {
        return removedEdges;
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }

    public List<Station> getAllStations() {
        return allStations;
    }

    public List<Station> getAddedStations() {
        return addedStations;
    }

    public List<Station> getRemovedStations() {
        return removedStations;
    }

    public List<Edge> getEdgesForCell(Cell cell) {
        return this.allEdges.stream().filter(edge -> edge.getSource().equals(cell) || edge.getTarget().equals(cell)).collect(Collectors.toList());
    }

    public void removeEdge(Edge edge) {
        allEdges.remove(edge);
        removedEdges.add(edge);
    }

    public void removeCell(Cell cell) {
        allCells.remove(cell);
        removedCells.add(cell);
    }

    public void removeStation(Station station) {
        allStations.remove(station);
        removedStations.add(station);
    }

    public void addCell(String id, CellType type) {

        switch (type) {
            case RECTANGLE -> {
                RectangleCell rectangleCell = new RectangleCell(id);
                addCell(rectangleCell);
            }
            case TRIANGLE -> {
                TriangleCell triangleCell = new TriangleCell(id);
                addCell(triangleCell);
            }
            case ZS -> {
                ZSCell zsCell = new ZSCell(id);
                addCell(zsCell);
            }
            default -> throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }

    private void addCell(Cell cell) {
        addedCells.add(cell);
        cellMap.put(cell.getCellId(), cell);
    }

    public void addStation(Station station){
        addedStations.add(station);
    }

    public void addEdge(String sourceId, String targetId) {
        this.addEdge(sourceId, targetId, Color.BLACK);
    }

    public void addEdge(String sourceId, String targetId, Color color) {
        Cell sourceCell = cellMap.get(sourceId);
        Cell targetCell = cellMap.get(targetId);
        Edge edge = new Edge(sourceCell, targetCell, color);
        addedEdges.add(edge);
    }

    /**
     * Attach all cells which don't have a parent to graphParent
     *
     * @param cellList A list of {@link Cell}
     */
    public void attachOrphansToGraphParent(List<Cell> cellList) {

        for (Cell cell : cellList) {
            if (cell.getCellParents().size() == 0) {
                graphParent.addCellChild(cell);
            }
        }

    }

    /**
     * Remove the graphParent reference if it is set
     *
     * @param cellList a list of {@link Cell}
     */
    public void disconnectFromGraphParent(List<Cell> cellList) {

        for (Cell cell : cellList) {
            graphParent.removeCellChild(cell);
        }
    }


    public void merge() {

        // cells
        allCells.addAll(addedCells);
        allCells.removeAll(removedCells);

        addedCells.clear();
        removedCells.clear();

        // edges
        allEdges.addAll(addedEdges);
        allEdges.removeAll(removedEdges);

        addedEdges.clear();
        removedEdges.clear();

        // stations
        allStations.addAll(addedStations);
        allCells.removeAll(removedCells);

        addedStations.clear();
        removedStations.clear();
    }

}