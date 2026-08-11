package com.viet.survival.gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainWindow {

    public void show(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root);

        stage.setTitle("Medieval Survival");
        stage.setWidth(800);
        stage.setHeight(600);

        stage.setScene(scene);
        stage.show();
    }
}
