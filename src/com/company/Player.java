package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Player extends Programmer {
    private int cash;
    private List<Worker> employedWorkers = new ArrayList<>();

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

    public void employWorker(Worker worker) {

    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n\nIt's you!";
    }
}
