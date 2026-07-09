package com.viet.survival;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();

        game.gatherFood();
        game.gatherFood();
        game.gatherFood();


        System.out.println(game.getVillage().getFood());

    }
}