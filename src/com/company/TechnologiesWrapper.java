package com.company;

public class TechnologiesWrapper<T> {
    public T frontend;
    public T backend;
    public T database;
    public T mobile;
    public T wordpress;
    public T prestashop;

    public TechnologiesWrapper(T defaultValue) {
        frontend = defaultValue;
        backend = defaultValue;
        database = defaultValue;
        mobile = defaultValue;
        wordpress = defaultValue;
        prestashop = defaultValue;
    }

    public TechnologiesWrapper(TechnologiesWrapper<T> from) {
        frontend = from.frontend;
        backend = from.backend;
        database = from.database;
        mobile = from.mobile;
        wordpress = from.wordpress;
        prestashop = from.prestashop;
    }

    TechnologiesWrapper<T> frontend(T value) {
        frontend = value;
        return this;
    }

    TechnologiesWrapper<T> backend(T value) {
        backend = value;
        return this;
    }

    TechnologiesWrapper<T> database(T value) {
        database = value;
        return this;
    }

    TechnologiesWrapper<T> mobile(T value) {
        mobile = value;
        return this;
    }

    TechnologiesWrapper<T> wordpress(T value) {
        wordpress = value;
        return this;
    }

    TechnologiesWrapper<T> prestashop(T value) {
        prestashop = value;
        return this;
    }

    int addValues() {
        return (int)frontend + (int)backend + (int)database + (int)mobile + (int)wordpress + (int)prestashop;
    }

    int countNonZero() {
        return (int)frontend==0      ? 0 : 1
                + (int)backend==0    ? 0 : 1
                + (int)database==0   ? 0 : 1
                + (int)mobile==0     ? 0 : 1
                + (int)wordpress==0  ? 0 : 1
                + (int)prestashop==0 ? 0 : 1;
    }
}
