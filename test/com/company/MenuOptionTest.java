package com.company;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MenuOptionTest {
    @Test
    public void create() {
        MenuOption optionChecked = new MenuOption("asdf", true);
        MenuOption optionUnchecked = new MenuOption("asdf", false);
        Assert.assertTrue(optionChecked.isChecked());
        Assert.assertFalse(optionUnchecked.isChecked());
    }

    @Test
    public void flip() {
        MenuOption option = new MenuOption("asdf", true);
        Assert.assertTrue(option.isChecked());
        option.flip();
        Assert.assertFalse(option.isChecked());
        option.flip();
        Assert.assertTrue(option.isChecked());
    }

    @Test
    public void check() {
        MenuOption optionChecked = new MenuOption("asdf", true);
        MenuOption optionUnchecked = new MenuOption("asdf", false);
        optionChecked.check();
        optionUnchecked.check();
        Assert.assertTrue(optionChecked.isChecked());
        Assert.assertTrue(optionUnchecked.isChecked());
    }

    @Test
    public void uncheck() {
        MenuOption optionChecked = new MenuOption("asdf", true);
        MenuOption optionUnchecked = new MenuOption("asdf", false);
        optionChecked.uncheck();
        optionUnchecked.uncheck();
        Assert.assertFalse(optionChecked.isChecked());
        Assert.assertFalse(optionUnchecked.isChecked());
    }
}