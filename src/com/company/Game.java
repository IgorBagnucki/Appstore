package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class Game {
    private static Game instance;

    private int turnIndex = 0;
    private Date date;
    private final List<Pair<Date, Function<Date, Boolean>>> registeredCallbacks = new ArrayList<>();

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
    private final MenuOption testCode = new MenuOption("Test your project", "");
    private final MenuOption settleWithAuthorities = new MenuOption("Settle with authorities", "");

    private Game() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY, 1);
        date = calendar.getTime();
        mainGameMenu.add(employProgrammer);
        mainGameMenu.add(employColleague);
        mainGameMenu.add(employTester);
        mainGameMenu.add(employSeller);
        mainGameMenu.add(findProject);
        mainGameMenu.add(fireWorker);
        mainGameMenu.add(testCode);
        mainGameMenu.add(settleWithAuthorities);

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

    public void addAvailableProject(Project project) {
        availableProjects.add(project);
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isGameFinished() {
        return false;
    }

    public void registerCallback(Function<Date, Boolean> callback, int delay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, delay);
        registeredCallbacks.add(new Pair<>(calendar.getTime(), callback));
    }

    private void callCallbacks() {
        for(Pair<Date, Function<Date, Boolean>> callback : registeredCallbacks) {
            if(callback.getKey().compareTo(date) == 0) {
                callback.getValue().apply(date);
                registeredCallbacks.remove(callback);
            }
        }
    }

    public void turn() {
        for(Player currentPlayer : players) {
            playerTurn(currentPlayer);
        }
        callCallbacks();
        turnIndex += 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();
    }

    public void play() {
        if(players.size() <= 0) {
            return;
        }
        while(!isGameFinished()) {
            turn();
        }
    }

    public Date getCurrentDate() {
        return date;
    }

    private Worker showWorkerMenu(List<Worker> list) {
        Menu menu = new Menu();
        for(Worker worker : list) {
            menu.add(new MenuOption(worker.toString(), worker.details()));
        }
        int workerIndex = Interface.getInstance().displayMenu(menu).getValue();
        return list.get(workerIndex);
    }

    private Project showProjectMenu(List<Project> projects) {
        Menu menu = new Menu();
        for(Project project : projects) {
            menu.add(new MenuOption(project.toString(), project.details()));
        }
        int projectIndex = Interface.getInstance().displayMenu(menu).getValue();
        return projects.get(projectIndex);
    }

    private void playerTurn(Player player) {
        playerActions(player);
    }

    private void playerActions(Player player) {
        MenuOption selectedOption = Interface.getInstance().displayMenu(mainGameMenu).getKey();
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
            player.employWorker(selectedWorker);
        } else if(selectedOption == findProject) {
            Project selectedProject = showProjectMenu(availableProjects);
            availableProjects.remove(selectedProject);
            player.startProject(selectedProject);
        } else if(selectedOption == fireWorker) {
            Worker firedWorker = showWorkerMenu(player.getEmployedWorkers());
            player.fireWorker(firedWorker);
            if(firedWorker instanceof Colleague) {
                availableColleagues.add(firedWorker);
            } else if(firedWorker instanceof Programmer) {
                availableProgrammers.add(firedWorker);
            } else if(firedWorker instanceof Tester) {
                availableTesters.add(firedWorker);
            } else if(firedWorker instanceof Seller) {
                availableSellers.add(firedWorker);
            }
        } else if(selectedOption == testCode) {
            Project selectedProject = showProjectMenu(player.getProjects());
            player.testCode(selectedProject);
        } else if(selectedOption == settleWithAuthorities) {
            player.settleWithAuthorities();
        }
    }
}
