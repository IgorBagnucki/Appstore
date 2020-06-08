package com.company;

public class Programmer extends Worker {
    TechnologiesWrapper<Integer> knownTechnologies;
    float workAccuracy;
    int maximumDelay;
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
}
