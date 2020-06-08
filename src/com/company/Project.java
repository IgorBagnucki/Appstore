package com.company;

import java.util.*;

public class Project {
    public enum ComplexityLevel {
        EASY,
        MEDIUM,
        COMPLICATED
    }
    private String name;
    private TechnologiesWrapper<Integer> technologyWorkDays;
    private Human client;
    private Date deadline;
    private Price feeForMissingDeadline;
    private Price price;
    private int daysToWaitBeforePayment;
    private ComplexityLevel complexity;

    public Project(String name, Human client, Date deadline, Price feeForMissingDeadline, Price price, int daysToWaitBeforePayment, TechnologiesWrapper<Integer> technologyWorkDays) {
        this.name = name;
        this.client = client;
        this.deadline = deadline;
        this.feeForMissingDeadline = feeForMissingDeadline;
        this.price = price;
        this.daysToWaitBeforePayment = daysToWaitBeforePayment;
        this.technologyWorkDays = technologyWorkDays;
        int requiredTechnologies = technologyWorkDays.countNonZero();
        complexity =
                requiredTechnologies==1 ? ComplexityLevel.EASY :
                requiredTechnologies==2 ? ComplexityLevel.MEDIUM :
                                          ComplexityLevel.COMPLICATED;
    }
}
