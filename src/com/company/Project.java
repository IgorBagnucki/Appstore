package com.company;

public class Project {

    public enum ComplexityLevel {
        EASY,
        MEDIUM,
        COMPLICATED
    }
    private String name;
    private TechnologiesWrapper<Integer> technologyWorkDays;
    private Human client;
    private Deadline timeBeforeDeadline;
    private Price feeForMissingDeadline;
    private Price price;
    private ComplexityLevel complexity;

    public Project(String name, Human client, Deadline timeBeforeDeadline, Price feeForMissingDeadline, Price price, TechnologiesWrapper<Integer> technologyWorkDays) {
        this.name = name;
        this.client = client;
        this.timeBeforeDeadline = timeBeforeDeadline;
        this.feeForMissingDeadline = feeForMissingDeadline;
        this.price = price;
        this.technologyWorkDays = technologyWorkDays;
        int requiredTechnologies = technologyWorkDays.countNonZero();
        complexity =
                requiredTechnologies==1 ? ComplexityLevel.EASY :
                requiredTechnologies==2 ? ComplexityLevel.MEDIUM :
                                          ComplexityLevel.COMPLICATED;
    }

    public String details() {
        return client
             + "time: " + timeBeforeDeadline + "\n"
             + "fee for delay: " + feeForMissingDeadline + "\n"
             + "payment: " + price + "\n\n"
             + "Working days required for technology:"
             + technologyWorkDays;
    }

    @Override
    public String toString() {
        return name;
    }
}
