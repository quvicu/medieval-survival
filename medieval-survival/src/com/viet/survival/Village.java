package com.viet.survival;


public class Village {

    private int population;
    private int food;
    private int wood;
    private int day;
    private static final int MAX_FOOD = 50;
    private static final int MAX_WOOD = 50;

    public Village(int population, int food, int wood, int day) {
        this.population = population;
        this.food = food;
        this.wood = wood;
        this.day = day;
    }

    public void recruitVillager() {
        if(isEnoughFoodForRecruitment()) {
            population++;
            food -= 10;
            System.out.println("Villager recruited!\n" +
                               "Population: " + population);
        }
        else
            System.out.println("Not enough food for recruitment, you need more than 10 Food!");
    }

    public boolean isEnoughFoodForRecruitment() {
            return food > 10;
    }

    public void gatherFood() {
        int amount = (int)(Math.random() * 18);
        food = Math.min(food + amount, MAX_FOOD); //
        System.out.println("Gathered: " + amount + " Food\nTotal Food: " + food);
    }

    public void gatherWood() {
        int amount = (int)(Math.random() * 18);
        wood = Math.min(wood + amount, MAX_WOOD);
        System.out.println("Gathered: " + amount + " Wood\nTotal Wood: " + wood);
    }

    public void endDay() {
        consumeFood();
        System.out.println("Food consumed.");
        if (!isStarving()) {
            day++;
            System.out.println("Day " + day + " begins.");
        }
    }

    public void printStats() {
        System.out.println("Population: " + population);
        System.out.println("Day: " + day);
        System.out.println("Food: " + food);
        System.out.println("Wood: " + wood);
    }

    public void consumeFood(){
        food -= 2 * population;
    }
    public boolean isStarving(){
            return food <= 0;
    }

}


