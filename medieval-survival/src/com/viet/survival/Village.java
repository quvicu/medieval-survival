package com.viet.survival;


import java.util.ArrayList;
import java.util.Random;

public class Village {

    private int population;
    private int food;
    private int wood;

    private int day;
    private static final int MAX_FOOD = 500;
    private static final int MAX_WOOD = 500;
    private static final int MAX_POPULATION = 100;

    private final ArrayList<VillageEvents> villageEventsList =  new ArrayList<>();

    //Negative events
    private static final VillageEvents ratInfestation = new VillageEvents("Rats had a feast in your food storage.", "food", -3, 0.15);
    private static final VillageEvents foodSpoilage = new VillageEvents("Some of ur food spoiled", "food", -3, 0.15);
    private static final VillageEvents wolfAttack = new VillageEvents("Wolves attacked ur village and it has cost a life", "population", -1, 0.1);
    private static final VillageEvents brokenTools = new VillageEvents("Broken tools", "wood", -2, 0.3);

    //Neutral events
    private static final VillageEvents batsInTheNight = new VillageEvents("A swarm of bats circled above the village before disappearing into the darkness.", "none", 0, 0.3);
    private static final VillageEvents crimsonMoon = new VillageEvents("The moon glowed with a deep crimson hue throughout the night.", "none", 0, 0.15);
    private static final VillageEvents distantHowls = new VillageEvents("Howls echoed across the valley long after sunset.", "none", 0, 0.30);
    private static final VillageEvents watchfulEyes = new VillageEvents("Several villagers reported feeling watched while walking home at night.", "none", 0, 0.2);
    private static final VillageEvents blackMist = new VillageEvents("A cold black mist rolled through the village during the night.", "none", 0, 0.2);

    //Positive Events
    private static final VillageEvents theWhiteStag = new VillageEvents("A rare white stag wandered near the village. Hunters returned with plenty of meat.", "food", 8, 0.2);
    private static final VillageEvents huntersMoon = new VillageEvents("Under the pale moonlight, the hunters enjoyed unusual success.", "food", 5, 0.4);
    private static final VillageEvents caravanWreck = new VillageEvents("Villagers discovered the remains of a shattered merchant caravan on the old forest road. \nThe cargo was beyond saving, but one survivor was found among the wreckage.", "population", 1, 0.1);

    public Village(int population, int food, int wood, int day) {
        this.population = population;
        this.food = food;
        this.wood = wood;
        this.day = day;

        initializeVillageEvents();
    }

    private void initializeVillageEvents() {
        villageEventsList.add(ratInfestation);
        villageEventsList.add(foodSpoilage);
        villageEventsList.add(wolfAttack);
        villageEventsList.add(brokenTools);
        villageEventsList.add(batsInTheNight);
        villageEventsList.add(crimsonMoon);
        villageEventsList.add(distantHowls);
        villageEventsList.add(watchfulEyes);
        villageEventsList.add(blackMist);
        villageEventsList.add(theWhiteStag);
        villageEventsList.add(huntersMoon);
        villageEventsList.add(caravanWreck);
    }

    private VillageEvents getRandomVillageEvent() {
        int randomIndex = (int)(Math.random() * villageEventsList.size());
        return villageEventsList.get(randomIndex);
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
        if (!isStarving() && !isOutOfWood() && !isUnpopulated()) {
            day++;
            System.out.println("Day " + day + " begins.");
            executeVillageEvent(getRandomVillageEvent());
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

    public boolean isOutOfWood(){
        return wood <= 0;
    }

    public boolean isUnpopulated(){
        return population <= 0;
    }
}


