package fcul.pco.teletasca.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fcul.pco.teletasca.exceptions.DuplicatedIdException;
import fcul.pco.teletasca.exceptions.InvalidDateException;
import fcul.pco.teletasca.exceptions.InvalidIdException;

/**
 * The class represents the sets of Orders known on the system. Note that load
 * and save operations are actually done by the class OrderCatalog in the
 * persistence package. This fact should be transparent for the rest of the
 * application. domain.OrderCatalog is the only class that "knows" that saving
 * and loading (persistence operations) are actually done in the persistence
 * package.
 *
 * @author Thibault Langlois 
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 */
public class OrderCatalog {

	private final Map<Integer, Order> ordersCatalog;
	
	private static OrderCatalog orderCatInst = new OrderCatalog();

	/**
	 * Creates an empty Order catalog.
	 */
	private OrderCatalog() {
		this.ordersCatalog = new HashMap<Integer, Order>();
	}
	
	/**
	 * A getter for an order catalog.
	 * 
	 * @return an instance of an order catalog.
	 */
	public static OrderCatalog getOrderCatalog(){
		return orderCatInst;
	}

	/**
	 * Saves the catalog to a file.
	 *
	 * @throws IOException
	 */
	public void save() throws IOException {
		fcul.pco.teletasca.persistence.OrderCatalog.save(this.getOrders());
		// System.out.println("Wrote OrderCatalog\n");
	}

	/**
	 * Loads the catalog from a file.
	 *
	 * @throws FileNotFoundException
	 * @throws InvalidIdException 
	 * @throws InvalidDateException 
	 */
	public void load() throws FileNotFoundException, InvalidDateException, InvalidIdException {
		final List<Order> listOrders = fcul.pco.teletasca.persistence.OrderCatalog.load();
		// System.out.println("Loaded OrderCatalog\n");
		this.setOrders(listOrders);
	}

	/**
	 * Adds an Order instance to the catalog.
	 *
	 * @param o: an Order instance
	 * @throws DuplicatedIdException 
	 */
	public void addOrder(Order o) throws DuplicatedIdException {
		final int orderId = o.getId();
		if (this.getOrderById(orderId) == null) {
			this.ordersCatalog.put(orderId, o);
		} else {
			throw new DuplicatedIdException("A encomenda já existe");
		}
	}
	
	/**
	 * Given an id, returns the corresponding Order instance. 
	 * If no instance is found, the value null is returned.
	 *
	 * @param id: the id of a Order
	 * @return Order instance or null
	 * @requires parameter "id" is a int
	 * @ensures returns an instance of order corresponding to the given id,
	 * 			or null if there isn't any match for the id.
	 *
	 */
	public Order getOrderById(int id) {
		if (this.ordersCatalog.containsKey(id)) {
			return this.ordersCatalog.get(id);
		} else {
			return null;
		}
	}

	/**
	 * Returns a list of orders made by the client c.
	 *
	 * @param c : a Client instance
	 * @return list of Orders the where done by the client specified.
	 */
	public List<Order> getClientOrders(Client c) {
		
		final ArrayList<Order> listOrders = new ArrayList<Order>();
		for (final Order order : this.ordersCatalog.values()) {
			final Client client = order.getClient();
			if (client.equals(c)) {
				listOrders.add(order);
			}
		}
		return listOrders;
	}

	/**
	 * Returns the list of all orders.
	 *
	 * @return a list of Orders.
	 */
	public List<Order> getOrders() {
		List<Order> listOrders = new ArrayList<Order>(this.ordersCatalog.values());
		listOrders.sort(new Comparator<Order>() {
	        @Override
			public int compare(Order o1, Order o2) {
				return - o1.getDate().compareTo(o2.getDate());
			}
		});
		
		return listOrders;
	}

	/**
	 * A method for adding orders from a list (to the Map).
	 * 
	 * @param listOrders
	 * @throws DuplicatedIdException 
	 */
	public void setOrders(List<Order> listOrders) throws DuplicatedIdException {
		for (final Order order : listOrders) {
			this.addOrder(order);
		}
	}

}