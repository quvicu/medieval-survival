package com.viet.survival;

public class Game {
    private final Village village = new Village(10, 10, 1);

    public void start() {
        System.out.println("=== Medieval Survival ===");

    }

    public Village getVillage() {
        return village;
    }

    public void gatherFood(){
        int gatheredFood = (int)(Math.random() * 18);
        int currentFood = village.getFood();
        int totalFood = currentFood + gatheredFood;

        village.setFood(totalFood);
        System.out.println("Gathered: " + gatheredFood + " Food\nTotal Food: " + totalFood);
    }
}



