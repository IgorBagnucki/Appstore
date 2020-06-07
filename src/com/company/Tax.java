package com.company;

public class Tax {
    private int percentage;
    public Tax(int percentage) {
        changeTax(percentage);
    }

    public void changeTax(int newPercentage) {
        if(newPercentage >= 0) {
            percentage = newPercentage;
        }
    }

    public int getPercentage() {
        return percentage;
    }

    public int calculateTax(int value) {
        return value * (percentage/100);
    }
}
