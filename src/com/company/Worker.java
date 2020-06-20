package com.company;

abstract public class Worker extends Human {
    private int expectedRemuneration;
    protected Player employer;

    public Worker(String firstName, String lastName, int expectedRemuneration) {
        super(firstName, lastName);
        this.expectedRemuneration = expectedRemuneration;
    }

    public String details() {
        return "expected remuneration: " + getExpectedRemuneration();
    }

    public int getExpectedRemuneration() {
        return expectedRemuneration;
    }

    public abstract void doWork();

    protected void getEmployer(Player player) {
        employer = player;
    }
}
