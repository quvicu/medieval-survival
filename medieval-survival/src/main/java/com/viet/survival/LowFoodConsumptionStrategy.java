package com.viet.survival;

public class LowFoodConsumptionStrategy implements FoodConsumptionStrategy{

    @Override
    public int getFoodConsumptionFactor() {
        return 2;
    }
}
