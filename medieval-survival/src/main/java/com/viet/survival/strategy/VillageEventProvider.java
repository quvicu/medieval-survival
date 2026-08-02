package com.viet.survival.strategy;

import com.viet.survival.domain.VillageEvents;

public interface VillageEventProvider {
    VillageEvents getRandomVillageEvent();
}
