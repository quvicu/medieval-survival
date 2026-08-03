package com.viet.survival.game;

import com.viet.survival.domain.Farmer;
import com.viet.survival.domain.Village;
import com.viet.survival.domain.Villager;
import com.viet.survival.persistence.SaveManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final SaveManager saveManager = new SaveManager();
    private final Village village;
    private final ArrayList<Villager> villagers = new ArrayList<>();

    public Game() {
        Village chosenVillage = null;
        boolean initialized = false;
        do {
            System.out.println("1. New Game\n2. Load Game");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    chosenVillage = new Village(villagers, 5, 5, 1);
                    villagers.add(new Farmer(1, "Famy"));
                    initialized = true;
                    break;
                case 2:
                    Village loaded = saveManager.loadGame();
                    if (loaded == null) {
                        System.out.println("No saving file found.");
                    } else {
                        chosenVillage = loaded;
                        initialized = true;
                    }
                    break;
                default:
                    System.out.println("Illegal choice.");
                    break;
            }
        } while (!initialized);
        village = chosenVillage;
    }

    private boolean isRunning = true;
    private static final String MENU =
            """
                    1. Gather Food
                    2. Gather Wood
                    3. End Day
                    4. WIP
                    5. Recruit Villager
                    6. Quit""";

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        do {
            System.out.println("=== Medieval Survival ===");
            village.printStats();
            System.out.println(MENU);
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    village.gatherFood();
                    saveGame();
                    break;
                case 2:
                    village.gatherWood();
                    saveGame();
                    break;
                case 3:
                    endDay();
                    if(isRunning) {
                        saveGame();
                    }
                    break;
                case 4:
                    System.out.println("WIP");
                    break;
                case 5:
                    village.recruitFarmer();
                    saveGame();
                    break;
                case 6:
                    quit();
                    saveGame();
                    break;
            }
        } while (isRunning);
    }

    private void endDay() {
        village.endDay();
        if (village.isDoomed()) {
            if (village.isStarving()) {
                System.out.println("Village has starved out.\nGame over...");
            } else if (village.isUnpopulated()) {
                System.out.println("Village has unpopulated out.\nGame over...");
            } else if (village.isOutOfWood()) {
                System.out.println("Village has run out of wood.\nGame over...");
            }
            quit();
            saveManager.deleteSave();
        }
    }

    private void quit() {
        isRunning = false;
    }

    private void saveGame() {
        saveManager.saveGame(village);
    }
}
