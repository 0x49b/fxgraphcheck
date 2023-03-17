package com.example.fxgraphcheck.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.*;


public class ZSCell extends Cell {

    Group view;

    public ZSCell(String id) {
        super(id);
        Group view = new Group();

        Rectangle rect = new Rectangle(25, 25);
        //rect.setStroke(Color.BLACK);
        rect.setFill(Color.web("0xffe135", 1.0f));

        Line line = new Line();
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(3d);
        line.setStartX(0);
        line.setStartY(12.5);
        line.setEndX(25);
        line.setEndY(12.5);

        Anchor leftAnchor = new Anchor(-6, 10);
        Anchor rightAnchor = new Anchor(26, 10);

        view.getChildren().addAll( leftAnchor.getAnchor(), rightAnchor.getAnchor(), rect, line);

        setView(view);
    }

    @Override
    public void setPosition(double x, double y) {
        view.setLayoutX(x);
        view.setLayoutY(y);
    }

}
