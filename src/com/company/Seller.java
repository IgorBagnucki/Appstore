package com.company;

import java.util.Calendar;

public class Seller extends Worker {
    public Seller(String firstName, String lastName, int expectedRemuneration) {
        super(firstName, lastName, expectedRemuneration);
    }

    @Override
    public void doWork() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Game.getInstance().getCurrentDate());
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            Client client = ClientGenerator.generate();
            Game.getInstance().addAvailableProject(ProjectGenerator.generate(client));
        }
    }

    @Override
    public String getProfessionName() {
        return "Seller";
    }
}
