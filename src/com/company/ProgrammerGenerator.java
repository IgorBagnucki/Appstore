package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ProgrammerGenerator extends HumanGenerator {

    public static Programmer generate(Programmer.SeniorityLevel seniority) {
        double workAccuracy;
        int maximumDelay;
        int expectedRemuneration;
        TechnologiesWrapper<Integer> knownTechnologies = new TechnologiesWrapper<>(0);

        Random random = new Random();
        Integer[] technologyIndexes = {0, 1, 2, 3, 4, 5};
        Collections.shuffle(Arrays.asList(technologyIndexes));

        if(seniority == Programmer.SeniorityLevel.STUDENT) {
            // random value between .6 and .8
            workAccuracy = random.nextDouble()/5 + .6;
            maximumDelay = random.nextInt(15);
            expectedRemuneration = random.nextInt(500)+1200;
            knownTechnologies.setAtIndex(technologyIndexes[0], random.nextInt(65)+10);
            knownTechnologies.setAtIndex(technologyIndexes[1], random.nextInt(30)+10);
        } else if(seniority == Programmer.SeniorityLevel.JUNIOR) {
            // random value between .7 and .8
            workAccuracy = random.nextDouble()/10 + .7;
            maximumDelay = random.nextInt(9);
            expectedRemuneration = random.nextInt(500)+2000;
            knownTechnologies.setAtIndex(technologyIndexes[0], random.nextInt(50)+40);
            knownTechnologies.setAtIndex(technologyIndexes[1], random.nextInt(45)+30);
            knownTechnologies.setAtIndex(technologyIndexes[2], random.nextInt(40)+20);
        } else if(seniority == Programmer.SeniorityLevel.MID) {
            // random value between .8 and .9
            workAccuracy = random.nextDouble()/10 + .8;
            maximumDelay = random.nextInt(4);
            expectedRemuneration = random.nextInt(2000)+4000;
            knownTechnologies.setAtIndex(technologyIndexes[0], random.nextInt(60)+40);
            knownTechnologies.setAtIndex(technologyIndexes[1], random.nextInt(50)+40);
            knownTechnologies.setAtIndex(technologyIndexes[2], random.nextInt(50)+40);
            knownTechnologies.setAtIndex(technologyIndexes[3], random.nextInt(45)+40);
            knownTechnologies.setAtIndex(technologyIndexes[4], random.nextInt(40)+40);
        } else {
            // random value between .9 and 1.0
            workAccuracy = random.nextDouble()/10 + .9;
            maximumDelay = random.nextInt(1);
            expectedRemuneration = random.nextInt(5000)+7000;
            knownTechnologies.setAtIndex(technologyIndexes[0], random.nextInt(50)+50);
            knownTechnologies.setAtIndex(technologyIndexes[1], random.nextInt(47)+50);
            knownTechnologies.setAtIndex(technologyIndexes[2], random.nextInt(44)+50);
            knownTechnologies.setAtIndex(technologyIndexes[3], random.nextInt(41)+50);
            knownTechnologies.setAtIndex(technologyIndexes[4], random.nextInt(38)+50);
            knownTechnologies.setAtIndex(technologyIndexes[5], random.nextInt(35)+50);
        }
        // Round it to two decimal places
        workAccuracy = Math.round(workAccuracy*100.0)/100.0;
        return new Programmer(
                seniority,
                generateFirstName(),
                generateLastName(),
                expectedRemuneration,
                workAccuracy,
                maximumDelay,
                knownTechnologies
        );
    }

    public static Programmer generate() {
        Random random = new Random();
        int seniorityIndex = random.nextInt(Programmer.SeniorityLevel.values().length);
        Programmer.SeniorityLevel seniority = Programmer.SeniorityLevel.values()[seniorityIndex];
        return generate(seniority);
    }
}
