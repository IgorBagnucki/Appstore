package com.company;

import javafx.util.Pair;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Character.toUpperCase;

public class Interface {

    private static Interface instance;
    private Player currentPlayer;
    private Date currentDate;

    public static Interface getInstance() {
        if(instance == null) {
            instance = new Interface();
        }
        return instance;
    }

    public Pair<MenuOption, Integer> displayMenu(Menu menu) {
        boolean exit = false;
        while(!exit) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            System.out.println(
                    calendar.get(Calendar.DATE) + "/"
                  + calendar.get(Calendar.MONTH)+1 + "/"
                  + calendar.get(Calendar.YEAR) + "\n");
            System.out.println(currentPlayer + "\n");
            System.out.println("you have: " + currentPlayer.getCash() + "\n");
            System.out.println(menu);
            if(!menu.selectedOption().details.equals("")) {
                System.out.println(menu.selectedOption().details);
            }
            if(menu.selectedOption().isConfiguration()) {
                System.out.println(
                        "\n\n"
                      + "confirm choice:  H <Enter>\n"
                      + "next option:     J <Enter>\n"
                      + "previous option: K <Enter>\n");
            } else {
                System.out.println(
                        "\n\n"
                      + "confirm choice:  H <Enter>\n"
                      + "next option:     J <Enter>\n"
                      + "previous option: K <Enter>\n"
                      + "change selected: L <Enter>\n");
            }
            boolean correctKeyEntered = false;
            while(!correctKeyEntered) {
                char key = '\0';
                try {
                    key = toUpperCase((char)System.in.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(key == 'H') {
                    correctKeyEntered = true;
                    exit = true;
                } else if(key == 'J') {
                    correctKeyEntered = true;
                    menu.next();
                } else if(key == 'K') {
                    correctKeyEntered = true;
                    menu.previous();
                } else if(key == 'L') {
                    correctKeyEntered = true;
                    if(menu.selectedOption().isConfiguration()) {
                        exit = true;
                    }
                    else {
                        menu.select();
                    }
                }
            }
        }
        return new Pair<>(menu.selectedOption(), menu.index());
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public void setCurrentDate(Date date) {
        currentDate = date;
    }
}
