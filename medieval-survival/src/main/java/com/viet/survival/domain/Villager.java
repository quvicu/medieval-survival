package com.viet.survival.domain;

public abstract class Villager {
    private final int villagerId;
    private String villagerName;
    private final VillagerType villagerType;

    public  Villager(int villagerId, String villagerName, VillagerType villagerType) {
        this.villagerId = villagerId;
        this.villagerName = villagerName;
        this.villagerType = villagerType;
    }

    public abstract double getMultiplier();

    public abstract ResourceType getGatheredResourceType();

    public int getVillagerId() {return villagerId;}
    public String getVillagerName() {return villagerName;}
}
