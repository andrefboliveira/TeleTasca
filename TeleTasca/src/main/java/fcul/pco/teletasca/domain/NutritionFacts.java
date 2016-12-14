package fcul.pco.teletasca.domain;

import java.security.InvalidParameterException;

public class NutritionFacts {
	
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final double fat;
	private final double sodium;
	private final double carbohydrate;
	
	/**
	 *  TODO	
	 *	servingSize e servings têm de ser definidos (maior que 0). A aplicação lança uma exceção
	 *	de tipo InvalidParameterException (presente na API) SE TENTAR INICIALIZAR um objecto com
	 *	servingSize ou servings inferiores ou iguais a zero.
	 */
	
	public NutritionFacts (int servingSize, int servings, int calories, double fat, double sodium, double carbohydrate) {
		this.servingSize = servingSize;
		this.servings = servings;
		this.calories = calories;
		this.fat = fat;
		this.sodium = sodium;
		this.carbohydrate = carbohydrate;
	}
	
	
	/**
	 * @return the servingSize
	 */
	public int getServingSize() {
		return servingSize;
	}

	/**
	 * @return the servings
	 */
	public int getServings() {
		return servings;
	}

	/**
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * @return the fat
	 */
	public double getFat() {
		return fat;
	}

	/**
	 * @return the sodium
	 */
	public double getSodium() {
		return sodium;
	}

	/**
	 * @return the carbohydrate
	 */
	public double getCarbohydrate() {
		return carbohydrate;
	}

	/**
	 * Em cada método, o parâmetro de tipo String é 
	 * utilizado em caso de erro para imprimir a origem do problema.
	 * @param x
	 * @param msg
	 * @return
	 */
	static int mustBePositive(int x, String msg) {
		if (x > 0) { 
			return x;
		} else {
			throw new InvalidParameterException(msg); 
		}
	}
	
	static double mayBePositive(double x, String msg) {
		if (x >= 0.0) { 
			return x;
		} else {
			throw new InvalidParameterException(msg); 
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
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
	 * só mostra parte do objecto??? perguntar no fórum
	 * 
	 * restitui uma String na forma:
	 * ...1 servings...290 kcal...0.1g of glucides...
	 * @return
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
