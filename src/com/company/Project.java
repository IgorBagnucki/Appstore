package com.company;

import java.util.Date;
import java.util.function.Function;

public class Project {

    public enum ComplexityLevel {
        EASY,
        MEDIUM,
        COMPLICATED
    }
    public TechnologiesWrapper<Integer> technologyWorkDays;
    public boolean hasErrors = false;
    private String name;
    private Client client;
    private Deadline timeBeforeDeadline;
    private Money feeForMissingDeadline;
    private Money price;
    private ComplexityLevel complexity;
    private int numberOfStages;
    private int stagesCompleted = 0;
    private Money moneyPayed = new Money(0);
    private int stageWorkDays;
    private int nextStageMilestone;
    private boolean hasReadyStage = false;

    public Project(String name, Client client, Deadline timeBeforeDeadline, Money feeForMissingDeadline, Money price, TechnologiesWrapper<Integer> technologyWorkDays, int numberOfStages) {
        this.name = name;
        this.client = client;
        this.timeBeforeDeadline = timeBeforeDeadline;
        this.feeForMissingDeadline = feeForMissingDeadline;
        this.price = price;
        this.technologyWorkDays = technologyWorkDays;
        this.numberOfStages = numberOfStages;
        int requiredTechnologies = technologyWorkDays.countNonZero();
        complexity =
                requiredTechnologies==1 ? ComplexityLevel.EASY :
                requiredTechnologies==2 ? ComplexityLevel.MEDIUM :
                                          ComplexityLevel.COMPLICATED;
        stageWorkDays = technologyWorkDays.addValues() / numberOfStages;
        nextStageMilestone = stageWorkDays;
    }

    public String details() {
        String bugsInProject = "";
        if(hasErrors) {
            bugsInProject = "\nTESTING REQUIRED\n";
        }
        return "client: " + client + "\n"
             + bugsInProject + "\n"
             + "stages: " + numberOfStages + "\n"
             + "time: " + timeBeforeDeadline + "\n"
             + "fee for delay: " + feeForMissingDeadline + "\n"
             + "payment: " + price + "\n\n"
             + "Working days required for technology\n"
             + technologyWorkDays;
    }

    private void registerPayment(Money amount, Player player) {
        int delay = client.getPaymentDelay();
        if(delay < 0) {
            return;
        }
        Function<Date, Boolean> callback = Date -> client.payForProject(amount, player);
        Game.getInstance().registerCallback(callback, client.getPaymentDelay());
    }

    public void updateStageReadiness() {
        if(technologyWorkDays.addValues() > nextStageMilestone) {
            hasReadyStage = true;
            nextStageMilestone += stageWorkDays;
            // prevent rounding errors
            if(nextStageMilestone + stageWorkDays > technologyWorkDays.addValues()) {
                nextStageMilestone = technologyWorkDays.addValues();
            }
        }
    }

    public boolean hasReadyStage() {
        return hasReadyStage;
    }

    public void submitStage(Player submitter) {
        ++stagesCompleted;
        Money toPay;
        if(stagesCompleted == numberOfStages) {
            // this helps with rounding errors
            toPay = new Money(price.get() - moneyPayed.get());
        } else {
            toPay = new Money(price.get() / numberOfStages);
        }
        registerPayment(toPay, submitter);
        moneyPayed.add(toPay);
        hasReadyStage = false;
        if(stagesCompleted == numberOfStages) {
            submitter.removeProject(this);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
