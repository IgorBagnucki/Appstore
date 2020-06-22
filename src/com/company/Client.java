package com.company;

import java.util.Random;

public class Client extends Human {

    public enum Type{
        CHILLED,
        SERIOUS,
        BASTARD
    }

    private Type type;
    private int timeBeforePayment;
    private boolean requiresFee;

    protected Client(String firstName, String lastName, Type type, int timeBeforePayment) {
        super(firstName, lastName);
        this.type = type;
        this.timeBeforePayment = timeBeforePayment;
        Random random = new Random();
        if(this.type == Type.CHILLED) {
            requiresFee = random.nextInt(100) < 20;
        } else {
            requiresFee = true;
        }
    }

    public boolean acceptsProject(Project project) {
        if(project.hasErrors) {
            if(type == Type.CHILLED) {
                Random random = new Random();
                return random.nextInt(100)<20;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean payForProject(Money price, Player player) {
        player.getPayment(price);
        return true;
    }

    public boolean requiresFee() {
        return requiresFee;
    }

    public int getPaymentDelay() {
        int paymentDelay = timeBeforePayment;
        Random random = new Random();
        switch(type) {
            case CHILLED:
                if(random.nextInt(10) < 3) {
                    paymentDelay += 7;
                }
                break;
            case BASTARD:
                int randomValue = random.nextInt(100);
                if(randomValue < 30) {
                    paymentDelay += 7;
                } else if(randomValue < 35) {
                    paymentDelay += 30;
                } else if(randomValue < 36) {
                    paymentDelay = -1;
                }
                break;
        }
        return paymentDelay;
    }
}
