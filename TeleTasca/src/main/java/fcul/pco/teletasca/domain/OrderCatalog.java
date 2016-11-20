package fcul.pco.teletasca.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class represents the sets of Orders known on the system. Note that load
 * and save operations are actually done by the class OrderCatalog in the
 * persistence package. This fact should be transparent for the rest of the
 * application. domain.OrderCatalog is the only class that "knows" that saving
 * and loading (persistence operations) are actually done in the persistence
 * package.
 *
 * @author Thibault Langlois Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 */
public class OrderCatalog {

	private final Map<Integer, Order> ordersCatalog;

	/**
	 * Creates an empty Order catalog.
	 */
	public OrderCatalog() {
		this.ordersCatalog = new HashMap<Integer, Order>();
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
	 */
	public void load() throws FileNotFoundException {
		final List<Order> listOrders = fcul.pco.teletasca.persistence.OrderCatalog.load();
		// System.out.println("Loaded OrderCatalog\n");
		this.setOrders(listOrders);
	}

	/**
	 * Adds an Order instance to the catalog.
	 *
	 * @param o
	 *            an Order instance
	 */
	public void addOrder(Order o) {
		final int orderId = o.getId();
		this.ordersCatalog.put(orderId, o);
	}

	/**
	 * Returns a list of orders made by the client c.
	 *
	 * @param c
	 *            a Client instance
	 * @return list of Orders.
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
	 * @return A list of Orders.
	 */
	public List<Order> getOrders() {
		return new ArrayList<Order>(this.ordersCatalog.values());
	}

	/**
	 * @param listOrders
	 */
	public void setOrders(List<Order> listOrders) {
		for (final Order order : listOrders) {
			this.addOrder(order);
		}
	}

}