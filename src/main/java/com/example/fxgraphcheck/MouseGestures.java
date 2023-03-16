package com.example.fxgraphcheck;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class MouseGestures {

    final DragContext dragContext = new DragContext();

    Graph graph;

    public MouseGestures(Graph graph) {
        this.graph = graph;
    }

    public void makeDraggable(final Node node) {

        node.setOnMousePressed(onMousePressedEventHandler);
        node.setOnMouseDragged(onMouseDraggedEventHandler);
        node.setOnMouseReleased(onMouseReleasedEventHandler);

    }

    EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            Node node = (Node) event.getSource();

            double scale = graph.getScale();

            dragContext.x = node.getBoundsInParent().getMinX() * scale - event.getScreenX();
            dragContext.y = node.getBoundsInParent().getMinY() * scale - event.getScreenY();

        }
    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            Node node = (Node) event.getSource();

            double offsetX = event.getScreenX() + dragContext.x;
            double offsetY = event.getScreenY() + dragContext.y;

            // adjust the offset in case we are zoomed
            double scale = graph.getScale();

            offsetX /= scale;
            offsetY /= scale;

            node.relocate(offsetX, offsetY);

        }
    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> {

       /* int snapSize = 25;

        double x = event.getX();
        double y = event.getY();

        double snapX = Math.round(x / snapSize) * snapSize;
        double snapY = Math.round(y / snapSize) * snapSize;

        System.out.println(event.getSource());
        System.out.println(event);
        System.out.println(snapX + " " + snapY);

        Cell cell = (Cell) event.getSource();


        List<Edge> edges = graph.getModel().getEdgesForCell(cell);
        for (Edge e : edges) {
            if (e.source.equals(cell)) {
                e.line.startXProperty().setValue(snapX);
                e.line.startYProperty().setValue(snapY);
            }
            if (e.target.equals(cell)) {
                e.line.endXProperty().setValue(snapX);
                e.line.endYProperty().setValue(snapY);
            }
        }
        cell.setPosition(snapX, snapY);*/

    };


    class DragContext {
        double x;
        double y;

    }
}
