package com.company;

import javax.swing.text.html.Option;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.add(new MenuOption("Music", " ", "x"));
        menu.add(new MenuOption("Taxes", " ", "x"));
        menu.add(new MenuOption("Blobs", " ", "x"));
        menu.add(new MenuOption("Level", "Easy", "Medium", "Hard"));
        try {
            menu.next();
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        }
        System.out.println(menu.toString());
    }
}
