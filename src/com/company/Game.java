package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> Players = new ArrayList<>();
    private List<Project> AvailableProjects = new ArrayList<>();
    private List<Programmer> AvailableProgrammers = new ArrayList<>();
    private List<Tester> AvailableTesters = new ArrayList<>();
    private List<Seller> AvailableSellers = new ArrayList<>();


    public Game() {
        for(int i = 0; i < 6; ++i) {
            AvailableProgrammers.add(ProgrammerGenerator.generate());
            AvailableTesters.add(TesterGenerator.generate());
            AvailableSellers.add(SellerGenerator.generate());
        }
    }
}
