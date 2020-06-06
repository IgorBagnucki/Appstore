package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private ArrayList<MenuOption> options;
    private int selectedOptionIndex;
    Pair<String, String> prompt;

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
        this.options = new ArrayList<>();
        this.options.addAll(options);
    }

    @Override
    public String toString() {
        StringBuilder stringedMenu = new StringBuilder();
        for(int i = 0; i < options.size(); ++i) {
            MenuOption option = options.get(i);
            if(i != selectedOptionIndex) {
                stringedMenu.append(option.toString());
            } else {
                stringedMenu.append(prompt.getKey());
                stringedMenu.append(option.toString());
                stringedMenu.append(prompt.getValue());
            }
        }
        return stringedMenu.toString();
    }

    public void next() throws InvalidOperationException {
        if(selectedOptionIndex < options.size()-1) {
            selectedOptionIndex += 1;
        } else {
            throw new InvalidOperationException("Next menu option does not exists");
        }
    }

    public void previous() throws InvalidOperationException {
        if(selectedOptionIndex > 0) {
            selectedOptionIndex -= 1;
        } else {
            throw new InvalidOperationException("Previous menu option does not exists");
        }
    }

    public void add(MenuOption option) {
        options.add(option);
    }

    public void add(MenuOption option, int index) {
        options.add(index, option);
    }

    public void removeLast() throws InvalidOperationException {
        if(options.size() >= 1) {
            options.remove(options.size()-1);
            adjustSelectedOption();
        } else {
            throw new InvalidOperationException("Cannot remove from empty list");
        }
    }

    public void removeFirst() throws InvalidOperationException {
        if(options.size() >= 1) {
            options.remove(0);
            adjustSelectedOption();
        } else {
            throw new InvalidOperationException("Cannot remove from empty list");
        }
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        if(options.size() > index && index >= 0) {
            options.remove(index);
            adjustSelectedOption();
        } else {
            throw new IndexOutOfBoundsException("Index is too big or less than 0");
        }
    }

    public void remove(MenuOption option) {
        options.remove(option);
        adjustSelectedOption();
    }

    public void remove(String description) throws InvalidOperationException {
        if(!options.removeIf(option -> option.description.equals(description))) {
            throw new InvalidOperationException("Element was not found");
        }
    }

    private void adjustSelectedOption() {
        if(selectedOptionIndex >= options.size() && options.size() != 0) {
            selectedOptionIndex = options.size()-1;
        }
    }
}
