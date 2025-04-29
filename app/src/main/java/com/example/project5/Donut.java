package com.example.project5;

import java.io.Serializable;
import java.text.DecimalFormat;
/**
 * The Donut class represents a type of MenuItem, specifically a donut item.
 * It includes attributes such as name, type (yeast, cake, hole), and quantity.
 * The price of the donut is calculated based on its type and quantity.
 * @author Joseph Goeller, Akshith Dandemraju
 */
public class Donut extends MenuItem implements Serializable {
    public String name;
    public int type;
    public int quantity;
    public int img;

    //constants
    private static final int YEAST = 0;
    private static final int CAKE = 1;
    private static final int HOLE = 2;
    private static final double YEAST_PRICE = 1.79;
    private static final double CAKE_PRICE = 1.89;
    private static final double HOLE_PRICE = 0.39;
    /**
     * Constructs a Donut object with the specified type, name, and quantity.
     *
     * @param type     The type of donut (0 for yeast, 1 for cake, 2 for hole)
     * @param name     The name of the donut
     * @param quantity The quantity of donuts
     */
    public Donut(int type, String name, int quantity, int img){
        this.type=type;
        this.name=name;
        this.quantity=quantity;
        this.img=img;
    }
    /**
     * Calculates the price of the donut based on its type and quantity.
     *
     * @return The calculated price of the donut
     */
    @Override
    public double price(){
        double preTrunc = HOLE_PRICE*quantity;
        if(type==YEAST){
            preTrunc = YEAST_PRICE*quantity;
        }
        else if(type==CAKE){
            preTrunc = CAKE_PRICE*quantity;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(preTrunc));
    }
    /**
     * Generates and returns a brief description of the donut item.
     *
     * @return A string containing the donut name and quantity
     */
    @Override
    public String getDetails(){
        StringBuilder itemDesc = new StringBuilder();
        itemDesc.append(name).append("(").append(quantity).append(")");
        return itemDesc.toString();
    }
    /**
     * Returns a string representation of the donut item.
     *
     * @return A string containing the donut details
     */
    @Override
    public String toString(){
        return getDetails();
    }

    public int getImage(){
        return img;
    }

    public String getItemName(){
        return name;
    }

    public void setQuantity(int newQuantity){
        this.quantity = newQuantity;
    }
}