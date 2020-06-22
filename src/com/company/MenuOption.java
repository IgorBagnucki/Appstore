package com.company;

import java.util.ArrayList;
import java.util.List;

public class MenuOption {
    public String description;
    public String details;
    private final ArrayList<MenuOptionState> states = new ArrayList<>();
    int stateIndex = 0;

    public boolean isConfiguration() {
        return states.size() <= 1;
    }

    public MenuOption(String description, String details, List<MenuOptionState> states) {
        this.details = details;
        this.description = description;
        this.states.addAll(states);
    }

    public MenuOption(String description, String details, String ...states) {
        this.details = details;
        this.description = description;
        for(String state : states) {
            this.states.add(new MenuOptionState(state));
        }
    }

    public void next() {
        if(stateIndex < states.size()-1) {
            stateIndex += 1;
        } else {
            stateIndex = 0;
        }
    }

    public void previous() {
        if(stateIndex > 0) {
            stateIndex -= 1;
        } else {
            stateIndex = states.size()-1;
        }
    }

    @Override
    public String toString() {
        String prompt;
        try {
            prompt = states.get(stateIndex).toString();
        } catch (IndexOutOfBoundsException e) {
            return String.format("%s", description);
        }
        return String.format("%s: %s", description, prompt);
    }
}
