package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        List<Player> players = new ArrayList<>();
        players.add(
                new Player(
                        Programmer.SeniorityLevel.SENIOR,
                        "Igor",
                        "Bagnucki",
                        10000,
                        .99,
                        0,
                        new TechnologiesWrapper<>(100),
                        new Money(150)));
//        InputStreamReader in= new InputStreamReader(System.in);
//        BufferedReader input = new BufferedReader(in);
//        TechnologiesWrapper<Integer> knownTechnologies = new TechnologiesWrapper<>(0);
//        knownTechnologies.database = 100;
//        knownTechnologies.frontend = 100;
//        knownTechnologies.wordpress = 100;
//        knownTechnologies.prestashop = 100;
//        int numberOfPlayers;
//
//        System.out.println("How many players are playing? ");
//        numberOfPlayers = Integer.parseInt(input.readLine());
//        for(int iterator = 0; iterator < numberOfPlayers; ++iterator) {
//            System.out.println("Player " + iterator + " first name");
//            String firstName = input.readLine();
//            System.out.println("Player " + iterator + " last name");
//            String lastName = input.readLine();
//            players.add(new Player(
//                    Programmer.SeniorityLevel.SENIOR,
//                    firstName,
//                    lastName,
//                    0,
//                    .99,
//                    0,
//                    knownTechnologies,
//                    new Money(150)));
//        }
        Game game = Game.getInstance();
        game.setPlayers(players);
        game.play();
        for(Player player : players) {
            if(player.isDefeated()) {
                if(player.legalProblems) {
                    System.out.println(player + " is defeated with " + player.getCash() + " and legal problems");
                } else {
                    System.out.println(player + " is defeated with " + player.getCash());
                }
            } else if(player.isVictorious()) {
                System.out.println(player + " won with " + player.getCash());
            } else {
                System.out.println(player + "is not defeated nor victorious with " + player.getCash());
            }
        }
    }
}
