package com.company;

public class Programmer extends Worker {
    private TechnologiesWrapper<Integer> knownTechnologies;
    private float workAccuracy;
    private int maximumDelay;
    public Programmer(
            String firstName,
            String lastName,
            int expectedRemuneration,
            float workAccuracy,
            int maximumDelay,
            TechnologiesWrapper<Integer> knownTechnologies) {
        super(firstName, lastName, expectedRemuneration);
        this.knownTechnologies = new TechnologiesWrapper<>(knownTechnologies);
    }

    public TechnologiesWrapper<Integer> getKnownTechnologies() {
        return knownTechnologies;
    }

    public float getWorkAccuracy() {
        return workAccuracy;
    }

    public int getMaximumDelay() {
        return maximumDelay;
    }
}
