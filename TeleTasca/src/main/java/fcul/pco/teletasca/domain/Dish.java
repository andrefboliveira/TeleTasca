// -*- coding: utf-8 -*-
package fcul.pco.teletasca.domain;

/**
 * This class represents a restaurant dish that can be ordered by a client.
 * 
 * @author Thibault Langlois 
 * Alunos: 
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
public class Dish {
   
    private static int counter = 1;
    
    private int id;
	private String name;
	private double price;
	

	/**
     * Initialize a Dish instance
     * 
     * @param name the description of the dish
     * @param price the dish price
     */
        
    public Dish(String name, double price) {
    	this(counter, name, price);
    	counter++;
    }
    
    private Dish(int id, String name, double price) {
    	this.id = id;
    	this.name = name;
    	this.price = price;
    }
    
    /**
     * 
     * @return the dish Id 
     */
    public int getId()  {
        return this.id;
    }
    
    /**
     * 
     * @return the dish description 
     */
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        // TODO
        return "";
    }
    
    /**
     * Creates a Dish instance from a string.
     * 
     * @param s a string that describes a dish
     * @return a Dish instance
     * @requires s is a string that contains the Id, the name of a dish and its price.
     * separated by a comma (,). The string must contain exactly 
     * two commas.
     * @ensures the returned value d is such that d.getName() is equal to the 
     * is equal to the name specified in s.
     * 
     */
    public static Dish fromString(String s) {
        String[] stringlist = s.split(",");
        int dishId = Integer.parseInt(stringlist[0].trim());
        String dishName = stringlist[1].trim();
        double dishPrice = Double.parseDouble(stringlist[2].trim());
        
        return new Dish(dishId, dishName, dishPrice);
    }
    
    

}