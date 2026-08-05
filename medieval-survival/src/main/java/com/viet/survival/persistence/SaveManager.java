package com.viet.survival.persistence;

import com.google.gson.*;
import com.viet.survival.domain.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveManager {
    private static final String saves = "Saves";
    private static final String saveGameJson = "savegame.json";

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Villager.class, new VillagerReader())
            .create();

    public void saveGame(Village village){
        String json = gson.toJson(village);
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
            loadedVillage = gson.fromJson(content, Village.class);
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

    // Liest nur das "villagerType"-Namensschild, baut dann den passenden konkreten Typ
    private static class VillagerReader implements JsonDeserializer<Villager> {
        @Override
        public Villager deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            String type = json.getAsJsonObject().get("villagerType").getAsString();
            VillagerType villagerType = VillagerType.valueOf(type.toUpperCase());
            return switch (villagerType) {
                case FARMER -> context.deserialize(json, Farmer.class);
                case WOODCUTTER -> context.deserialize(json, Woodcutter.class);
            };
        }
    }
}