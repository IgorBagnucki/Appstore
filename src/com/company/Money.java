package com.company;

public class Money {
    public int amount;
    Money(int amount) {
        this.amount = amount;
    }

    public Money() {
        this(0);
    }

    public int get() {
        return amount;
    }

    public void set(int value) {
        amount = value;
    }

    public void subtract(Money other) {
        amount -= other.get();
    }

    public void add(Money other) {
        amount += other.get();
    }

    @Override
    public String toString() {
        return amount + "$";
    }

    public void set(Money cash) {
        set(cash.get());
    }
}
