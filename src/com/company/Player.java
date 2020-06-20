package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player extends Programmer {
    private Money cash;
    private List<Worker> employedWorkers = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private int amountOfTesters = 0;
    private int amountOfProgrammers = 0;
    private boolean legalProblems = false;
    private int bigProjectsDoneWithoutPlayerWorking = 0;
    private Money startingMoney = new Money();

    private static final Money hiringCost = new Money(1000);
    private static final Money firingCost = new Money(1000);

    public Player(
        SeniorityLevel seniorityLevel,
        String firstName,
        String lastName,
        int expectedRemuneration,
        double workAccuracy,
        int maximumDelay,
        TechnologiesWrapper<Integer> knownTechnologies,
        Money cash) {
        super(
            seniorityLevel,
            firstName,
            lastName,
            expectedRemuneration,
            workAccuracy,
            maximumDelay,
            knownTechnologies);
        this.cash = cash;
        startingMoney.set(cash);
        employer = this;
    }

    public List<Project> getProjects() {
        return projects;
    }

    boolean isVictorious() {
        return bigProjectsDoneWithoutPlayerWorking >= 3;
    }

    boolean isDefeated() {
        return cash.get() < 0 || legalProblems;
    }

    public void employWorker(Worker worker) {
        getEmployedWorkers().add(worker);
        worker.getEmployer(this);
        if(worker instanceof Programmer) {
            ++amountOfProgrammers;
        } else if(worker instanceof Tester) {
            ++amountOfTesters;
        }
    }

    public void startProject(Project project) {
        projects.add(project);
    }

    public void testCode(Project project) {
        project.hasErrors = false;
    }

    public void settleWithAuthorities() { }

    public List<Worker> getEmployedWorkers() {
        return employedWorkers;
    }

    public void fireWorker(Worker workerToFire) {
        employedWorkers.remove(workerToFire);
    }

    public void getPayment(Money amount) {
        cash.add(amount);
    }

    public Money getCash() {
        return cash;
    }

    public void getEmployeesToWork() {
        for(Worker worker : employedWorkers) {
            worker.doWork();
        }
        if(amountOfTesters * 3 >= amountOfProgrammers) {
            for(Project project : projects) {
                project.hasErrors = false;
            }
        }
    }
}
