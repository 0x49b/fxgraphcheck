package com.example.fxgraphcheck.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TriangleCell extends Cell {

    public TriangleCell( String id) {
        super( id);

        double width = 25;
        double height = 25;

        Polygon view = new Polygon( width / 2, 0, width, height, 0, height);

        view.setStroke(Color.RED);
        view.setFill(Color.RED);

        setView( view);

    }

}