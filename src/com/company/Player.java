package com.company;

public class Player extends Programmer {
    private int cash;

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

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n\nIt's you!";
    }
}
