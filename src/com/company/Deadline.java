package com.company;

public class Deadline {
    private int daysLeft;
    public Deadline(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    @Override
    public String toString() {
        return daysLeft + " days";
    }
}
