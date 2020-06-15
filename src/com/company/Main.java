package com.company;

public class Main {

    public static void main(String[] args) {
        Client Zenek = ClientGenerator.generate();
        Project proj = ProjectGenerator.generate(Zenek, 3);
        System.out.println(proj);
    }
}
