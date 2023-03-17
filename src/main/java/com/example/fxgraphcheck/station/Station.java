package com.example.fxgraphcheck.station;

import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.util.UUID;

public class Station extends Pane implements Serializable {

    private final UUID uid;
    private final String name;
    private final String stationCode;


    public Station(String name, String stationCode, double height, double width) {
        super();

        this.uid = UUID.randomUUID();
        this.name = name;
        this.stationCode = stationCode;

        this.setHeight(height);
        this.setWidth(width);

    }

    public String toString() {
        return "Station: " + this.name + " " + this.stationCode + " [" + this.uid + "]";
    }

}
