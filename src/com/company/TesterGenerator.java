package com.company;

import java.util.Random;

public class TesterGenerator extends HumanGenerator {
    public Tester generate() {
        Random random = new Random();
        int expectedRemuneration = 1500+random.nextInt(5000);
        return new Tester(
                generateFirstName(),
                generateLastName(),
                expectedRemuneration);
    }
}
