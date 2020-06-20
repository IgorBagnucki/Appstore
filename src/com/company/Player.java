package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player extends Programmer {
    private int cash;
    private List<Worker> employedWorkers = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    public Player(
        SeniorityLevel seniorityLevel,
        String firstName,
        String lastName,
        int expectedRemuneration,
        double workAccuracy,
        int maximumDelay,
        TechnologiesWrapper<Integer> knownTechnologies,
        int cash) {
        super(
            seniorityLevel,
            firstName,
            lastName,
            expectedRemuneration,
            workAccuracy,
            maximumDelay,
            knownTechnologies);
        this.cash = cash;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void employWorker(Worker worker) {
        getEmployedWorkers().add(worker);
    }

    public void startProject(Project project) {
        projects.add(project);
    }

    public void testCode(Project project) { }

    public void settleWithAuthorities() { }

    public List<Worker> getEmployedWorkers() {
        return employedWorkers;
    }

    public void fireWorker(Worker workerToFire) {
        employedWorkers.remove(workerToFire);
    }
}
