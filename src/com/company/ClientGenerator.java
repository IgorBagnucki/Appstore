package com.company;

public class ClientGenerator extends HumanGenerator{
    public static Client generate() {
        return new Client(
                generateFirstName(),
                generateLastName());
    }
}
