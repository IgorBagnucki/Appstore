package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Game {
    private static Game instance;

    private Calendar date = Calendar.getInstance();
    private int turnIndex = 0;

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

    private Game() {
        date.set(2020, Calendar.JANUARY, 1);

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
            availableProjects.add(ProjectGenerator.generate(ClientGenerator.generate()));
        }
    }

    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isGameFinished() {
        return false;
    }

    private Worker showWorkerMenu(List<Worker> list) {
        Menu menu = new Menu();
        for(Worker worker : list) {
            menu.add(new MenuOption(worker.toString(), worker.details()));
        }
        int workerIndex = Interface.displayMenu(menu).getValue();
        return list.get(workerIndex);
    }

    private Project showFindProjectMenu(List<Project> projects) {
        Menu menu = new Menu();
        for(Project project : projects) {
            menu.add(new MenuOption(project.toString(), project.details()));
        }
        int workerIndex = Interface.displayMenu(menu).getValue();
        return projects.get(workerIndex);
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
                Worker selectedWorker = showWorkerMenu(selectedList);
                selectedList.remove(selectedWorker);
                currentPlayer.employWorker(selectedWorker);
            } else if(selectedOption == findProject) {
                Project selectedProject = showFindProjectMenu(availableProjects);
                availableProjects.remove(selectedProject);
                currentPlayer.startProject(selectedProject);
            } else if(selectedOption == fireWorker) {
                Worker firedWorker = showWorkerMenu(currentPlayer.getEmployedWorkers());
                currentPlayer.fireWorker(firedWorker);
                if(firedWorker instanceof Colleague) {
                    availableColleagues.add(firedWorker);
                } else if(firedWorker instanceof Programmer) {
                    availableProgrammers.add(firedWorker);
                } else if(firedWorker instanceof Tester) {
                    availableTesters.add(firedWorker);
                } else if(firedWorker instanceof Seller) {
                    availableSellers.add(firedWorker);
                }
            }
        }
        date.add(Calendar.DATE, 1);
    }

    public void play() {
        if(players.size() <= 0) {
            return;
        }
        while(!isGameFinished()) {
            turn();
        }
    }
}
