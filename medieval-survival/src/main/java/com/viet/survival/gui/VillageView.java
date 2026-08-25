package com.viet.survival.gui;

import com.viet.survival.domain.Village;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        VBox contentBox = new VBox();
        contentBox.setSpacing(30);
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        backgroundView.fitWidthProperty().bind(pane.widthProperty());
        backgroundView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(backgroundView);

        contentBox.getChildren().add(hBox);
        contentBox.getChildren().add(buttonBox);
        pane.getChildren().add(contentBox);


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

        TextArea statusArea = new TextArea();
        statusArea.setPrefRowCount(4);
        statusArea.setMaxWidth(400);
        statusArea.setEditable(false);
        contentBox.getChildren().add(statusArea);

        Button gatherFoodButton = new Button("Gather Food", new ImageView(foodImage));
        gatherFoodButton.setOnAction(event -> {
            statusArea.appendText("Food Gathered:" + village.gatherFood() + "\n");
            foodLabel.setText(String.valueOf(village.getFood()));
        });
        buttonBox.getChildren().add(gatherFoodButton);

        Button gatherWoodButton = new Button("Gather Wood", new ImageView(woodImage));
        gatherWoodButton.setOnAction(event -> {
            statusArea.appendText("Wood Gathered:" + village.gatherWood() + "\n");
            woodLabel.setText(String.valueOf(village.getWood()));
        });
        buttonBox.getChildren().add(gatherWoodButton);

        Button recruitVillagerButton = new Button("Recruit Farmer", new ImageView(populationImage));
        recruitVillagerButton.setOnAction(event -> {
            if(village.recruitFarmer()){
                populationLabel.setText(String.valueOf(village.getPopulation()));
                foodLabel.setText(String.valueOf(village.getFood()));
                statusArea.appendText("Recruited a new Farmer\n");
            }
            else {
                statusArea.appendText("Not enough food for recruitment\n");
            }
        });
        buttonBox.getChildren().add(recruitVillagerButton);

        Button endDayButton = new Button("End Day...", new ImageView(populationImage));
        endDayButton.setOnAction(event -> {
            village.endDay();
            statusArea.appendText("Ending Day......\n");
            statusArea.appendText(village.endDay() + "\n");
            foodLabel.setText(String.valueOf(village.getFood()));
            woodLabel.setText(String.valueOf(village.getWood()));
            populationLabel.setText(String.valueOf(village.getPopulation()));
        });
        buttonBox.getChildren().add(endDayButton);

        stage.getScene().setRoot(pane);
    }
}
