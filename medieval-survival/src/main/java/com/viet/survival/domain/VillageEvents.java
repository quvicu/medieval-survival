package com.viet.survival.domain;

import java.util.ArrayList;

public class VillageEvents {
    private final String text;
    private final String resourceType;
    private final int amount;
    private final double probability;

    private static final ArrayList<VillageEvents> villageEventsList =  new ArrayList<>();

    //Negative events
    private static final VillageEvents ratInfestation = new VillageEvents("Rats had a feast in your food storage.", "food", -3, 1);
    private static final VillageEvents foodSpoilage = new VillageEvents("Some of ur food spoiled", "food", -3, 1);
    private static final VillageEvents wolfAttack = new VillageEvents("Wolves attacked ur village and it has cost a life", "population", -1, 1);
    private static final VillageEvents brokenTools = new VillageEvents("Broken tools", "wood", -2, 1);

    //Neutral events
    private static final VillageEvents batsInTheNight = new VillageEvents("A swarm of bats circled above the village before disappearing into the darkness.", "none", 0, 2);
    private static final VillageEvents crimsonMoon = new VillageEvents("The moon glowed with a deep crimson hue throughout the night.", "none", 0, 2);
    private static final VillageEvents distantHowls = new VillageEvents("Howls echoed across the valley long after sunset.", "none", 0, 2);
    private static final VillageEvents watchfulEyes = new VillageEvents("Several villagers reported feeling watched while walking home at night.", "none", 0, 2);
    private static final VillageEvents blackMist = new VillageEvents("A cold black mist rolled through the village during the night.", "none", 0, 2);
    private static final VillageEvents silentDawn = new VillageEvents("The villagers awoke to an unsettling silence. Not a single bird could be heard.", "none", 0, 2);
    private static final VillageEvents paleFog = new VillageEvents("A pale fog drifted through the village streets until sunrise.", "none", 0, 2);
    //Positive Events
    private static final VillageEvents theWhiteStag = new VillageEvents("A rare white stag wandered near the village. Hunters returned with plenty of meat.", "food", 8, 1);
    private static final VillageEvents huntersMoon = new VillageEvents("Under the pale moonlight, the hunters enjoyed unusual success.", "food", 5, 1);
    private static final VillageEvents caravanWreck = new VillageEvents("Villagers discovered the remains of a shattered merchant caravan on the old forest road. \nThe cargo was beyond saving, but one survivor was found among the wreckage.", "population", 1, 1);

    //initializes village events
    static {
        initializeVillageEvents();
    }

    private static void initializeVillageEvents() {
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
        villageEventsList.add(silentDawn);
        villageEventsList.add(paleFog);
    }

    VillageEvents(String text, String resourceType, int amount, double probability) {
        this.text = text;
        this.resourceType = resourceType;
        this.amount = amount;
        this.probability = probability;
    }

    public static double getTotalProbability() {
        double total = 0;
        for(VillageEvents villageEvent : villageEventsList) {
            total += villageEvent.probability;
        }
        return total;
    }

    public static VillageEvents getRandomVillageEvent() {
        double randomNumber = Math.random() * getTotalProbability();
        double running = 0;

        for(VillageEvents villageEvent : villageEventsList) {
            running += villageEvent.probability;
            if(randomNumber <= running) {
                return villageEvent;
            }
        }
        return null;
    }

    public void printText(){
        System.out.println(text);
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getAmount() {
        return amount;
    }
}
