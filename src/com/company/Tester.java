package com.company;

public class Tester extends Worker {

    public Tester(String firstName, String lastName, int expectedRemuneration) {
        super(firstName, lastName, expectedRemuneration);
    }

    @Override
    public void doWork() { }

    @Override
    public String getProfessionName() {
        return "Tester";
    }

}
