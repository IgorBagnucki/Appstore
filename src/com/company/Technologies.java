package com.company;

public class Technologies<T> {
    public T frontend;
    public T backend;
    public T database;
    public T mobile;
    public T wordpress;
    public T prestashop;

    Technologies(T defaultValue) {
        frontend = defaultValue;
        backend = defaultValue;
        database = defaultValue;
        mobile = defaultValue;
        wordpress = defaultValue;
        prestashop = defaultValue;
    }

    Technologies<T> frontend(T value) {
        frontend = value;
        return this;
    }

    Technologies<T> backend(T value) {
        backend = value;
        return this;
    }

    Technologies<T> database(T value) {
        database = value;
        return this;
    }

    Technologies<T> mobile(T value) {
        mobile = value;
        return this;
    }

    Technologies<T> wordpress(T value) {
        wordpress = value;
        return this;
    }

    Technologies<T> prestashop(T value) {
        prestashop = value;
        return this;
    }
}
