package com.company;

public class Player extends Programmer {
    public Player(
        SeniorityLevel seniorityLevel,
        String firstName,
        String lastName,
        int expectedRemuneration,
        double workAccuracy,
        int maximumDelay,
        TechnologiesWrapper<Integer> knownTechnologies) {
        super(
            seniorityLevel,
                firstName,
                lastName,
                expectedRemuneration,
                workAccuracy,
                maximumDelay,
                knownTechnologies);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n\nIt's you!";
    }
}
