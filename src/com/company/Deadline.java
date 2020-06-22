package com.company;

public class Deadline {
    private final int daysLeft;
    public Deadline(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public int getTime() {
        return daysLeft;
    }

    @Override
    public String toString() {
        return daysLeft + " days";
    }
}
