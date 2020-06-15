package com.company;

public class Price {
    public int price;
    Price(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return price + "$";
    }
}
