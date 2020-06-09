package com.company;

import java.util.Random;

abstract public class Human {
    public String firstName;
    public String lastName;

    protected Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
