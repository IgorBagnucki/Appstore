package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(
                new Player(
                        Programmer.SeniorityLevel.SENIOR,
                        "Igor",
                        "Bagnucki",
                        10000,
                        .99,
                        0,
                        new TechnologiesWrapper<Integer>(100),
                        15000));
        Game game = new Game(players);
        game.play();
    }
}
