package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();
    private List<Project> availableProjects = new ArrayList<>();
    private List<Programmer> availableProgrammers = new ArrayList<>();
    private List<Tester> availableTesters = new ArrayList<>();
    private List<Seller> availableSellers = new ArrayList<>();

    private Menu mainGameMenu = new Menu();


    public Game() {
        mainGameMenu.add(new MenuOption("asdf"));
        for(int i = 0; i < 6; ++i) {
            availableProgrammers.add(ProgrammerGenerator.generate());
            availableTesters.add(TesterGenerator.generate());
            availableSellers.add(SellerGenerator.generate());
        }
    }

    public void turn() {

    }
}
