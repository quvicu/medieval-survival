package com.viet.survival.domain;

import com.viet.survival.strategy.FoodConsumptionStrategy;
import com.viet.survival.strategy.LowFoodConsumptionStrategy;
import com.viet.survival.strategy.RandomVillageEventProvider;
import com.viet.survival.strategy.VillageEventProvider;

import java.util.List;

public class Village {

    private final List<Villager> villagers;
    private int food;
    private int wood;
    private int day;

    private static final int MAX_FOOD = 500;
    private static final int MAX_WOOD = 500;
    private static final int MAX_POPULATION = 100;

    private final transient VillageEventProvider eventProvider;
    private final transient FoodConsumptionStrategy foodConsumptionStrategy;

    public Village(List<Villager> villagers, int food, int wood, int day) {
        this(villagers, food, wood, day, new RandomVillageEventProvider(), new LowFoodConsumptionStrategy());
    }

    public Village(List<Villager> villagers, int food, int wood, int day, VillageEventProvider eventProvider, FoodConsumptionStrategy foodConsumptionStrategy) {
        this.villagers = villagers;
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

    public int getPopulation() { return villagers.size(); }

    public void recruitFarmer() {
        if(isEnoughFoodForRecruitment()) {
            villagers.add(addNewFarmer());
            food -= 10;
            System.out.println("Villager recruited!\n" +
                               "Population: " + villagers.size());
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
            case  ResourceType.FOOD:
                food = Math.min(food + villageEvent.getAmount(), MAX_FOOD);
                break;
            case  ResourceType.WOOD:
                wood = Math.min(wood + villageEvent.getAmount(), MAX_WOOD);
                break;
            case  ResourceType.POPULATION:
                killOrAddVillager(villageEvent);
                break;
            default:
                break;
        }
    }

    private void killOrAddVillager(VillageEvents villageEvent) {
        boolean isAmountPositive = villageEvent.isAmountPositive();
        if(isAmountPositive) {
            if(!is_MAX_POPULATION()) {
                villagers.add(addNewFarmer());
            }
            else  {
                System.out.println("Your have already reached the capacity of your population! No villager was added.");
            }
        }
        else {
            killRandomVillagers(villageEvent.getAmount());
        }
    }

    private void killRandomVillagers(int amount) {
        int positiveAmount = amount * (-1);
        int actualDeaths = Math.min(positiveAmount, villagers.size());

        for(int i = 0; i < actualDeaths; i++){
            villagers.remove((int)(Math.random() * villagers.size()));
        }
        System.out.println(actualDeaths + " villager died.");
    }

    private Woodcutter addNewWoodcutter() {
        return(new Woodcutter(villagers.size() + 1, "Unknown Woodcutter"));
    }

    private Farmer addNewFarmer() {
        return(new Farmer(villagers.size() + 1, "Unknown Farmer"));
    }

    public void printStats() {
        System.out.println("Population: " + villagers.size());
        System.out.println("Day: " + day);
        System.out.println("Food: " + food);
        System.out.println("Wood: " + wood);
    }

    public void consumeFood(){
        food -= foodConsumptionStrategy.getFoodConsumptionFactor() * villagers.size();
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
        return villagers.isEmpty();
    }

    private boolean is_MAX_POPULATION() {
        return villagers.size() >=  MAX_POPULATION;
    }

}


