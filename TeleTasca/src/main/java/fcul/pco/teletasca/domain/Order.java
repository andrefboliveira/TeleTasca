package fcul.pco.teletasca.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * This class represents an order composed by a date, a client and a list of
 * dishes.
 *
 * @author Thibault Langlois 
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 */
public class Order {
	// Ver duplicados. Usar catalogo. Verificar no construtor e quando adiciona o catálogo.

	private static int MaxId = 0;
	private static int counter = (MaxId > 0) ? MaxId : 1;	private int id;
	
	private Calendar date;
	private Client client;
	private ArrayList<Dish> dishList = new ArrayList<Dish>();
	private static String dateFormatString = "yyyy/MM/dd HH:mm";
	
	private static OrderCatalog currentCatalog = fcul.pco.teletasca.main.App.orderCatalog;
	private static ClientCatalog currentClientCatalog = fcul.pco.teletasca.main.App.clientCatalog;
	private static DishCatalog currentDishCatalog = fcul.pco.teletasca.main.App.dishCatalog;

	

	/**
	 * Initializes an Order instance.
	 *
	 * @param date : a Calendar instance (with time).
	 * @param client : an instance of Client.
	 */
	public Order(Calendar date, Client client) {
		this(Order.counter, date, client);
		Order.counter++;
	}

	/**
	 * Creates a private Order instance with a specific id, 
	 * only for managing purposes.
	 *
	 * @param id : unique id of the order
	 * @param date : date of the order
	 * @param client : who made the order.
	 */
	private Order(int id, Calendar date, Client client) {
		if (currentCatalog.getOrderById(id) != null) {
			this.id = id;
			this.date = date;
			this.client = client;
			MaxId = id;
		} else {
			System.err.println("\nEncomenda " + id + " já existe.\n");
		}
	}

	/**
	 * Adds a dish to the order.
	 *
	 * @param d : a Dish instance
	 */
	public void addDish(Dish d) {
		this.dishList.add(d);
	}

	/**
	 * Returns the client who made the order.
	 *
	 * @return a Client instance
	 */
	public Client getClient() {
		return this.client;
	}

	/**
	 * A getter for the order unique id.
	 *
	 * @return the id of the order
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Creates an Order instance from a string.
	 *
	 * @param s : a string that contains the id, the client's email address, the
	 *            date and the list of Dishes ids that compose the order.
	 * @return an Order instance
	 * @requires s is a string that contains the Id, the client's email address,
	 *           the date and a list of dishes Id separated by commas (,). The
	 *           string must contain at least four commas.
	 */
	public static Order fromString(String s) {

		final String[] stringList = s.split(",");
		final int orderId = Integer.parseInt(stringList[0].trim());
		final String clientEmail = stringList[1].trim();

		final Calendar orderDate = Calendar.getInstance();
		final SimpleDateFormat dateFormat = new SimpleDateFormat(Order.dateFormatString, new Locale("pt", "PT"));
		try {
			orderDate.setTime(dateFormat.parse(stringList[2].trim()));
		} catch (final ParseException e) {
			System.err.println("Error parsing date. Use format: " + Order.dateFormatString);
			e.printStackTrace();
		}
		final Client c = currentClientCatalog.getClientByEmail(clientEmail);


		final Order newOrder = new Order(orderId, orderDate, c);

		for (int i = 3; i < stringList.length; i++) {
			final int dishID = Integer.parseInt(stringList[i]);
			final Dish d = currentDishCatalog.getDishById(dishID);
			newOrder.addDish(d);
		}
		return newOrder;
	
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * A method that returns a string that "textually represents" an object.
	 * Composed of the unique client's id, order date, associated client and 
	 * the list of dishes ordered.
	 *
	 * @return a string that contains the id, the client's email address, the
	 *         date and the list of Dishes ids that compose the order, following
	 *         .csv format.
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(this.id);
		builder.append(",");
		builder.append(this.client.getEmail());
		builder.append(",");

		final Date dateValue = this.date.getTime();
		final SimpleDateFormat dateFormat = new SimpleDateFormat(Order.dateFormatString, new Locale("pt", "PT"));
		final String dateString = dateFormat.format(dateValue);
		builder.append(dateString);

		for (final Dish dish : this.dishList) {
			builder.append(",");
			builder.append(dish.getId());
		}
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	/**
	 * Generates hashCode for a given order instance based on the unique id.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + this.id;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/**
	 * Indicates whether some other order is "equal to" this one.
	 * 
	 * @return true if the orders are the same, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Order)) {
			return false;
		}
		final Order other = (Order) obj;
		return this.id == other.id;
	}

}