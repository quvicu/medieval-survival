package com.viet.survival.strategy;

import com.viet.survival.domain.VillageEvents;

public class RandomVillageEventProvider implements VillageEventProvider {

    @Override
    public VillageEvents getRandomVillageEvent() {
        return VillageEvents.getRandomVillageEvent();
    }
}
