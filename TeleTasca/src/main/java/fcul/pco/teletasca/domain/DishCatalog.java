
package fcul.pco.teletasca.domain;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class represents the sets of Dishes of the restaurant. Note that load and
 * save operations are actually done by the class DishCatalog in the persistence
 * package. This fact should be transparent for the rest of the application.
 * domain.DishCatalog is the only class that "knows" that saving and loading
 * (persistence operations) are actually done in the persistence package.
 *
 * @author Thibault Langlois 
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 */
public class DishCatalog {

	private Map<Integer, Dish> dishesCatalog;
	
	private static DishCatalog dishCatInst = new DishCatalog();
	
	/**
	 * Creates an empty catalog.
	 */
	private DishCatalog() {
		this.dishesCatalog = new TreeMap<Integer, Dish>();
	}
	
	/**
	 * @return
	 */
	public static DishCatalog getDishCatalog(){
		return dishCatInst;
	}

	/**
	 * Saves a catalog to a file.
	 *
	 * @throws IOException
	 */
	public void save() throws IOException {
		fcul.pco.teletasca.persistence.DishCatalog.save(this.dishesCatalog);
		// System.out.println("Wrote DishCatalog\n");
	}

	/**
	 * Loads a catalog from a file and returns a catalog instance.
	 *
	 * @throws IOException
	 */
	public void load() throws IOException {
		this.dishesCatalog = fcul.pco.teletasca.persistence.DishCatalog.load();
		// System.out.println("Loaded DishCatalog\n");
	}

	/**
	 * Adds a new dish to the catalog.
	 *
	 * @param d : is a Dish instance.
	 * @requires a Dish as a parameter
	 * @ensures the given dish is added to the dishes catalog.
	 */
	public void addDish(Dish d) {
		final int dishId = d.getId();
		if (this.getDishById(dishId) != null) {
			
			this.dishesCatalog.put(dishId, d);
		} else {
			System.err.println("\nPrato não instanciado\n");

		}

		// Ensure uniqueness in the catalog (not necessary, handled by Dish
		// constructor):
		/*
		 * if (!this.dishesCatalog.keySet().contains(dishId)) {
		 * this.dishesCatalog.put(dishId, d); }
		 */
	}

	/**
	 * Given an id, returns the corresponding Dish instance. 
	 * If no instance is found, the value null is returned.
	 *
	 * @param id : the id of a Dish
	 * @return Dish instance or null
	 * @requires parameter "id" is a int
	 * @ensures returns an instance of dish corresponding to the given id,
	 * 			or null if there isn't any match for the id.
	 *
	 */
	public Dish getDishById(int id) {
		if (this.dishesCatalog.containsKey(id)) {
			return this.dishesCatalog.get(id);
		} else {
			return null;
		}
	}

	/**
	 * Returns a collection of all dishes.
	 *
	 * @return a Collection of Dishes
	 */
	public Collection<Dish> getDishes() {
		return this.dishesCatalog.values();
	}

	/**
	 * Given an id, removes the corresponding dish from the catalog.
	 *
	 * @param dishId : an integer value that corresponds to the id of a Dish.
	 * @requires parameter "dishId" is an int (and is the Map key)
	 */
	public void removeDish(int dishId) {
		try {
			this.dishesCatalog.remove(dishId);
		} catch (NullPointerException e) {
			System.err.println("\nPrato não existe\n");
		}
		
	}

}