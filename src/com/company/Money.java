package com.company;

public class Money {
    public int price;
    Money(int price) {
        this.price = price;
    }

    public Money() {
        this(0);
    }

    public int get() {
        return price;
    }

    public void set(int value) {
        price = value;
    }

    public void subtract(Money other) {
        price -= other.get();
    }

    public void add(Money other) {
        price += other.get();
    }

    @Override
    public String toString() {
        return price + "$";
    }

    public void set(Money cash) {
        set(cash.get());
    }
}
