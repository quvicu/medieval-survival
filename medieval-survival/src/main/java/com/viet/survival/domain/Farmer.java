package com.viet.survival.domain;

public class Farmer extends Villager{

    public Farmer(int villagerId, String villagerName) {
        super(villagerId, villagerName, VillagerType.FARMER);
    }

    @Override
    public double getMultiplier() {
        return 1.5;
    }

    @Override
    public ResourceType getGatheredResourceType() {
        return ResourceType.FOOD;
    }

}
