package com.company;

import java.util.Random;

public class ClientGenerator extends HumanGenerator{
    public static Client generate() {
        Random random = new Random();
        Client.Type type;
        int randomValue = random.nextInt(100);
        if(randomValue < 20) {
            type = Client.Type.CHILLED;
        } else if(randomValue < 92) {
            type = Client.Type.SERIOUS;
        } else {
            type = Client.Type.BASTARD;
        }
        return new Client(
                generateFirstName(),
                generateLastName(),
                type,
                random.nextInt(7));
    }
}
