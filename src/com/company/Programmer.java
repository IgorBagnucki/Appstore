package com.company;

import java.util.Random;

public class Programmer extends Worker {
    public enum SeniorityLevel{
        STUDENT,
        JUNIOR,
        MID,
        SENIOR
    }
    private TechnologiesWrapper<Integer> knownTechnologies;
    private double workAccuracy;
    private int maximumDelay;
    private SeniorityLevel seniorityLevel;

    public Programmer(
            SeniorityLevel seniorityLevel,
            String firstName,
            String lastName,
            int expectedRemuneration,
            double workAccuracy,
            int maximumDelay,
            TechnologiesWrapper<Integer> knownTechnologies) {
        super(firstName, lastName, expectedRemuneration);
        this.seniorityLevel = seniorityLevel;
        this.workAccuracy = workAccuracy;
        this.maximumDelay = maximumDelay;
        this.knownTechnologies = new TechnologiesWrapper<>(knownTechnologies);
    }

    @Override
    public String toString() {
        return  firstName + " " + lastName + "\n\n"
                + seniorityLevel + "\n\n"
                + "Remuneration: "  + getExpectedRemuneration() + "\n"
                + "Work accuracy: " + getWorkAccuracy() + "\n"
                + "Misses deadlines up to " + getMaximumDelay() + " days\n\n"
                + knownTechnologies;
    }

    public TechnologiesWrapper<Integer> getKnownTechnologies() {
        return knownTechnologies;
    }

    public double getWorkAccuracy() {
        return workAccuracy;
    }

    public int getMaximumDelay() {
        return maximumDelay;
    }
}
