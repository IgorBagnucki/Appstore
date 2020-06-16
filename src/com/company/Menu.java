package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private ArrayList<MenuOption> options = new ArrayList<>();
    private int selectedOptionIndex;
    private Pair<String, String> prompt;

    public Menu() {
        this(new ArrayList<>());
    }

    public Menu(List<MenuOption> options) {
        this(options, new Pair<>(">>", "<<"));
    }

    public Menu(Pair<String, String> prompt) {
        this(new ArrayList<>(), prompt);
    }

    public Menu(List<MenuOption> options, Pair<String, String> prompt) {
        selectedOptionIndex = 0;
        this.prompt = new Pair<>(prompt.getKey(), prompt.getValue());
        this.options.addAll(options);
    }

    @Override
    public String toString() {
        StringBuilder stringedMenu = new StringBuilder();
        for(int optionIndex = 0; optionIndex < options.size(); ++optionIndex) {
            MenuOption option = options.get(optionIndex);
            if(optionIndex != selectedOptionIndex) {
                stringedMenu.append(" ".repeat(Math.max(0, prompt.getKey().length())));
                stringedMenu.append(option.toString());
            } else {
                stringedMenu.append(prompt.getKey());
                stringedMenu.append(option.toString());
                stringedMenu.append(prompt.getValue());
            }
            stringedMenu.append("\n");
        }
        return stringedMenu.toString();
    }

    public void select() {
        options.get(selectedOptionIndex).next();
    }

    public void next(){
        if(selectedOptionIndex < options.size()-1) {
        selectedOptionIndex += 1;
        }
    }

    public void previous() {
        if(selectedOptionIndex > 0) {
            selectedOptionIndex -= 1;
        }
    }

    public void add(MenuOption option) {
        options.add(option);
    }

    public void add(MenuOption option, int index) {
        options.add(index, option);
    }

    public void removeLast() {
        if(options.size() >= 1) {
            options.remove(options.size()-1);
            adjustSelectedOption();
        }
    }

    public void removeFirst() {
        if(options.size() >= 1) {
            options.remove(0);
            adjustSelectedOption();
        }
    }

    public void remove(int index) {
        if(options.size() > index && index >= 0) {
            options.remove(index);
            adjustSelectedOption();
        }
    }

    public void remove(MenuOption option) {
        options.remove(option);
        adjustSelectedOption();
    }

    public void remove(String description) {
        options.removeIf(option -> option.description.equals(description));
    }

    private void adjustSelectedOption() {
        if(selectedOptionIndex >= options.size() && options.size() != 0) {
            selectedOptionIndex = options.size()-1;
        }
    }
}
