package com.example.project5;

/**
 * The CurrentOrder class represents the current order that the user is working on.
 * @author Joseph Goeller, Akshith Dandemraju
 */
final class CurrentOrder {
    private static CurrentOrder currentOrder;
    private Order order;
    private int orderNumber;

    /**
     * Constructs a CurrentOrder object with the specified order number.
     */
    private CurrentOrder(){
        order = new Order(orderNumber);
    }

    /**
     * Gets the current order.
     *
     * @return The current order
     */
    public static synchronized CurrentOrder getCurrent(){
        if(currentOrder==null){
            currentOrder = new CurrentOrder();
        }
        return currentOrder;
    }

    /**
     * Adds a menu item to the current order.
     *
     * @param item The menu item to add
     */
    public void addItemToOrder(MenuItem item) {
        if (item == null) {
            return;
        }
        order.add(item);
    }

    public void removeItemFromOrder(int index) {
        order.remove(index);
    }

    public Order getOrder(){
        return order;
    }

    public void newCurrentOrder(){
        orderNumber++;
        order = new Order(orderNumber);
    }
}
