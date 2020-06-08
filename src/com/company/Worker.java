package com.company;

abstract public class Worker extends Human {
    private int expectedRemuneration;
    public Worker(String firstName, String lastName, int expectedRemuneration) {
        super(firstName, lastName);
        this.expectedRemuneration = expectedRemuneration;
    }

    public int getExpectedRemuneration() {
        return expectedRemuneration;
    }
}
