package com.company;

import javafx.util.Pair;

import java.io.IOException;

import static java.lang.Character.toUpperCase;

public class Interface {
    static Pair<MenuOption, Integer> displayMenu(Menu menu) {
        boolean exit = false;
        while(!exit) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(menu);
            if(!menu.selectedOption().details.equals("")) {
                System.out.println(menu.selectedOption().details);
            }
            if(menu.selectedOption().isConfiguration()) {
                System.out.println(
                        "\n\n"
                      + "next option:     J <Enter>\n"
                      + "previous option: K <Enter>\n"
                      + "confirm choice:  L <Enter>\n"
                );
            } else {
                System.out.println(
                        "\n\n"
                      + "confirm choice:  H <Enter>\n"
                      + "next option:     J <Enter>\n"
                      + "previous option: K <Enter>\n"
                      + "change selected: L <Enter>\n"
                );
            }
            try {
                char key = toUpperCase((char)System.in.read());
                if(key == 'H') {
                    exit = true;
                } else if(key == 'J') {
                    menu.next();
                } else if(key == 'K') {
                    menu.previous();
                } else if(key == 'L') {
                    if(menu.selectedOption().isConfiguration()) {
                        exit = true;
                    }
                    else {
                        menu.select();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Pair<>(menu.selectedOption(), menu.index());
    }
}
