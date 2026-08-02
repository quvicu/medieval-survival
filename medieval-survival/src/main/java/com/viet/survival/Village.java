package com.viet.survival;

public class Village {

    private int population;
    private int food;
    private int wood;
    private int day;

    private static final int MAX_FOOD = 500;
    private static final int MAX_WOOD = 500;
    private static final int MAX_POPULATION = 100;

    private final transient VillageEventProvider eventProvider;
    private final transient FoodConsumptionStrategy foodConsumptionStrategy;

    public Village(int population, int food, int wood, int day) {
        this(population, food, wood, day, new RandomVillageEventProvider(), new LowFoodConsumptionStrategy());
    }

    public Village(int population, int food, int wood, int day, VillageEventProvider eventProvider, FoodConsumptionStrategy foodConsumptionStrategy) {
        this.population = population;
        this.food = food;
        this.wood = wood;
        this.day = day;
        this.eventProvider = eventProvider;
        this.foodConsumptionStrategy = foodConsumptionStrategy;
    }

    public int getFood() {
        return food;
    }

    public int getWood() {
        return wood;
    }

    public int getPopulation() {
        return population;
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
        if (!isDoomed()) {
            day++;
            System.out.println("Day " + day + " begins.");
            VillageEvents villageEvent = eventProvider.getRandomVillageEvent();
            if(villageEvent != null) {
                executeVillageEvent(villageEvent);
            }
            else
                System.out.println("Error village event! is null");
        }
    }

    private void executeVillageEvent(VillageEvents villageEvent) {
        villageEvent.printText();
        switch (villageEvent.getResourceType()) {
            case "food":
                food = Math.min(food + villageEvent.getAmount(), MAX_FOOD);
                break;
            case "wood":
                wood = Math.min(wood + villageEvent.getAmount(), MAX_WOOD);
                break;
            case "population":
                population = Math.min(population + villageEvent.getAmount(), MAX_POPULATION);
                break;
            default:
                break;
        }
    }

    public void printStats() {
        System.out.println("Population: " + population);
        System.out.println("Day: " + day);
        System.out.println("Food: " + food);
        System.out.println("Wood: " + wood);
    }

    public void consumeFood(){
        food -= foodConsumptionStrategy.getFoodConsumptionFactor() * population;
    }

    public boolean isDoomed(){
        return isStarving() || isOutOfWood() || isUnpopulated();
    }

    public boolean isStarving(){
            return food <= 0;
    }

    public boolean isOutOfWood(){
        return wood <= 0;
    }

    public boolean isUnpopulated(){
        return population <= 0;
    }
}


