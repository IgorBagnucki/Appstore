package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ColleagueFactory extends HumanGenerator {
    public static Colleague get(Colleague.Type type) {
        Random random = new Random();
        double workAccuracy;
        int expectedRenumeration;
        int maximumDelay;
        Programmer.SeniorityLevel seniority;
        TechnologiesWrapper<Integer> knownTechnologies = new TechnologiesWrapper<>(0);

        Integer[] technologyIndexes = {0, 1, 2, 3, 4, 5};
        Collections.shuffle(Arrays.asList(technologyIndexes));

        if(type == Colleague.Type.BEST) {
            seniority = Programmer.SeniorityLevel.SENIOR;
            expectedRenumeration = random.nextInt(5000)+80000;
            workAccuracy = 1.0;
            maximumDelay = 0;
            for(int index = 0; index < 6; ++index) {
                knownTechnologies.setAtIndex(
                        technologyIndexes[technologyIndexes[index]],
                        random.nextInt(50)+50);
            }

        } else if(type == Colleague.Type.MID) {
            seniority = Programmer.SeniorityLevel.MID;
            expectedRenumeration = random.nextInt(2000)+40000;
            workAccuracy = .9;
            maximumDelay = 0;
            for(int index = 0; index < 5; ++index) {
                knownTechnologies.setAtIndex(
                        technologyIndexes[technologyIndexes[index]],
                        random.nextInt(50)+50);
            }
        } else {
            seniority = Programmer.SeniorityLevel.MID;
            expectedRenumeration = random.nextInt(3000)+4000;
            workAccuracy = .8;
            maximumDelay = 2;
            for(int index = 0; index < 4; ++index) {
                knownTechnologies.setAtIndex(
                        technologyIndexes[technologyIndexes[index]],
                        random.nextInt(40)+60);
            }
        }
         return new Colleague(
                seniority,
                generateFirstName(),
                generateLastName(),
                expectedRenumeration,
                workAccuracy,
                maximumDelay,
                knownTechnologies,
                type);
    }
}
