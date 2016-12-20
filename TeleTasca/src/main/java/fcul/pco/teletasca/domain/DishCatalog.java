
package fcul.pco.teletasca.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import fcul.pco.teletasca.domain.Dish.DishType;
import fcul.pco.teletasca.exceptions.DuplicatedIdException;

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
	 * @throws DuplicatedIdException 
	 */
	public void load() throws IOException, DuplicatedIdException {
		this.dishesCatalog = fcul.pco.teletasca.persistence.DishCatalog.load();
		// System.out.println("Loaded DishCatalog\n");
	}

	/**
	 * Adds a new dish to the catalog.
	 *
	 * @param d : is a Dish instance.
	 * @throws DuplicatedIdException 
	 * @requires a Dish as a parameter
	 * @ensures the given dish is added to the dishes catalog.
	 */
	public void addDish(Dish d) throws DuplicatedIdException {
		final int dishId = d.getId();
		if (this.getDishById(dishId) == null) {
			this.dishesCatalog.put(dishId, d);
		} else {
			throw new DuplicatedIdException("O prato já existe");
		}

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
	 * Returns a collection of all available dishes.
	 *
	 * @return a Collection of available Dishes
	 */
	public Collection<Dish> getAvailableDishes() {
		ArrayList<Dish> dishCollection = new ArrayList<Dish>();
		for (Dish d : this.dishesCatalog.values()) {
			if (d.isAvailable()) {
				dishCollection.add(d);
			}
		}
		return dishCollection;
	}
	
	/**
	 * Returns a collection of all available dishes of a given type (STANDARD, LIGHT, FORTWO).
	 *
	 * @return a Collection of available Dishes of a given type
	 */
	public Collection<Dish> getDishesByType(DishType type) {
		ArrayList<Dish> dishCollection = new ArrayList<Dish>();
		for (Dish d : this.dishesCatalog.values()) {
			if (d.isAvailable() && d.getDishType() == type) {
				dishCollection.add(d);
			}
		}
		return dishCollection;
	}
	


	/**
	 * Given an id, removes the corresponding dish from the catalog.
	 *
	 * @param dishId : an integer value that corresponds to the id of a Dish.
	 * @requires parameter "dishId" is an int (and is the Map key)
	 */
	public void removeDish(int dishId) {
		Dish d = this.getDishById(dishId);
		d.setAvailable(false);
		this.dishesCatalog.put(dishId, d);
	}

}