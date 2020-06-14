package com.company;

import java.util.Random;

public class SellerGenerator extends HumanGenerator{
    public static Seller generate() {
        Random random = new Random();
        int expectedRemuneration = 1500+random.nextInt(5000);
        return new Seller(
                generateFirstName(),
                generateLastName(),
                expectedRemuneration);
    }
}
