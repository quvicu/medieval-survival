package com.viet.survival;


public class Village {

    private int population;
    private int food;
    private int wood;
    private int day;
    private static final int MAX_FOOD = 200;

    public Village(int population, int food, int wood, int day) {
        this.population = population;
        this.food = food;
        this.wood = wood;
        this.day = day;
    }

    public int getPopulation() { return population; }

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

    public int getFood() {
        return food;
    }
    public void setFood(int food) {
        this.food = food;
    }

    public int getWood() {
        return wood;
    }
    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }

    public void consumeFood(){
        food -= 2 * population;
    }
    public boolean isStarving(){
            return food <= 0;
    }

}


