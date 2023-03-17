package com.example.fxgraphcheck;

import com.example.fxgraphcheck.cell.CellType;
import com.example.fxgraphcheck.layout.Layout;
import com.example.fxgraphcheck.layout.RandomLayout;
import com.example.fxgraphcheck.model.Model;
import com.example.fxgraphcheck.station.Station;
import com.example.fxgraphcheck.view.Rail0rMenu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;
import java.util.UUID;

public class Main extends Application {

    Graph graph;
    Rail0rMenu menu;
    Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        graph = new Graph();
        menu = new Rail0rMenu(graph);
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> graph.endUpdate()));
        timeline.setCycleCount(Animation.INDEFINITE);

        root.setTop(menu);
        root.setCenter(graph.getScrollPane());

        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());

        this.setupEventHandlers();

        primaryStage.setScene(scene);
        primaryStage.show();

        addGraphComponents();

        Layout layout = new RandomLayout(graph);
        layout.execute();
        timeline.play();

    }

    private void addGraphComponents() {

        Model model = graph.getModel();

        graph.beginUpdate();

        model.addCell("Cell A", CellType.RECTANGLE);
        model.addCell("Cell B", CellType.RECTANGLE);
        model.addCell("Cell C", CellType.RECTANGLE);
        model.addCell("Cell D", CellType.TRIANGLE);
        model.addCell("Cell E", CellType.TRIANGLE);
        model.addCell("Cell F", CellType.RECTANGLE);
        model.addCell("Cell G", CellType.RECTANGLE);
        model.addCell("ZS1", CellType.ZS);


        model.addEdge("Cell A", "Cell B");
        model.addEdge("Cell A", "Cell C");
        model.addEdge("Cell B", "Cell C");
        model.addEdge("Cell C", "Cell D");
        model.addEdge("Cell B", "Cell E");
        model.addEdge("Cell D", "Cell F");
        model.addEdge("Cell D", "Cell G");

        model.addEdge("Cell D", "Cell E", Color.RED);

        model.addStation(new Station("Doppleschwand-Rom._SiC", "DOPP", 909, 273));

        graph.endUpdate();

    }

    private void setupEventHandlers() {}

    public static void main(String[] args) {
        launch(args);
    }
}