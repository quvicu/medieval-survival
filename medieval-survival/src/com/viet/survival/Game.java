package com.viet.survival;

import java.util.Scanner;

public class Game {
    private final Village village = new Village(1, 10, 10, 1);
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