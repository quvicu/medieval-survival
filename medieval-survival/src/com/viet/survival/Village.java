package com.viet.survival;


public class Village {

    private int food;
    private int wood;
    private int day;

    public Village(int food, int wood, int day) {
        this.food = food;
        this.wood = wood;
        this.day = day;
    }

    public int getFood() {
        return food;
    }
    public void setFood(int food) {
        this.food = food;
    }

    public int getWood() {
        return wood;
    }
    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }

}


