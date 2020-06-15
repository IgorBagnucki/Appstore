package com.company;

import java.util.Random;

public class ClientGenerator extends HumanGenerator{
    public static Client generate() {
        return new Client(
                generateFirstName(),
                generateLastName());
    }

}
