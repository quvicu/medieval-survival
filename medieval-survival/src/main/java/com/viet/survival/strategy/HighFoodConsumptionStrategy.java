package com.viet.survival.strategy;

public class HighFoodConsumptionStrategy implements FoodConsumptionStrategy {
    @Override
    public int getFoodConsumptionFactor() {
        return 4;
    }
}
