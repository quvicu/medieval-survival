package com.viet.survival.gui;

import com.viet.survival.domain.Village;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static com.viet.survival.gui.AssetLoader.loadImage;

public class VillageView {

    private final Village village;
    // getting the assets from the resource folder
    private final Image woodImage = loadImage("wood_icon.PNG");
    private final Image foodImage = loadImage("food_icon.PNG");
    private final Image populationImage = loadImage("population_icon.PNG");
    private final Image bachgroundImage = loadImage("background.PNG");

    //Put images into ImageView to be able to add it to hbox since it only takes nodes
    private final ImageView woodView = new ImageView(woodImage);
    private final ImageView foodView = new ImageView(foodImage);
    private final ImageView populationView = new ImageView(populationImage);
    private final ImageView backgroundView = new ImageView(bachgroundImage);

    public VillageView(Village village) {
        this.village = village;
    }

    public void show(Stage stage) {


        StackPane pane = new StackPane();
        HBox hBox = new HBox();
        hBox.setSpacing(10);

        //extract it duplicate
        backgroundView.fitWidthProperty().bind(pane.widthProperty());
        backgroundView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(backgroundView);

        pane.getChildren().add(hBox);

        String foodAmount = String.valueOf(village.getFood());
        Label foodLabel = new Label(foodAmount);
        hBox.getChildren().add(foodView);
        hBox.getChildren().add(foodLabel);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        String woodAmount = String.valueOf(village.getWood());
        Label woodLabel = new Label(woodAmount);
        hBox.getChildren().add(woodView);
        hBox.getChildren().add(woodLabel);

        String populationAmount = String.valueOf(village.getPopulation());
        Label populationLabel = new Label(populationAmount);
        hBox.getChildren().add(populationView);
        hBox.getChildren().add(populationLabel);

        stage.getScene().setRoot(pane);
    }
}
