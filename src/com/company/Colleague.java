package com.company;

public class Colleague extends Programmer {
    public enum Type{
        BEST,
        MID,
        COCKY
    }
    private Type type;
    public Colleague(
            SeniorityLevel seniorityLevel,
            String firstName,
            String lastName,
            int expectedRemuneration,
            double workAccuracy,
            int maximumDelay,
            TechnologiesWrapper<Integer> knownTechnologies,
            Type type) {
        super(
                seniorityLevel,
                firstName,
                lastName,
                expectedRemuneration,
                workAccuracy,
                maximumDelay,
                knownTechnologies);
        this.type = type;
    }

    @Override
    public String details() {
        String value;
        if(type == Type.BEST) {
            value = "He is the most expensive, but makes no mistakes and doesn't miss deadlines";
        } else if(type == Type.MID) {
            value = "He doesn't miss deadlines, but has 10% chance to make errors";
        } else {
            value = "Cheapest, 20% chance he'll be late and 20% chance he'll make errors";
        }
        return seniorityLevel + "\n"
                + "Remuneration: "  + getExpectedRemuneration() + "\n"
                + "Work accuracy: " + getWorkAccuracy() + "\n"
                + "Misses deadlines up to " + getMaximumDelay() + " days\n\n"
                + knownTechnologies + "\n\n"
                + value;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
