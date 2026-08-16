package com.viet.survival.gui;

import com.viet.survival.domain.Village;
import com.viet.survival.persistence.SaveManager;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static com.viet.survival.gui.AssetLoader.loadImage;

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
        newGameButton.setOnAction(event -> {
            Village newVillage = Village.createStartingVillage();
            new VillageView(newVillage).show(stage);
        });

        Button loadGameButton = new Button();
        loadGameButton.setTranslateY(20);
        loadGameButton.setOnAction(event -> {
            Village loadedVillage = new SaveManager().loadGame();
            if (loadedVillage != null) {
                new VillageView(loadedVillage).show(stage);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("No saved game found...");
                alert.showAndWait();
            }
        });

        pane.getChildren().add(newGameButton);
        pane.getChildren().add(loadGameButton);
        newGameButton.setText("New Game");
        loadGameButton.setText("Load Game");
    }
}
