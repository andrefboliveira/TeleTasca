// -*- coding: utf-8 -*-
package fcul.pco.teletasca.domain;

import java.util.ArrayList;

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
    private static ArrayList<Integer> list_id = new ArrayList<Integer>();
    
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
//    	if (!list_id.contains(id)){
//    		this.id = id;
//        	this.name = name;
//        	this.price = price;
//        	list_id.add(id);
//    	} else {
//    		System.err.println("Prato " + id + " já existe.");
//    	}
    	
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
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.id);
		builder.append(",");
		builder.append(this.name);
		builder.append(",");
		builder.append(this.price);
		return builder.toString();
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Dish))
			return false;
		Dish other = (Dish) obj;
		return this.id == other.id;
	}
    
    

}