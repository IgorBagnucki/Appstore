package com.company;

import java.util.ArrayList;
import java.util.List;

public class MenuOption {
    public String description;
    private ArrayList<MenuOptionState> states = new ArrayList<>();
    int stateIndex = 0;

    public MenuOption(String description, List<MenuOptionState> states) {
        this.description = description;
        this.states.addAll(states);
    }

    public MenuOption(String description, String ...states) {
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

    public String toString() {
        MenuOptionState prompt = states.get(stateIndex);
        return String.format("%s: %s", description, prompt.toString());
    }
}
