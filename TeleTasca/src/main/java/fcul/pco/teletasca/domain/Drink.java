package fcul.pco.teletasca.domain;

import fcul.pco.teletasca.exceptions.DuplicatedIdException;

/**
 * This class represents 3 types of drinks that will be offered within the menu, in some cases.
 * Each drink has various corresponding nutritional facts.
 *
 * @author Thibault Langlois 
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
public class Drink extends NutritionFacts {
	
	public static final Drink BEER = new Drink("Beer", 300, 1, 126, 0, 0, 28);
	public static final Drink COCAZERO = new Drink("Coca-Cola Zero", 333, 1, 0, 0, 55, 0);
	public static final Drink WINE = new Drink("Vinho", 100, 1, 72, 0, 9, 1.2);
	
	private String name;
	
	/**
	 * Creates a private Drink instance, only for managing purposes.
	 *
	 * @param name: the description of the drink
	 * @param servingSize: the serving size, in ml, of the drink
	 * @param servings: the number of servings
	 * @param calories: the number of calories of the drink, in kcal
	 * @param fat: the composition of fat, in g
	 * @param sodium: the composition of sodium, in mg
	 * @param carbohydrate: the composition of carbohydrates, in g
	 * @requires parameter "name" is a string; "servingSize", "servings" and "calories"
	 * 			 are integers; "fat", "sodium" and "carbohydrate" are doubles.
	 */	
	private Drink(String name, int servingSize, int servings, int calories, double fat, double sodium, double carbohydrate) {
		super(servingSize, servings, calories, fat, sodium, carbohydrate);
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * A method that returns a string that "textually represents" an object,
	 * composed of the drink's description and nutritional facts.
	 * 
	 * @return the drink's name and nutritional facts.
	 */
	@Override
	public String toString() {
		return this.name + super.toString();
	}
	
	@Override
	public String quickFacts() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.name);
		builder.append(super.quickFacts());
		return builder.toString();
	}
}
