package com.viet.survival.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class MedievalSurvivalApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainWindow window = new MainWindow();
        window.show(primaryStage);
    }
}
