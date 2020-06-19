package com.company;

import javafx.util.Pair;

import java.io.IOException;

public class Interface {
    static Pair<MenuOption, Integer> displayMenu(Menu menu) {
        boolean exit = false;
        while(!exit) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(menu);
            System.out.println(
                    "\n\n"
                  + "next option: j <Enter>\n"
                  + "previous option: k <Enter>\n"
                  + "change selected: l <Enter>\n"
                  + "confirm choice: h <Enter>"
            );
            try {
                int key = System.in.read();
                if(key == 'j') {
                    menu.next();
                } else if(key == 'k') {
                    menu.previous();
                } else if(key == 'l') {
                    if(!menu.selectedOption().isConfiguration()) {
                        return new Pair<>(menu.selectedOption(), menu.index());
                    }
                    menu.select();
                } else if(key == 'h') {
                    exit = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Pair<>(menu.selectedOption(), menu.index());
    }
}
