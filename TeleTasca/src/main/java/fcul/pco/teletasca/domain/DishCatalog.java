
package fcul.pco.teletasca.domain;

import java.io.IOException;
import java.util.Collection;
/**
 * The class represents the sets of Dishes of the restaurant.
 * Note that load and save operations are actually done by the class 
 * DishCatalog in the persistence package. This fact should be transparent 
 * for the rest of the application. domain.DishCatalog is the only class that 
 * "knows" that saving and loading (persistence operations) are actually done in 
 * the persistence package. 
 * 
 * @author Thibault Langlois
 */
public class DishCatalog {
	
    /**
     * Creates an empty catalog.
     */
    public DishCatalog() {
        // TODO
    }
    
    /**
     * Saves a catalog to a file.
     * 
     * @throws IOException 
     */
    public void save() throws IOException {
        // TODO
    }
    
    /**
     * Loads a catalog from a file and returns a catalog instance.
     * 
     * @throws IOException 
     */
    public void load() throws IOException {
        // TODO
    }
    
    
    /**
     * Adds a new dish to the catalog.
     * 
     * @param d is a Dish instance.
     * @requires ESPECIFICAR O CONTRATO 
     * @ensures ESPECIFICAR O CONTRATO
     */
    public void addDish(Dish d) {
        // TODO
    }
    
    /**
     * Given an Id, returns the corresponding Dish instance. If no instance is found, the value null is returned.
     * @param id the Id of a Dish
     * @return Dish instance or null
     * @requires ESPECIFICAR O CONTRATO 
     * @ensures ESPECIFICAR O CONTRATO
     * 
     */
    public Dish getDishById(int id) {
        // TODO
        return null;
    }
    
    /**
     * Returns a collection of all dishes.
     * @return a Collection of Dish(es)
     */
    public Collection<Dish> getDishes() {
        // TODO
        return null;
    }
    
    /**
     * Removes the corresponding dish from the catalog.
     * 
     * @param dishId an integer value that corresponds to the id of a Dish.
     * @requires ESPECIFICAR O CONTRATO 
     * @ensures ESPECIFICAR O CONTRATO 
     */
    public void removeDish(int dishId) {
        // TODO
    }  
	
}