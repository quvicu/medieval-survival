package com.viet.survival.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.viet.survival.strategy.FakeVillageEventProvider;
import com.viet.survival.strategy.HighFoodConsumptionStrategy;
import com.viet.survival.strategy.LowFoodConsumptionStrategy;
import com.viet.survival.strategy.RandomVillageEventProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class VillageTest {

    private List<Villager> defaultVillagers;

    private List<Villager> createVillagers(int count) {
        List<Villager> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Farmer(i + 1, "TestFarmer" + (i + 1)));
        }
        return list;
    }

    @BeforeEach
    void setUp() {
        defaultVillagers = createVillagers(1);
    }


    @Test
    void killRandomVillagerWhenAmountBiggerThanExists() {
        VillageEvents testEvent = new VillageEvents("Test-Raid(Kills 3 Villager)", ResourceType.POPULATION, -3,1);
        Village village = new Village(createVillagers(1),100,100,1, new FakeVillageEventProvider(testEvent), new LowFoodConsumptionStrategy());
        village.endDay();
        assertEquals(0, village.getPopulation());
    }

    @RepeatedTest(300)
    void endDayEventChangesCorrectly() {
        VillageEvents testEvent = new VillageEvents("Test-Event",  ResourceType.FOOD, 10, 1);
        Village village = new Village(defaultVillagers,100,100,1, new FakeVillageEventProvider(testEvent), new LowFoodConsumptionStrategy());
        village.endDay();
        assertEquals(100 - 2+ testEvent.getAmount(), village.getFood());
    }

    @RepeatedTest(30)
    void gatherFoodUsesCorrectAmount() {
        Village village = new Village(defaultVillagers,100,20,1);
        village.gatherFood();
        assertTrue(village.getFood() >= 100 && village.getFood() <= 117);
    }

    @RepeatedTest(300)
    void gatherWoodUsesCorrectAmount() {
        Village village = new Village(defaultVillagers,10,100,1);
        village.gatherWood();
        assertTrue(village.getWood() >= 100 && village.getWood() <= 117);
    }

    @RepeatedTest(300)
    void gatherFoodDoesntExceedMaxCapacity() {
        Village village = new Village(defaultVillagers,500,500,1);
        village.gatherFood();
        assertTrue(village.getFood() <= 500);
    }

    @RepeatedTest(300)
    void gatherWoodDoesntExceedMaxCapacity() {
        Village village = new Village(defaultVillagers,100,500,1);
        village.gatherWood();
        assertTrue(village.getWood() <= 500);
    }

    @Test
    void recruitVillagerSucceedsWhenEnoughFood() {
        Village village = new Village(defaultVillagers,20,20,3);
        village.recruitFarmer();
        assertEquals(2,village.getPopulation());
        assertEquals(10, village.getFood());
    }

    @Test
    void recruitVillagerDoesNothingWhenNotEnoughFood() {
        Village village = new Village(defaultVillagers,9,20,3);
        village.recruitFarmer();
        assertEquals(9, village.getFood());
        assertEquals(1, village.getPopulation());
    }

    @Test
    void isEnoughFoodForRecruitmentFalseWhenTen() {
        Village village = new Village(defaultVillagers,10,10,10);
        boolean result = village.isEnoughFoodForRecruitment();
        assertFalse(result);
    }

    @Test
    void isEnoughFoodForRecruitmentTrueWhenEleven() {
        Village village = new Village(defaultVillagers,11,10,10);
        boolean result = village.isEnoughFoodForRecruitment();
        assertTrue(result);
    }

    @Test
    void consumeFood() {
        Village village = new Village(createVillagers(3),20,10,10);
        village.consumeFood();
        assertEquals(14, village.getFood());
    }

    @Test
    void checkConsumeFoodInterfaceDelegation() {
        Village village = new Village(createVillagers(3),30,19, 1, new RandomVillageEventProvider(), new HighFoodConsumptionStrategy());
        village.consumeFood();
        assertEquals(18, village.getFood());
    }

    @Test
    void isDoomedFalseWhenVillageHealthy() {
        Village village = new Village(defaultVillagers,1,1,1);
        boolean result = village.isDoomed();
        assertFalse(result);
    }

    @Test
    void isDoomedTrueWhenIsStarving() {
        Village village = new Village(defaultVillagers,0,10,1);
        boolean result = village.isDoomed();
        assertTrue(result);
    }

    @Test
    void isDoomedTrueWhenIsOutOfWood() {
        Village village = new Village(defaultVillagers,10,0,1);
        boolean result = village.isDoomed();
        assertTrue(result);
    }

    @Test
    void isDoomedTrueWhenIsUnpopulated() {
        Village village = new Village(createVillagers(0),10,10,1);
        boolean result = village.isDoomed();
        assertTrue(result);
    }

    @Test
    void isStarving() {
        Village village = new Village(defaultVillagers, 0, 10, 1);
        boolean result = village.isStarving();
        assertTrue(result);
    }

    @Test
    void isStarvingFalseWhenFoodPositive(){
        Village village = new Village(defaultVillagers, 5, 10, 1);
        boolean result = village.isStarving();
        assertFalse(result);
    }

    @Test
    void isOutOfWood() {
        Village village = new Village(defaultVillagers, 10, 0, 1);
        boolean result = village.isOutOfWood();
        assertTrue(result);
    }

    @Test
    void isOutOfWoodFalseWhenWoodPositive() {
        Village village = new Village(defaultVillagers, 10, 5, 1);
        boolean result = village.isOutOfWood();
        assertFalse(result);
    }

    @Test
    void isOutOfWoodTrueWhenWoodNegative() {
        Village village = new Village(defaultVillagers, 10, -5, -1);
        boolean result = village.isOutOfWood();
        assertTrue(result);
    }

    @Test
    void isUnpopulated() {
        Village village = new Village(createVillagers(0), 10, 10, 1);
        boolean result = village.isUnpopulated();
        assertTrue(result);
    }

    @Test
    void isUnpopulatedFalseWhenPopulationPositive() {
        Village village = new Village(defaultVillagers, 10, 10, -1);
        boolean result = village.isUnpopulated();
        assertFalse(result);
    }

    @Test
    void isUnpopulatedTrueWhenPopulationEmpty() {
        Village village = new Village(createVillagers(0), 10, 10, -1);

        boolean result = village.isUnpopulated();
        assertTrue(result);
    }
}
