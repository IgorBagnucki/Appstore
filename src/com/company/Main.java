package com.company;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.add(new MenuOption("How many", "one", "two", "three"));
        menu.add(new MenuOption("Music", "[ ]", "[x]"));
        menu.add(new MenuOption("No choice"));
        System.out.println(Interface.displayMenu(menu));
    }
}
