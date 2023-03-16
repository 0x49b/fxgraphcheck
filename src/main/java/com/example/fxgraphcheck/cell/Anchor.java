package com.example.fxgraphcheck.cell;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Anchor {

    Rectangle view;

    public Anchor(double x, double y){
        view = new Rectangle(5, 5);
        view.setStrokeWidth(.5);
        view.setStroke(Color.BLACK);
        view.setFill(Color.WHITE);
        view.setX(x);
        view.setY(y);
    }

    public Rectangle getAnchor(){
        return view;
    }

}
