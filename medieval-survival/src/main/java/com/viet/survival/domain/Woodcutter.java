package com.viet.survival.domain;

public class Woodcutter extends Villager {

    public Woodcutter(int villagerId, String villagerName) {
        super(villagerId, villagerName, VillagerType.WOODCUTTER);
    }

    @Override
    public double getMultiplier() {
        return 2.0;
    }

    @Override
    public ResourceType getGatheredResourceType() {
        return ResourceType.WOOD;
    }


}
