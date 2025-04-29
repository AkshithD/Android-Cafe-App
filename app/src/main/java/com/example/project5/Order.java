package com.example.project5;

import java.util.ArrayList;

/**
 * The Order class represents an order that contains a list of menu items.
 * It provides methods for adding and removing items, calculating subtotal, and accessing order information.
 * @author Joseph Goeller, Akshith Dandemraju
 */
public class Order{
    private int orderNumber;
    private ArrayList<MenuItem> items;
    /**
     * Constructs an Order object with the specified order number.
     *
     * @param orderNumber The order number
     */
    public Order(int orderNumber){
        this.orderNumber= orderNumber;
        this.items = new ArrayList<MenuItem>();
    }
    /**
     * Adds a new menu item to the order.
     *
     * @param newItem The menu item to add
     */
    public void add(MenuItem newItem){
        items.add(newItem);
    }
    /**
     * Removes the menu item at the specified index from the order.
     *
     * @param removeIndex The index of the menu item to remove
     */
    public void remove(int removeIndex){
        items.remove(removeIndex);
    }
    /**
     * Gets the order number.
     *
     * @return The order number
     */
    public int getOrderNumber(){
        return orderNumber;
    }
    /**
     * Gets the list of menu items in the order.
     *
     * @return The list of menu items
     */
    public ArrayList<MenuItem> getItems(){
        return items;
    }
    /**
     * Calculates and returns the subtotal of the order based on the prices of menu items.
     *
     * @return The subtotal of the order
     */
    public double getSubtotal(){
        double total=0;
        for(int i=0; i<items.size(); i++){
            total+=items.get(i).price();
        }
        return total;
    }
}