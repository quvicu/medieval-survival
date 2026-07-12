package com.viet.survival;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final Village village = new Village(10, 10, 1);
    private boolean isRunning = true;
    private static final String MENU =
            """
                    1. Gather Food
                    2. Gather Wood
                    3. End Day
                    4. Talk to Scholar
                    5. Quit""";
    private static final String SCHOLAR_MENU =
            """
                    1. Talk to Scholar
                    2. Binary ->Decimal
                    3. Decimal -> Binary
                    4. Ask Scholar:
                    5. Leave""";

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        do {
            System.out.println("=== Medieval Survival ===");
            printVillageStats();
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
                    talkToScholar();
                    break;
                case 5:
                    quit();
                    break;
            }
        } while (isRunning);
    }

    private void gatherFood(){
        int randomFoodAmount = (int)(Math.random() * 18);
        int currentFood = village.getFood();
        int totalFood = currentFood + randomFoodAmount;

        village.setFood(totalFood);
        System.out.println("Gathered: " + randomFoodAmount + " Food\nTotal Food: " + totalFood);
    }

    private void gatherWood(){
        int randomWoodAmount = (int)(Math.random() * 18);
        int currentWood = village.getWood();
        int totalWood = currentWood + randomWoodAmount;

        village.setWood(totalWood);
        System.out.println("Gathered: " + randomWoodAmount + " Wood\nTotal Wood: " + totalWood);
    }

    private void endDay(){
        village.setDay(village.getDay() + 1);
        System.out.println("Day " + village.getDay() + " begins.");
    }

    private void talkToScholar() {
        do {
            System.out.println(SCHOLAR_MENU);
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    System.out.println("W");
                    break;
                case 2:
                    System.out.println("Binary -> Decimal");
                    binaryToDecimal();
                    break;
                case 3:
                    System.out.println("Decimal -> Binary");
                    decimalToBinary();
                    break;
                case 4:
                    System.out.println("Ask Scholar:");
                    break;
                case 5:
                    return;
            }
        }while (isRunning);
    }

    private void binaryToDecimal() {
        String input = scanner.next();
        int result = input.charAt(0) - '0';
        for(int i = 1; i < input.length(); i++){
            int inputNumber = (int)input.charAt(i) - '0';
            result = (result * 2  + inputNumber);
        }
        System.out.println(result);
    }


    private void decimalToBinary(){
        int input = scanner.nextInt();
        int decimalInput = input;
        StringBuilder builder = new StringBuilder();
        ArrayList<Integer> binaries = new ArrayList<>();
        if(input == 0){
            System.out.println("The Binary of 0 is 0\n");
            return;
        }
        else {
            while (input != 0) {
                binaries.add(input % 2);
                input /= 2;
            }
        }
        for(int i = binaries.size(); i > 0; i--){
            builder.append(binaries.get(i-1));
        }
        System.out.println("Calculating...\nDecimal: " + decimalInput + "\nBinary: " + builder.toString() + "\n");
    }

    private void quit() {
        isRunning = false;
    }

    private void printVillageStats(){
        System.out.println("Day: " + village.getDay());
        System.out.println("Food: " + village.getFood());
        System.out.println("Wood: " + village.getWood());
    }
}



