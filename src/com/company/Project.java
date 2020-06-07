package com.company;

import java.util.*;

public class Project {
    public enum ComplexityLevel {
        EASY,
        MEDIUM,
        COMPLICATED
    }
    private String name;
    private Technologies<Integer> technologyWorkDays;
    private Human client;
    private Date deadline;
    private Price feeForMissingDeadline;
    private Price price;
    private int daysToWaitBeforePayment;
    private ComplexityLevel complexity;

    public Project(String name, Human client, Date deadline, Price feeForMissingDeadline, Price price, int daysToWaitBeforePayment) {

    }
}
