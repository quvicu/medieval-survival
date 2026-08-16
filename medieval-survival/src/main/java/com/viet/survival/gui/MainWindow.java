package com.viet.survival.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainWindow {

    public void show(Stage stage) {

        StackPane pane = new StackPane();
        Scene scene = new Scene(pane);

        // getting the assets from the resource folder
        Image icon = loadImage("wood_icon.PNG");
        Image backgroundImage = loadImage("background.PNG");

        // default window size after leaving fullscreen
        stage.setWidth(800); stage.setHeight(600);

        // add a background image as background which has the same size as pane
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(pane.widthProperty());
        backgroundView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(backgroundView);

        // add icon, title, turning on fullscreen and adding the scene to stage and show
        stage.getIcons().add(icon);
        stage.setTitle("Medieval Survival");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();

        Button newGameButton = new Button();
        newGameButton.setTranslateY(-20);
        newGameButton.setOnAction(event -> System.out.println("New Game"));

        Button loadGameButton = new Button();
        loadGameButton.setTranslateY(20);
        loadGameButton.setOnAction(event -> System.out.println("Load Game"));

        pane.getChildren().add(newGameButton);
        pane.getChildren().add(loadGameButton);
        newGameButton.setText("New Game");
        loadGameButton.setText("Load Game");
    }

    private Image loadImage(String path) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/" + path)));
    }
}
