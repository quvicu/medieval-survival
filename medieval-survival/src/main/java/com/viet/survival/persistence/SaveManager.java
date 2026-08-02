package com.viet.survival.persistence;

import com.google.gson.Gson;
import com.viet.survival.domain.Village;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveManager {
    private static final String saves = "Saves";
    private static final String saveGameJson = "savegame.json";

    public void saveGame(Village village){
        String json = new Gson().toJson(village);
        try {
            Files.createDirectories(Path.of(saves));
            Files.writeString(Path.of(saves, saveGameJson), json);
        } catch (IOException e) {
            System.out.println("Error while writing: " + e.getMessage());
        }
    }

    public Village loadGame(){
        Village loadedVillage = null;
        try {
            String content = Files.readString(Path.of(saves, saveGameJson));
            loadedVillage = new Gson().fromJson(content, Village.class);
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Error while reading: " + e.getMessage());
        }
        return loadedVillage;
    }

    public void deleteSave() {
        try {
            Files.deleteIfExists(Path.of(saves, saveGameJson));
        } catch (IOException e) {
            System.out.println("Error while deleting: " + e.getMessage());
        }
    }

}
