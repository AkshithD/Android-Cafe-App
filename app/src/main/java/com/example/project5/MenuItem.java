package com.example.project5;
/**
 * Abstract class that represents a menu item
 * @author Joseph Goeller, Akshith Dandemraju
 */
public abstract class MenuItem {
    /**
     * Abstract method that returns the price of the menu item
     * @return the price of the menu item
     */
    public abstract double price();
    /**
     * Abstract method that returns the details of the menu item
     * @return the details of the menu item
     */
    public abstract String getDetails();
}

