package com.example.fxgraphcheck.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleCell extends Cell {

    Rectangle view;

    public RectangleCell(String id) {
        super(id);
        view = new Rectangle(25, 25);

        this.setupEventHandlers();

        view.setStroke(Color.DODGERBLUE);
        view.setFill(Color.DODGERBLUE);
        setView(view);

    }

    @Override
    public void setPosition(double x, double y) {
        view.setLayoutX(x);
        view.setLayoutY(y);
    }

    private void setupEventHandlers() {
        //this.activeProperty().addListener();
    }


}
