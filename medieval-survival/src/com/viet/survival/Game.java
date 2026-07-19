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
            printVillageStats();
            System.out.println(MENU);
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    village.gatherFood();
                    break;
                case 2:
                    gatherWood();
                    break;
                case 3:
                    endDay();
                    break;
                case 4:
                    scholar.start();
                    break;
                case 5:
                    recruitVillager();
                    break;
                case 6:
                    quit();
                    break;
            }
        } while (isRunning);
    }

    private void gatherWood() {
        int randomWoodAmount = (int)(Math.random() * 18);
        int currentWood = village.getWood();
        int totalWood = currentWood + randomWoodAmount;

        village.setWood(totalWood);
        System.out.println("Gathered: " + randomWoodAmount + " Wood\nTotal Wood: " + totalWood);
    }

    private void endDay() {
        village.consumeFood();
        System.out.println("Food consumed.");

        if(village.isStarving()) {
            System.out.println("Starving... Game over.");
            isRunning = false;
            return;
        }

        village.setDay(village.getDay() + 1);
        System.out.println("Day " + village.getDay() + " begins.");
    }

    private void quit() {
        isRunning = false;
    }

    private void recruitVillager() {
            village.recruitVillager();
    }

    private void printVillageStats() {
        System.out.println("Population: " + village.getPopulation());
        System.out.println("Day: " + village.getDay());
        System.out.println("Food: " + village.getFood());
        System.out.println("Wood: " + village.getWood());
    }
}