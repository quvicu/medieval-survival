package com.viet.survival.gui;

import com.viet.survival.domain.Village;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static com.viet.survival.gui.AssetLoader.loadImage;

public class VillageView {

    private final Village village;

    public VillageView(Village village) {
        this.village = village;
    }

    public void show(Stage stage) {
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane);

        // default window size after leaving fullscreen
        stage.setWidth(800); stage.setHeight(600);

        // getting the assets from the resource folder
        Image woodIcon = loadImage("wood_icon.PNG");
        Image foodIcon = loadImage("food_icon.PNG");
        Image populationIcon = loadImage("population_icon.PNG");
        Image backgroundImage = loadImage("background.PNG");

    }
}
