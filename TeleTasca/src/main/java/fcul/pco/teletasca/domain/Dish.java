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
	// Ver duplicados. Usar catalogo. Verificar no construtor e quando adiciona o catálogo.
	private static int counter = 1;
	
	private static DishCatalog currentCatalog = fcul.pco.teletasca.main.App.dishCatalog;

	
	private int id;
	private String name;
	private double price;

	/**
	 * Initializes a Dish instance.
	 *
	 * @param name : the description of the dish
	 * @param price : the dish price
	 * @requires parameter "name" is a string, and "price" is a double
	 */
	public Dish(String name, double price) {
		this(Dish.counter, name, price);
		Dish.counter++;
	}

	/**
	 * Creates a private Dish instance with a specific id, 
	 * only for managing purposes.
	 * 
	 * @param id : a unique id for a dish
	 * @param name : the description of the dish
	 * @param price : the dish price
	 * @requires parameter "id" is an int, "name" is a string, and "price" 
	 * 			 is a double
	 */
	private Dish(int id, String name, double price) {
		if (currentCatalog.getDishById(id) != null) {
			this.id = id;
			this.name = name;
			this.price = price;
		} else {
			System.err.println("\nPrato " + id + " já existe.\n");
		}
	}

	/**
	 * A getter for the dish unique id.
	 *
	 * @return the dish id
	 * @requires the dish has an id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * A getter for the dish name/description.
	 * @return the dish description
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Creates a Dish instance from a string.
	 *
	 * @param s : a string that describes a dish
	 * @return a Dish instance
	 * @requires s is a string that contains the id, the name of a dish and its
	 *           price, separated by a comma (,). The string must contain
	 *           exactly two commas.
	 * @ensures the returned value d is such that d.getName() is equal to the
	 *          name specified in s.
	 *
	 */
	public static Dish fromString(String s) {
		final String[] stringlist = s.split(",");
		final int dishId = Integer.parseInt(stringlist[0].trim());
		final String dishName = stringlist[1].trim();
		final double dishPrice = Double.parseDouble(stringlist[2].trim());

		return new Dish(dishId, dishName, dishPrice);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * A method that returns a string that "textually represents" an object.
	 * Composed of the unique dish's id, description and price.
	 * 
	 * @return a string that contains the dish id, description and price,
	 *         following .csv format.
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(this.id);
		builder.append(",");
		builder.append(this.name);
		builder.append(",");
		builder.append(this.price);
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	/**
	 * Generates hashCode for a given dish instance based on the unique id.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + this.id;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/**
	 * Indicates whether some other dish is "equal to" this one.
	 * 
	 * @return true if the dishes are the same, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Dish)) {
			return false;
		}
		final Dish other = (Dish) obj;
		return this.id == other.id;
	}

}