package com.viet.survival.game;

import com.viet.survival.Scholar;
import com.viet.survival.domain.Village;
import com.viet.survival.persistence.SaveManager;

import java.util.Scanner;

public class Game {
    private final SaveManager saveManager = new SaveManager();
    private final Village village;

    public Game() {
        Village chosenVillage = null;
        boolean initialized = false;
        do {
            System.out.println("1. New Game\n2. Load Game");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    chosenVillage = new Village(1, 5, 5, 1);
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
                    4. Talk to Scholar
                    5. Recruit Villager
                    6. Quit""";

    private final Scanner scanner = new Scanner(System.in);
    private final Scholar scholar = new Scholar(scanner);

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
                    scholar.start();
                    break;
                case 5:
                    village.recruitVillager();
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
