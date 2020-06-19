package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private final List<Project> availableProjects = new ArrayList<>();
    private final List<Worker> availableProgrammers = new ArrayList<>();
    private final List<Worker> availableTesters = new ArrayList<>();
    private final List<Worker> availableSellers = new ArrayList<>();
    private final List<Worker> availableColleagues = new ArrayList<>();

    private final Menu mainGameMenu = new Menu();
    private final MenuOption employProgrammer = new MenuOption("Employ a programmer", "");
    private final MenuOption employColleague = new MenuOption("Employ an colleague", "");
    private final MenuOption employTester = new MenuOption("Employ a tester", "");
    private final MenuOption employSeller = new MenuOption("Employ a seller", "");
    private final MenuOption findProject = new MenuOption("Find new project", "");
    private final MenuOption fireWorker = new MenuOption("Fire a worker", "");

    public Game(List<Player> players) {
        this.players = players;
        mainGameMenu.add(employProgrammer);
        mainGameMenu.add(employColleague);
        mainGameMenu.add(employTester);
        mainGameMenu.add(employSeller);
        mainGameMenu.add(findProject);
        mainGameMenu.add(fireWorker);

        availableColleagues.add(ColleagueFactory.get(Colleague.Type.BEST));
        availableColleagues.add(ColleagueFactory.get(Colleague.Type.MID));
        availableColleagues.add(ColleagueFactory.get(Colleague.Type.COCKY));
        for(int i = 0; i < 6; ++i) {
            availableProgrammers.add(ProgrammerGenerator.generate());
            availableTesters.add(TesterGenerator.generate());
            availableSellers.add(SellerGenerator.generate());
        }
    }

    public boolean isGameFinished() {
        return false;
    }

    private Worker workerMenu(List<Worker> list) {
        Menu menu = new Menu();
        for(Worker worker : list) {
            menu.add(new MenuOption(worker.toString(), worker.details()));
        }
        Integer workerIndex = Interface.displayMenu(menu).getValue();
        return list.get(workerIndex);
    }

    public void turn() {
        for(Player currentPlayer : players) {
            MenuOption selectedOption = Interface.displayMenu(mainGameMenu).getKey();
            if(selectedOption == employProgrammer
            || selectedOption == employColleague
            || selectedOption == employTester
            || selectedOption == employSeller) {
                List<Worker> selectedList;
                if(selectedOption == employProgrammer) {
                    selectedList = availableProgrammers;
                } else if(selectedOption == employColleague) {
                    selectedList = availableColleagues;
                } else if(selectedOption == employTester) {
                    selectedList = availableTesters;
                } else {
                    selectedList = availableSellers;
                }
                Worker selectedWorker = workerMenu(selectedList);
                currentPlayer.employWorker(selectedWorker);
            } else if(selectedOption == findProject) {
                System.out.println(4);
            } else if(selectedOption == fireWorker) {
                System.out.println(5);
            }
        }
    }

    public void play() {
        while(!isGameFinished()) {
            turn();
        }
    }
}
