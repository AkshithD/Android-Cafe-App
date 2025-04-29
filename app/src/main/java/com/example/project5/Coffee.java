package com.example.project5;

import java.text.DecimalFormat;

/**
 * The Coffee class is a subclass of MenuItem
 * @author Joseph Goeller, Akshith Dandemraju
 */
public class Coffee extends MenuItem {

    public String size;
    public boolean sweet;
    public boolean french;
    public boolean irish;
    public boolean caramel;
    public boolean mocha;
    public int quantity;

    //constants
    private static final double SHORT_PRICE = 1.99;
    private static final double ADDED_PER_SIZE = 0.50;
    private static final double ADDED_PER_ADD_IN = 0.30;
    /**
     * Constructs a Coffee object with the specified attributes.
     *
     * @param size     The size of the coffee (Tall, Grande, Venti)
     * @param sweet    Indicates if the coffee is sweet
     * @param french   Indicates if French vanilla is added
     * @param irish    Indicates if Irish cream is added
     * @param caramel  Indicates if caramel is added
     * @param mocha    Indicates if mocha is added
     * @param quantity The quantity of coffee
     */
    public Coffee(String size, boolean sweet, boolean french, boolean irish, boolean caramel, boolean mocha, int quantity){
        this.size=size;
        this.sweet=sweet;
        this.french=french;
        this.irish=irish;
        this.caramel=caramel;
        this.mocha=mocha;
        this.quantity=quantity;
    }
    /**
     * Calculates the price of the coffee item based on its attributes.
     *
     * @return The calculated price of the coffee
     */
    @Override
    public double price(){
        int intSize = 0;
        if(size.equals("Tall")){
            intSize=1;
        }
        if(size.equals("Grande")){
            intSize=2;
        }
        if(size.equals("Venti")){
            intSize=3;
        }
        int addInCount=0;
        if(sweet){
            addInCount++;
        }
        if(french){
            addInCount++;
        }
        if(irish){
            addInCount++;
        }
        if(caramel){
            addInCount++;
        }
        if(mocha){
            addInCount++;
        }
        double preTrunc = (SHORT_PRICE+(ADDED_PER_SIZE*intSize)+(addInCount*ADDED_PER_ADD_IN))*quantity;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(preTrunc));
    }
    /**
     * Generates and returns a detailed description of the coffee item.
     *
     * @return A string containing the coffee item details
     */
    @Override
    public String getDetails(){
        StringBuilder itemDesc = new StringBuilder();
        itemDesc.append("Coffee(").append(quantity).append(") ");
        itemDesc.append(size);
        if(sweet||french||irish||caramel||mocha){
            itemDesc.append(", [");
            if(sweet){
                itemDesc.append("Sweet cream, ");
            }
            if(french){
                itemDesc.append("French vanilla, ");
            }
            if(irish){
                itemDesc.append("Irish cream, ");
            }
            if(caramel){
                itemDesc.append("Caramel, ");
            }
            if(mocha){
                itemDesc.append("Mocha, ");
            }
            itemDesc.setLength(itemDesc.length()-2);
            itemDesc.append("]");
        }
        return itemDesc.toString();
    }
}