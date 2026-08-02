package com.viet.survival.strategy;

public class LowFoodConsumptionStrategy implements FoodConsumptionStrategy {

    @Override
    public int getFoodConsumptionFactor() {
        return 2;
    }
}
