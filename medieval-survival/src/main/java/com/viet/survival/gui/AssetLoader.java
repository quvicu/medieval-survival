package com.viet.survival.gui;

import javafx.scene.image.Image;

import java.util.Objects;

public class AssetLoader {
    public static Image loadImage(String path) {
        return new Image(Objects.requireNonNull(AssetLoader.class.getResourceAsStream("/assets/" + path)));
    }
}
