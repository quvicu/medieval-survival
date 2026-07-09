package com.viet.survival;

import java.util.Scanner;

public class Game {
    private final Village village = new Village(10, 10, 1);
    private boolean isRunning = true;
    private static final String MENU =
            """
                    1. Gather Food
                    2. Gather Wood
                    3. End Day
                    4. Quit""";
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        do {
            System.out.println("=== Medieval Survival ===");
            System.out.println("Day: " + village.getDay());
            System.out.println("Food: " + village.getFood());
            System.out.println("Wood: " + village.getWood());
            System.out.println(MENU);
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    gatherFood();
                    break;
                case 2:
                    gatherWood();
                    break;
                case 3:
                    endDay();
                    break;
                case 4:
                    quit();
                    break;
            }
        } while (isRunning);
    }

    private void quit() {
        isRunning = false;
    }


    public Village getVillage() {
        return village;
    }

    public void gatherFood(){
        int randomFoodAmount = (int)(Math.random() * 18);
        int currentFood = village.getFood();
        int totalFood = currentFood + randomFoodAmount;

        village.setFood(totalFood);
        System.out.println("Gathered: " + randomFoodAmount + " Food\nTotal Food: " + totalFood);
    }

    public void gatherWood(){
        int randomWoodAmount = (int)(Math.random() * 18);
        int currentWood = village.getWood();
        int totalWood = currentWood + randomWoodAmount;

        village.setWood(totalWood);
        System.out.println("Gathered: " + randomWoodAmount + " Wood\nTotal Wood: " + totalWood);
    }

    public void endDay(){
        village.setDay(village.getDay() + 1);
        System.out.println("Day " + village.getDay() + " begins.");
    }
}



