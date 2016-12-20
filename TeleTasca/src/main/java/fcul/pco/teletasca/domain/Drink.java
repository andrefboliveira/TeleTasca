package fcul.pco.teletasca.domain;

public class Drink extends NutritionFacts {
	
	public static final Drink BEER = new Drink("Beer", 300, 1, 126, 0, 0, 28);
	public static final Drink COCAZERO = new Drink("Coca-Cola Zero", 333, 1, 0, 0, 55, 0);
	public static final Drink WINE = new Drink("Vinho", 100, 1, 72, 0, 9, 1.2);
	
	private String name;
	
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
