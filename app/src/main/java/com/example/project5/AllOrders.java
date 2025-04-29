package com.example.project5;

import java.util.ArrayList;

/**
 * The AllOrders class represents all orders that have been placed.
 * @author Joseph Goeller, Akshith Dandemraju
 */
final class AllOrders {
    private static AllOrders instance;
    private ArrayList<Order> orders;

    private AllOrders() {
        orders = new ArrayList<>();
    }

    /**
     * Gets the instance of the AllOrders class.
     *
     * @return The instance of the AllOrders class
     */
    public static synchronized AllOrders getInstance() {
        if (instance == null) {
            instance = new AllOrders();
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}