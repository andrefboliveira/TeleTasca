// -*- coding: utf-8 -*-
package fcul.pco.teletasca.domain;

/**
 * This class represents a restaurant dish that can be ordered by a client.
 * 
 * @author Thibault Langlois
 *
 */
public class Dish {
   
    private static int id = 0;
	private String name;
	private double price;

	/**
     * Initialize a Dish instance
     * 
     * @param name the description of the dish
     * @param price the dish price
     */
        
    public Dish(String name, double price) {
        // TODO
    	this(id, name, price);
    	id++;
    }
    
    private Dish(int id, String name, double price) {
        // TODO
    	Dish.id = id;
    	this.name = name;
    	this.price = price;
    }
    
    /**
     * 
     * @return the dish Id 
     */
    public int getId()  {
        return id;
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
        // TODO
        return null;
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dish a = new Dish("Bacalhau à Brás", 5.95);
		System.out.println(a.getId());
		System.out.println(a.getName());
		Dish b = new Dish("Bolonhesa", 7.0);
		System.out.println(b.getId());
		System.out.println(b.getName());

	}

}