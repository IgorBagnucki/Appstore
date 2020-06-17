package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players = new ArrayList<>();
    private final List<Project> availableProjects = new ArrayList<>();
    private final List<Programmer> availableProgrammers = new ArrayList<>();
    private final List<Tester> availableTesters = new ArrayList<>();
    private final List<Seller> availableSellers = new ArrayList<>();

    private final Menu mainGameMenu = new Menu();
    private final MenuOption employProgrammer = new MenuOption("Employ a programmer");
    private final MenuOption employColleague = new MenuOption("Employ an colleague");
    private final MenuOption employTester = new MenuOption("Employ a tester");
    private final MenuOption employSeller = new MenuOption("Employ a seller");
    private final MenuOption findProject = new MenuOption("Find new project");
    private final MenuOption fireWorker = new MenuOption("Fire a worker");

    public Game() {
        mainGameMenu.add(employProgrammer);
        mainGameMenu.add(employColleague);
        mainGameMenu.add(employTester);
        mainGameMenu.add(employSeller);
        mainGameMenu.add(findProject);
        mainGameMenu.add(fireWorker);
        for(int i = 0; i < 6; ++i) {
            availableProgrammers.add(ProgrammerGenerator.generate());
            availableTesters.add(TesterGenerator.generate());
            availableSellers.add(SellerGenerator.generate());
        }
    }

    public boolean turn() {
        MenuOption selectedOption = Interface.displayMenu(mainGameMenu);
        if(selectedOption == employProgrammer) {
            System.out.println(0);
        } else if(selectedOption == employColleague) {
            System.out.println(1);
        } else if(selectedOption == employTester) {
            System.out.println(2);
        } else if(selectedOption == employSeller) {
            System.out.println(3);
        } else if(selectedOption == findProject) {
            System.out.println(4);
        } else if(selectedOption == fireWorker) {
            System.out.println(5);
        }
        return true;
    }
}
