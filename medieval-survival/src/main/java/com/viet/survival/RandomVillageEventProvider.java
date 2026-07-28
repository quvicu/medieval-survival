package com.viet.survival;

public class RandomVillageEventProvider implements VillageEventProvider {

    @Override
    public VillageEvents getRandomVillageEvent() {
        return VillageEvents.getRandomVillageEvent();
    }
}
