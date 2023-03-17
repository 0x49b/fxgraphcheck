package com.example.fxgraphcheck.view;


import com.example.fxgraphcheck.Graph;
import com.example.fxgraphcheck.cell.CellType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.UUID;

public class Rail0rMenu extends MenuBar {

    Graph graph;
    Menu fileMenu;
    MenuItem open;

    Menu addMenu;
    MenuItem addZS;

    public Rail0rMenu(Graph graph) {
        super();

        this.graph = graph;

        fileMenu = new Menu("File");
        open = new MenuItem("Open");

        fileMenu.getItems().addAll(open);

        addMenu = new Menu("Add");
        addZS = new MenuItem("Add ZS");

        addMenu.getItems().addAll(addZS);


        getStyleClass().add("custom-menu-bar");
        getMenus().addAll(fileMenu, addMenu);

        this.registerActionHandler();
    }

    private void registerActionHandler() {
        addZS.setOnAction(event -> {
            graph.getModel().addCell(UUID.randomUUID().toString(), CellType.ZS);
        });
    }

}
