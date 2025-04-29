package com.example.project5;

import java.text.DecimalFormat;
/**
 * The Sandwich class represents a sandwich menu item.
 * @author Joseph Goeller, Akshith Dandemraju
 */
public class Sandwich extends MenuItem {

    public String type;
    public String bread;
    public boolean cheese;
    public boolean tomatoes;
    public boolean lettuce;
    public boolean onion;


    //constants
    private static final double BEEF_PRICE = 10.99;
    private static final double CHICKEN_PRICE = 8.99;
    private static final double FISH_PRICE = 9.99;
    private static final double VEGGIE_PRICE = 0.30;
    private static final double CHEESE_PRICE = 1.00;

    /**
     * Constructs a Sandwich object with the specified type, bread, and add-ins.
     *
     * @param type The type of sandwich
     * @param bread The type of bread
     * @param cheese Whether the sandwich has cheese
     * @param tomatoes Whether the sandwich has tomatoes
     * @param lettuce Whether the sandwich has lettuce
     * @param onion Whether the sandwich has onion
     */
    public Sandwich(String type, String bread, boolean cheese, boolean tomatoes, boolean lettuce, boolean onion){
        this.type=type;
        this.bread=bread;
        this.cheese=cheese;
        this.tomatoes=tomatoes;
        this.onion=onion;
        this.lettuce=lettuce;
    }
    /**
     * Calculates and returns the price of the sandwich based on its type and add-ins.
     *
     * @return The price of the sandwich
     */
    @Override
    public double price(){
        double total=0;
        if(type.equals("Beef")){
            total+=BEEF_PRICE;
        }
        else if(type.equals("Chicken")){
            total+=CHICKEN_PRICE;
        }
        else{
            total+=FISH_PRICE;
        }
        if(cheese){
            total+=CHEESE_PRICE;
        }
        int veggieAddIn=0;
        if(lettuce){
            veggieAddIn++;
        }
        if(tomatoes){
            veggieAddIn++;
        }
        if(onion){
            veggieAddIn++;
        }
        total+=(veggieAddIn*VEGGIE_PRICE);
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(total));
    }
    /**
     * Returns a string representation of the sandwich details.
     *
     * @return The sandwich details string
     */
    @Override
    public String getDetails(){
        StringBuilder itemDesc = new StringBuilder();
        itemDesc.append("Sandwich(1) [");
        itemDesc.append(type).append(", ");
        itemDesc.append(bread);
        if(cheese||tomatoes||onion||lettuce){
            itemDesc.append(", [");
            if(cheese){
                itemDesc.append("Cheese, ");
            }
            if(tomatoes){
                itemDesc.append("Tomatoes, ");
            }
            if(onion){
                itemDesc.append("Onion, ");
            }
            if(lettuce){
                itemDesc.append("Lettuce, ");
            }
            itemDesc.setLength(itemDesc.length()-2);
            itemDesc.append("]");
        }
        itemDesc.append("]");
        return itemDesc.toString();
    }

}