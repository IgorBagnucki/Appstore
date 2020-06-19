package com.company;

abstract public class Human {
    public String firstName;
    public String lastName;

    protected Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
