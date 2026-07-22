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
                    break;
                case 2:
                    village.gatherWood();
                    break;
                case 3:
                    village.endDay();
                    break;
                case 4:
                    scholar.start();
                    break;
                case 5:
                    village.recruitVillager();
                    break;
                case 6:
                    quit();
                    break;
            }
        } while (isRunning);
    }

    private void endDay() {
        village.endDay();
        if (village.isStarving()) {
            System.out.println("Starving... Game over.");
            isRunning = false;
        }
    }

    private void quit() {
        isRunning = false;
    }
}