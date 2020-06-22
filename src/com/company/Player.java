package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Player extends Programmer {
    public boolean legalProblems = false;
    private Money cash;
    private List<Worker> employedWorkers = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private int amountOfTesters = 0;
    private int amountOfProgrammers = 0;
    private int bigProjectsDoneWithoutPlayerWorking = 0;
    private Money startingMoney = new Money();
    private Money taxToPay = new Money();
    public Date lastSettling;

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
        lastSettling = Game.getInstance().getCurrentDate();
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
        cash.subtract(Game.COST_OF_NEW_EMPLOY);
        getEmployedWorkers().add(worker);
        worker.getEmployer(this);
        if(worker instanceof Programmer) {
            ++amountOfProgrammers;
        } else if(worker instanceof Tester) {
            ++amountOfTesters;
        }
    }

    public void startProject(Project project) {
        project.startingDate = Game.getInstance().getCurrentDate();
        projects.add(project);
    }

    public void testCode(Project project) {
        project.hasErrors = false;
    }

    public void settleWithAuthorities() {
        lastSettling = Game.getInstance().getCurrentDate();
    }

    public List<Worker> getEmployedWorkers() {
        return employedWorkers;
    }

    public void fireWorker(Worker workerToFire) {

        employedWorkers.remove(workerToFire);
        cash.subtract(Game.COST_OF_FIRING);
    }

    public void completeProject(Project project) {
        if(project.complexity == Project.ComplexityLevel.COMPLICATED && !project.playerWorked) {
            ++bigProjectsDoneWithoutPlayerWorking;
        }
        projects.remove(project);
    }

    public void getPayment(Money amount) {
        cash.add(amount);
        taxToPay.add(new Money(amount.get() / Game.TAX_PERCENTAGE));
    }

    public Money getCash() {
        return cash;
    }

    public void getEmployeesToWork() {
        for(Worker worker : employedWorkers) {
            worker.doWork();
        }
        if(amountOfTesters * Game.AMOUNT_OF_PROGRAMMERS_PER_TESTER >= amountOfProgrammers) {
            for(Project project : projects) {
                project.hasErrors = false;
            }
        }
    }

    public void payEmployees() {
        for(Worker employee : employedWorkers) {
            cash.subtract(employee.getExpectedRemuneration());
            cash.subtract(Game.MONTHLY_FEE_FOR_EMPLOYEE);
        }
    }

    public List<Project> getProjectsWithReadyStage() {
        List<Project> readyProjects = new ArrayList<>();
        for(Project project : projects) {
            if(project.hasReadyStage()) {
                readyProjects.add(project);
            }
        }
        return readyProjects;
    }

    public boolean hasProjectsWithReadyStage() {
        return getProjectsWithReadyStage().size() != 0;
    }

    public void payTaxes() {
        cash.subtract(taxToPay);
        taxToPay.set(0);
    }

    public void payFeeForMissingDeadline(Money fee) {
        cash.subtract(fee);
        taxToPay.add(new Money(fee.get() / Game.TAX_PERCENTAGE));
    }
}
