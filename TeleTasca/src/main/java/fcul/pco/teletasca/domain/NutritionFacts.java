package fcul.pco.teletasca.domain;

import java.security.InvalidParameterException;

import fcul.pco.teletasca.exceptions.DuplicatedIdException;

/**
 * This class represents the nutritional facts of a drink/dish 
 * that can be ordered by a restaurant client.
 *
 * @author Thibault Langlois 
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
public abstract class NutritionFacts {
	
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final double fat;
	private final double sodium;
	private final double carbohydrate;
	
	
	/**
	 * Initializes a Dish instance with the corresponding nutritional facts.
	 * 
	 * @param servingSize
	 * @param servings
	 * @param calories
	 * @param fat
	 * @param sodium
	 * @param carbohydrate
	 * @requires parameters "servingSize", "servings" and "calories" are integers; 
	 * "price", "fat", "sodium" and "carbohydrate" are doubles; "servingSize" and 
	 * "servings" parameters must be positive numbers.
	 */
	public NutritionFacts (int servingSize, int servings, int calories, double fat, double sodium, double carbohydrate) {
		this.servingSize = mustBePositive(servingSize, "O peso tem de ser > (maior) que 0");
		this.servings = mustBePositive(servings, "O número de pessoas tem de ser > (maior) que 0");
		this.calories = (int) mayBePositive(calories, "As calorias não podem ser um número negativo (< 0), foram inicializadas a 0");
		this.fat = mayBePositive(fat, "A quantidade de lípidos não podem ser um número negativo (< 0), foi inicializado a 0");
		this.sodium = mayBePositive(sodium, "A quantidade de sal não podem ser um número negativo (< 0), foi inicializado a 0");
		this.carbohydrate = mayBePositive(carbohydrate, "A quantidade de hidratos de carbono não podem ser um número negativo (< 0), foi inicializado a 0");	
	}
	
	
	/**
	 * A getter for the serving size.
	 * 
	 * @return the servingSize
	 */
	public int getServingSize() {
		return servingSize;
	}

	/**
	 * A getter for the servings. 
	 * 
	 * @return the servings
	 */
	public int getServings() {
		return servings;
	}

	/**
	 * A getter for the number of calories.
	 * 
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * A getter for the composition of fat.
	 * 
	 * @return the fat
	 */
	public double getFat() {
		return fat;
	}

	/**
	 * A getter for the composition of sodium.
	 * 
	 * @return the sodium
	 */
	public double getSodium() {
		return sodium;
	}

	/**
	 * A getter for the composition of carbohydrates.
	 * 
	 * @return the carbohydrate
	 */
	public double getCarbohydrate() {
		return carbohydrate;
	}

	/**
	 * A method to classify if the integer is positive.
	 * 
	 * @param x, an integer
	 * @param msg, a string
	 * @return an integer x if it is positive
	 * @throws InvalidParameterException if x is not positive
	 */
	static int mustBePositive(int x, String msg) {
		if (x > 0) { 
			return x;
		} else {
			throw new InvalidParameterException(msg); 
		}
	}
	
	/**
	 * A method to classify if the double is positive.
	 * 
	 * @param x, a double
	 * @param msg, a string
	 * @return a double x if it is positive, the double 0.0 if not
	 */
	static double mayBePositive(double x, String msg) {
		if (x >= 0.0) { 
			return x;
		} else {
			System.err.println(msg);
			return 0.0;
		}
	}
	
	/**
	 * A method that returns a string that "textually represents" an object,
	 * composed of the dish/drink nutritional facts.
	 * 
	 * @return the serving size, servings, calories, fat, sodium and carbohydrates
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(",");
		builder.append(this.servingSize);
		builder.append(",");
		builder.append(this.servings);
		builder.append(",");
		builder.append(this.calories);
		builder.append(",");
		builder.append(this.fat);
		builder.append(",");
		builder.append(this.sodium);
		builder.append(",");
		builder.append(this.carbohydrate);
		return builder.toString();
	}
	
	/**
	 * Returns a user-friendly string with the dish nutritional facts 
	 * to appear on the application.
	 * 
	 * @return a string with the nutritional facts
	 */
	public String quickFacts() {
		final StringBuilder builder = new StringBuilder();
		builder.append("...");
		builder.append(this.servings);
		builder.append(" serving(s)...");
		builder.append(this.calories);
		builder.append(" kcal...");
		builder.append(this.fat);
		builder.append("g of fat...");
		builder.append(this.sodium);
		builder.append("mg of sodium...");
		builder.append(this.carbohydrate);
		builder.append("g of carbohydrates...");
		return builder.toString();
	}
	
}
