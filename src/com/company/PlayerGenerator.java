package com.company;

public class PlayerGenerator extends HumanGenerator{
    public static Player generate() {
        TechnologiesWrapper<Integer> knownTechnologies = new TechnologiesWrapper<>(0);
        knownTechnologies.setFrontend(100);
        knownTechnologies.setDatabase(100);
        knownTechnologies.setWordpress(100);
        knownTechnologies.setPrestashop(100);

        return new Player(
            Programmer.SeniorityLevel.SENIOR,
            generateFirstName(),
            generateLastName(),
            0,
            1.0,
            0,
            knownTechnologies,
            new Money(2000));
    }
}
