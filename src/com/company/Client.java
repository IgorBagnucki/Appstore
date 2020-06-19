package com.company;

public class Client extends Human {
    public enum Type{
        CHILLED,
        SERIOUS,
        BASTARD
    }

    private Type type;
    private int timeBeforePayment;

    protected Client(String firstName, String lastName, Type type, int timeBeforePayment) {
        super(firstName, lastName);
        this.type = type;
        this.timeBeforePayment = timeBeforePayment;
    }
}
