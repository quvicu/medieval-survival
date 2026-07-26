package com.viet.survival;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VillageTest {

    @Test
    void recruitVillagerSucceedsWhenEnoughFood() {
        Village village = new Village(10,20,20,3);
        village.recruitVillager();
        assertEquals(11,village.getPopulation());
        assertEquals(10, village.getFood());
    }

    @Test
    void recruitVillagerDoesNothingWhenNotEnoughFood() {
        Village village = new Village(10,9,20,3);
        village.recruitVillager();
        assertEquals(9, village.getFood());
        assertEquals(10, village.getPopulation());
    }

    @Test
    void isEnoughFoodForRecruitmentFalseWhenTen() {
        Village village = new Village(10,10,10,10);
        boolean result = village.isEnoughFoodForRecruitment();
        assertFalse(result);
    }

    @Test
    void isEnoughFoodForRecruitmentTrueWhenEleven() {
        Village village = new Village(10,11,10,10);
        boolean result = village.isEnoughFoodForRecruitment();
        assertTrue(result);
    }

    @Test
    void consumeFood() {
        Village village = new Village(3,20,10,10);
        village.consumeFood();
        assertEquals(14, village.getFood());
    }

    @Test
    void isDoomedFalseWhenVillageHealthy() {
        Village village = new Village(1,1,1,1);
        boolean result = village.isDoomed();
        assertFalse(result);
    }

    @Test
    void isDoomedTrueWhenIsStarving() {
        Village village = new Village(10,0,10,1);
        boolean result = village.isDoomed();
        assertTrue(result);
    }

    @Test
    void isDoomedTrueWhenIsOutOfWood() {
        Village village = new Village(10,10,0,1);
        boolean result = village.isDoomed();
        assertTrue(result);
    }

    @Test
    void isDoomedTrueWhenIsUnpopulated() {
        Village village = new Village(0,10,10,1);
        boolean result = village.isDoomed();
        assertTrue(result);
    }

    @Test
    void isStarving() {
        Village village = new Village(1, 0, 10, 1);
        boolean result = village.isStarving();
        assertTrue(result);
    }

    @Test
    void isStarvingFalseWhenFoodPositive(){
        Village village = new Village(1, 5, 10, 1);
        boolean result = village.isStarving();
        assertFalse(result);
    }

    @Test
    void isOutOfWood() {
        Village village = new Village(1, 10, 0, 1);
        boolean result = village.isOutOfWood();
        assertTrue(result);
    }

    @Test
    void isOutOfWoodFalseWhenWoodPositive() {
        Village village = new Village(1, 10, 5, 1);
        boolean result = village.isOutOfWood();
        assertFalse(result);
    }

    @Test
    void isOutOfWoodTrueWhenWoodNegative() {
        Village village = new Village(1, 10, -5, -1);
        boolean result = village.isOutOfWood();
        assertTrue(result);
    }

    @Test
    void isUnpopulated() {
        Village village = new Village(0, 10, 10, 1);
        boolean result = village.isUnpopulated();
        assertTrue(result);
    }

    @Test
    void isUnpopulatedFalseWhenPopulationPositive() {
        Village village = new Village(10, 10, 10, -1);
        boolean result = village.isUnpopulated();
        assertFalse(result);
    }

    @Test
    void isUnpopulatedTrueWhenPopulationNegative() {
        Village village = new Village(-1, 10, 10, -1);
        boolean result = village.isUnpopulated();
        assertTrue(result);
    }
}