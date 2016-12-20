// -*- coding: utf-8 -*-
package fcul.pco.teletasca.domain;

import fcul.pco.teletasca.exceptions.DuplicatedIdException;

/**
 * This class represents a restaurant dish that can be ordered by a client.
 * Each dish has various corresponding nutritional facts.
 *
 * @author Thibault Langlois 
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
public class Dish extends NutritionFacts {

	private static DishCatalog currentCatalog = fcul.pco.teletasca.main.App.dishCatalog;
	
	private int id;
	private String name;
	private double price;
	private boolean available;
	
	private DishType dishType;
	
	public enum DishType {
		STANDARD, LIGHT, FORTWO
	}
	
	private static int MaxId = 0;
	private static int counter = (MaxId > 1) ? MaxId : 1;
	
	
	/**
	 * Initializes a Dish instance with the corresponding nutritional facts.
	 *
	 * @param name: the description of the dish
	 * @param price: the dish price
	 * @param servingSize: the serving size, in g, of the dish
	 * @param servings: the number of servings
	 * @param calories: the number of calories of the dish, in kcal
	 * @param fat: the composition of fat, in g
	 * @param sodium: the composition of sodium, in mg
	 * @param carbohydrate: the composition of carbohydrates, in g
	 * @requires parameter "name" is a string; "servingSize", "servings" and "calories"
	 * 			 are integers; "price", "fat", "sodium" and "carbohydrate" are doubles.
	 * @throws DuplicatedIdException 
	 */	
	public Dish(String name, double price, boolean available, int servingSize, int servings, int calories, double fat, double sodium, double carbohydrate) throws DuplicatedIdException {
		this(Dish.counter++, name, price, available, servingSize, servings, calories, fat, sodium, carbohydrate);
	}

	/**
	 * Creates a private Dish instance with a specific id, 
	 * only for managing purposes.
	 * 
	 * @param id: a unique id for a dish
	 * @param name: the description of the dish
	 * @param price: the dish price
	 * @param servingSize: the serving size, in g, of the dish
	 * @param servings: the number of servings
	 * @param calories: the number of calories of the dish, in kcal
	 * @param fat: the composition of fat, in g
	 * @param sodium: the composition of sodium, in mg
	 * @param carbohydrate: the composition of carbohydrates, in g
	 * @requires parameter "name" is a string; "id", "servingSize", "servings" and "calories"
	 * 			 are integers; "fat", "sodium" and "carbohydrate" are doubles.
	 * @throws DuplicatedIdException 
	 */
	private Dish(int id, String name, double price, boolean available, int servingSize, int servings, int calories, double fat, double sodium, double carbohydrate) throws DuplicatedIdException {
		super(servingSize, servings, calories, fat, sodium, carbohydrate);
		// Erro comparar com NULO???
		if (currentCatalog.getDishById(id) == null) {
			this.id = id;
			this.name = name;
			this.price = price;
			this.available = available;
			setDishType();
			
			if (id > MaxId) {
				MaxId = id;
				counter = MaxId + 1;
			}
		} else {
			throw new DuplicatedIdException("O prato já existe");
		}	
	}

	/**
	 * Determines the dish type, according to the dish serving/calories.
	 * 
	 * @requires a dish with nutritional facts
	 */
	private void setDishType() {
		if (getServings() > 1) {
			this.dishType = DishType.FORTWO;
		} else if (getCalories() <= 500) {
			this.dishType = DishType.LIGHT;
		} else {
			this.dishType = DishType.STANDARD;
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
	 * 
	 * @return the dish description
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the price
	 */
	public double getPrice() {
		return this.price;
	}

	/**

	 * Returns the availability of the dish in the menu.
	 * 
	 * @return the availability
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Gives the dish the status "available".
	 * 
	 * @param true if available, false if not
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * A getter for the dish type.
	 * 
	 * @return the dish type
	 */
	public DishType getDishType() {
		return dishType;
	}

	/**
	 * Creates a Dish instance from a string.
	 *
	 * @param s: a string that describes a dish
	 * @return a Dish instance
	 * @throws DuplicatedIdException 
	 * @requires s is a string that contains the id, the name of a dish and its
	 *           price, separated by a comma (,). The string must contain
	 *           exactly two commas.
	 * @ensures the returned value d is such that d.getName() is equal to the
	 *          name specified in s.
	 *
	 */
	public static Dish fromString(String s) throws DuplicatedIdException {
		/*
		 * System.out.println(Dish.fromString("arroz de pato, 5, 500, 1, 750, 8, 0.5, 89"));
		 * ---
		 * Exception in thread "main" java.lang.NumberFormatException: For input string: "arroz de pato"
		 *	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
		 *	at java.lang.Integer.parseInt(Integer.java:580)
		 *	at java.lang.Integer.parseInt(Integer.java:615)
		 *	at fcul.pco.teletasca.domain.Dish.fromString(Dish.java:135)
		 *	at fcul.pco.teletasca.domain.tester.main(tester.java:11)
		 */
		final String[] stringlist = s.split(",");
		final int dishId = Integer.parseInt(stringlist[0].trim());
		final String dishName = stringlist[1].trim();
		final double dishPrice = Double.parseDouble(stringlist[2].trim());
		final boolean dishAvalability = Boolean.parseBoolean(stringlist[3].trim());
		final int dishServingSize = Integer.parseInt(stringlist[4].trim());
		final int dishServings = Integer.parseInt(stringlist[5].trim());
		final int dishCalories = Integer.parseInt(stringlist[6].trim());
		final double dishFat = Double.parseDouble(stringlist[7].trim());
		final double dishSodium = Double.parseDouble(stringlist[8].trim());
		final double dishCarbohydrate = Double.parseDouble(stringlist[9].trim());

		return new Dish(dishId, dishName, dishPrice, dishAvalability, dishServingSize, dishServings, dishCalories, dishFat, dishSodium, dishCarbohydrate);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * A method that returns a string that "textually represents" an object,
	 * composed of the unique dish's id, description, price, and nutritional facts.
	 * @requires parameter "name" is a string; "servingSize", "servings" and "calories"
	 * 			 are integers; "fat", "sodium" and "carbohydrate" are doubles.
	 * @return a string that contains the dish id, description, price, serving size,
	 * 		   servings, calories, fat, sodium and carbohydrates following .csv format.
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(this.id);
		builder.append(",");
		builder.append(this.name);
		builder.append(",");
		builder.append(this.price);
		builder.append(",");
		builder.append(this.available);
		builder.append(super.toString());
		return builder.toString();
	}
	
	/**
	 * Returns a user-friendly string with the dish nutritional facts 
	 * to appear on the application.
	 * 
	 * @return a string with the nutritional facts
	 */
	@Override
	public String quickFacts() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.name);
		builder.append("...");
		builder.append(this.price);
		builder.append(" EUR");
		builder.append(super.quickFacts());
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