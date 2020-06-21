package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ProjectGenerator {
    public static Project generate(Client client) {
        Random random = new Random();
        TechnologiesWrapper<Integer> technologyWorkDays = generateTechnologyWorkDays();
        return new Project(
                generateProjectName(),
                client,
                new Deadline(technologyWorkDays.addValues() + random.nextInt(3)),
                new Money(random.nextInt(960)+40),
                new Money(technologyWorkDays.addValues() * (random.nextInt(500)+20)),
                technologyWorkDays,
                random.nextInt(5)+1);
    }

    private static TechnologiesWrapper<Integer> generateTechnologyWorkDays() {
        Random random = new Random();
        TechnologiesWrapper<Integer> technologyWorkDays = new TechnologiesWrapper<>(0);
        Integer[] technologyIndexes = {0, 1, 2, 3, 4, 5};
        Collections.shuffle(Arrays.asList(technologyIndexes));
        for(int technologyCounter = 0; technologyCounter < random.nextInt(5)+1; ++technologyCounter) {
            technologyWorkDays.setAtIndex(technologyIndexes[technologyCounter], random.nextInt(4)+1);
        }
        return technologyWorkDays;
    }

    private static String generateProjectName() {
        Random random = new Random();
        String[] noun = {"webpage", "project", "shop", "game", "wiki", "casino", "product", "content", "in-house",
                         "java", "cloud", "python", "C#", "embedded", "server", "desktop", "manual", "automated",
                         "unity", "ios", "media", "sound", "security"};
        String[] verb = {"management", "improvement", "support", "hardening", "pentesting", "testing", "development",
                         "design", "programming", "executive", "translation", "QA"};
        return noun[random.nextInt(noun.length)] + " " + verb[random.nextInt(verb.length)];
    }

}
