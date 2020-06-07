package com.company;

import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

public class Project {
    public enum ComplexityLevel {
        EASY,
        MEDIUM,
        COMPLICATED
    }
    private String name;
    private Dictionary<String, Integer> technologyWorkdays = new Hashtable<>();
    private Human client;
    private Date deadline;
    private Price feeForMissingDeadline;
    private Price price;
    private int daysBeforePayment;
    private ComplexityLevel complexity;
}
