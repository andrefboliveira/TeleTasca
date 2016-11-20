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

	private static int counter = 1;
	private int id;
	private Calendar date;
	private Client client;
	private ArrayList<Dish> dishList = new ArrayList<Dish>();
	private static ArrayList<Integer> listId = new ArrayList<Integer>();
	private static String dateFormatString = "yyyy/MM/dd HH:mm";

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
		this.id = id;
		this.date = date;
		this.client = client;

		// To ensure orders are unique, not requested:
		/*
		 * if (!Order.listId.contains(id)) { this.id = id; this.date = date;
		 * this.client = client; } else { System.err.println("A encomenda " + id
		 * + " já existe."); }
		 */
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
		final Client c = fcul.pco.teletasca.main.App.clientCatalog.getClientByEmail(clientEmail);

		if (c != null) {
			final Order newOrder = new Order(orderId, orderDate, c);

			for (int i = 3; i < stringList.length; i++) {
				final int dishID = Integer.parseInt(stringList[i]);
				final Dish d = fcul.pco.teletasca.main.App.dishCatalog.getDishById(dishID);
				if (d != null) {
					newOrder.addDish(d);
				}
			}
			return newOrder;
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * Prints correctly to the .csv file.
	 *
	 * @requires and id, the client email and a date.
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
	 * Indicates whether some other object is "equal to" this one. 
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
	 * Creates and returns a copy of an object.
	 * 
	 * @return a copy of an object, in this case an email.
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
	
	/**
	 * Check if instance is null or has null properties.
	 * @return true if an instance is null else returns false
	 */
	public boolean isNull(){
		// Uses negation to catch unexpected cases. Unless it follows the
		// expected format it is consideres as null
		if (this == null) {
			return true;
		}
		if (!(this.id > 0)) {
			return true;
		}
		if (date == null) {
			return true;
		}
		if (this.client.isNull()) {
			return true;
		}
		return false;
	}

}