package com.viet.survival;

public class VillageEvents {
    private final String text;
    private final String resourceType;
    private final int amount;
    private final double probability;

    VillageEvents(String text, String resourceType, int amount, double probability) {
        this.text = text;
        this.resourceType = resourceType;
        this.amount = amount;
        this.probability = probability;
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

    public double getProbability() {
        return probability;
    }
}
