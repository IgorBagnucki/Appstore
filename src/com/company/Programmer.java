package com.company;

import java.util.Random;

public class Programmer extends Worker {
    public enum SeniorityLevel{
        STUDENT,
        JUNIOR,
        MID,
        SENIOR
    }
    protected TechnologiesWrapper<Integer> knownTechnologies;
    private double workAccuracy;
    private int maximumDelay;
    private int[] technologyIndexesByKnowledge = {0, 1, 2, 3, 4, 5};
    protected SeniorityLevel seniorityLevel;

    public Programmer(
            SeniorityLevel seniorityLevel,
            String firstName,
            String lastName,
            int expectedRemuneration,
            double workAccuracy,
            int maximumDelay,
            TechnologiesWrapper<Integer> knownTechnologies) {
        super(firstName, lastName, expectedRemuneration);
        this.seniorityLevel = seniorityLevel;
        this.workAccuracy = workAccuracy;
        this.maximumDelay = maximumDelay;
        this.knownTechnologies = new TechnologiesWrapper<>(knownTechnologies);
    }

    @Override
    public String details() {
        return seniorityLevel + "\n"
             + "Remuneration: "  + getExpectedRemuneration() + "\n"
             + "Work accuracy: " + getWorkAccuracy() + "\n"
             + "Misses deadlines up to " + getMaximumDelay() + " days\n\n"
             + knownTechnologies;
    }

    @Override
    public void doWork() {
        for(int technologyIndex : technologyIndexesByKnowledge) {
            boolean workDoneForToday = false;
            for(Project project : employer.getProjects()) {
                if(project.technologyWorkDays.getAtIndex(technologyIndex) > 0) {
                    project.technologyWorkDays.setAtIndex(
                            technologyIndex,
                            project.technologyWorkDays.getAtIndex(technologyIndex) - 1);
                    Random random = new Random();
                    if(random.nextInt(100) >= knownTechnologies.getAtIndex(technologyIndex)) {
                        project.hasErrors = true;
                    }
                    workDoneForToday = true;
                    break;
                }
            }
            if(workDoneForToday) {
                break;
            }
        }
    }

    public TechnologiesWrapper<Integer> getKnownTechnologies() {
        return knownTechnologies;
    }

    public double getWorkAccuracy() {
        return workAccuracy;
    }

    public int getMaximumDelay() {
        return maximumDelay;
    }

    private void sortTechnologyIndexesByKnowledge() {
        // Find out how to use Arrays.sort(<Array>, <Lambda>) with elegance
        for(int i = 0; i < technologyIndexesByKnowledge.length; ++i) {
            for(int j = 0; j < technologyIndexesByKnowledge.length-i-1; ++j) {
                if(knownTechnologies.getAtIndex(j) > knownTechnologies.getAtIndex(j+1)) {
                    int tmp = knownTechnologies.getAtIndex(j);
                    knownTechnologies.setAtIndex(j, knownTechnologies.getAtIndex(j+1));
                    knownTechnologies.setAtIndex(j+1, tmp);
                }
            }
        }

    }
}
