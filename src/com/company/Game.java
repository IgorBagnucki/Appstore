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
    private final List<Project> availableProjects   = new ArrayList<>();
    private final List<Worker> availableProgrammers = new ArrayList<>();
    private final List<Worker> availableTesters     = new ArrayList<>();
    private final List<Worker> availableSellers     = new ArrayList<>();
    private final List<Worker> availableColleagues  = new ArrayList<>();

    private static final MenuOption employProgrammer      = new MenuOption("Employ a programmer", "");
    private static final MenuOption employColleague       = new MenuOption("Employ a colleague", "");
    private static final MenuOption employTester          = new MenuOption("Employ a tester", "");
    private static final MenuOption employSeller          = new MenuOption("Employ a seller", "");
    private static final MenuOption findProject           = new MenuOption("Find new project", "");
    private static final MenuOption fireWorker            = new MenuOption("Fire a worker", "");
    private static final MenuOption testCode              = new MenuOption("Test your project", "");
    private static final MenuOption developCode           = new MenuOption("Develop your project", "");
    private static final MenuOption settleWithAuthorities = new MenuOption("Settle with authorities", "");
    private static final MenuOption seeActiveProjects     = new MenuOption("See projects in progress", "");
    private static final MenuOption seeWorkers            = new MenuOption("See your workers", "");
    private static final MenuOption submitProject         = new MenuOption("Submit ready project", "");

    private Game() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY, 1);
        date = calendar.getTime();

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
        boolean noPlayersInGame = true;
        for(Player player : players) {
            if(player.isVictorious()) {
                return true;
            } else if(!player.isDefeated()) {
                noPlayersInGame = false;
            }
        }
        return noPlayersInGame;
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
        Interface.getInstance().setCurrentDate(date);
        for(Player currentPlayer : players) {
            if(!currentPlayer.isDefeated()) {
                playerTurn(currentPlayer);
            }
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
        return showWorkerMenu(list, false);
    }

    private Worker showWorkerMenu(List<Worker> list, boolean showProfession) {
        Menu menu = new Menu();
        for(Worker worker : list) {
            String workerTitle = worker.toString();
            if(showProfession) {
                workerTitle += " - " + worker.getProfessionName();
            }
            menu.add(new MenuOption(workerTitle, worker.details()));
        }
        int workerIndex = Interface.getInstance().displayMenu(menu).getValue();
        return list.get(workerIndex);
    }

    private Project showProjectMenu(List<Project> projects) {
        Menu menu = new Menu();
        for(Project project : projects) {
            String ready = "";
            if(project.hasReadyStage()) {
                ready = " - STAGE COMPLETED";
            }
            menu.add(new MenuOption(project.toString()+ready, project.details()));
        }
        int projectIndex = Interface.getInstance().displayMenu(menu).getValue();
        return projects.get(projectIndex);
    }

    private void playerTurn(Player player) {
        Interface.getInstance().setCurrentPlayer(player);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
            player.getEmployeesToWork();
        }
        while(playerActions(player));
    }

    private boolean playerActions(Player player) {
        boolean turnStillGoing = false;
        // Create and show menu
        Menu mainGameMenu = new Menu();
        if(availableProgrammers.size()        > 0) {mainGameMenu.add(employProgrammer);}
        if(availableColleagues.size()         > 0) {mainGameMenu.add(employColleague);}
        if(availableTesters.size()            > 0) {mainGameMenu.add(employTester);}
        if(availableSellers.size()            > 0) {mainGameMenu.add(employSeller);}
        if(availableProjects.size()           > 0) {mainGameMenu.add(findProject);}
        if(player.getEmployedWorkers().size() > 0) {mainGameMenu.add(fireWorker);}
        if(player.getProjects().size()        > 0) {mainGameMenu.add(testCode);}
        if(player.getProjects().size()        > 0) {mainGameMenu.add(developCode);}
                                                    mainGameMenu.add(settleWithAuthorities);
        if(player.getProjects().size()        > 0) {mainGameMenu.add(seeActiveProjects);}
        if(player.getEmployedWorkers().size() > 0) {mainGameMenu.add(seeWorkers);}
        if(player.hasProjectsWithReadyStage()    ) {mainGameMenu.add(submitProject);}
        MenuOption selectedOption = Interface.getInstance().displayMenu(mainGameMenu).getKey();

        // Do selected action
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
            Worker firedWorker = showWorkerMenu(player.getEmployedWorkers(), true);
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
        } else if(selectedOption == developCode) {
            player.doWork();
        } else if(selectedOption == settleWithAuthorities) {
            player.settleWithAuthorities();
        } else if(selectedOption == seeActiveProjects) {
            turnStillGoing = true;
            showProjectMenu(player.getProjects());
        } else if(selectedOption == seeWorkers) {
            turnStillGoing = true;
            showWorkerMenu(player.getEmployedWorkers(), true);
        } else if(selectedOption == submitProject) {
            Project selectedProject = showProjectMenu(player.getProjectsWithReadyStage());
            selectedProject.submitStage(player);
        }
        return turnStillGoing;
    }
}
