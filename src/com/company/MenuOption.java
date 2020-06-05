package com.company;

public class MenuOption {
    public String description;
    private boolean checked;

    public MenuOption(String description) {
        this(description, false);
    }

    public MenuOption(String description, boolean checked) {
        this.description = description;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void check() {
        checked = true;
    }

    public void uncheck() {
        checked = false;
    }

    public void flip() {
        checked = !checked;
    }

    public String toString() {
        char prompt = checked ? 'x' : ' ';
        return String.format("%s [%c]", description, prompt);
    }
}
