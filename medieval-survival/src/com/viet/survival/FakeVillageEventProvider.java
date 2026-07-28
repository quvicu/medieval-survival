package com.viet.survival;

import com.viet.survival.VillageEvents;

public class FakeVillageEventProvider implements VillageEventProvider {
    private final VillageEvents fixedEvent;

    public FakeVillageEventProvider(VillageEvents fixedEvent) {
        this.fixedEvent = fixedEvent;
    }

    @Override
    public VillageEvents getRandomVillageEvent() {
        return fixedEvent;
    }
}