package fcul.pco.teletasca.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
/**
 * The class represents the sets of Orders known on the system.
 * Note that load and save operations are actually done by the class 
 * OrderCatalog in the persistence package. This fact should be transparent 
 * for the rest of the application. domain.OrderCatalog is the only class that 
 * "knows" that saving and loading (persistence operations) are actually done in 
 * the persistence package. 
 * 
 * @author Thibault Langlois
 */
public class OrderCatalog {

	/**
	 * Creates an empty Order catalog.
	 */
	public OrderCatalog() {
            // TODO
        }

        /**
         * Saves the catalog to a file.
         * 
         * @throws IOException 
         */
	public void save() throws IOException {
            // TODO
        }

        /**
         * Loads the catalog from a file.
         * @throws FileNotFoundException 
         */
	public void load() throws FileNotFoundException {
        // TODO
        }

        /**
         * Adds an Order instance to the catalog.
         * 
         * @param o an Order instance 
         */
        public void addOrder(Order o) {
            // TODO
        }
        
        /**
         * Returns a list of orders made by the client c.
         * 
         * @param c a Client instance
         * @return A list of Orders.
         */
        public List<Order> getClientOrders(Client c) {
            // TODO
            return null;
        } 
        
        /**
         * Returns the list of all orders.
         * 
         * @return A list of Orders.
         */
        public List<Order> getOrders() {
            // TODO
            return null;
        }
        
	
}