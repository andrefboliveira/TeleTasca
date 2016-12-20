package fcul.pco.teletasca.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fcul.pco.teletasca.exceptions.DuplicatedIdException;
import fcul.pco.teletasca.exceptions.InvalidDateException;
import fcul.pco.teletasca.exceptions.InvalidIdException;

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
	
	private int id;
	private Calendar date;
	private Client client;
	private List<Dish> dishList;
	
	private static int MaxId = 0;
	private static int counter = (MaxId > 1) ? MaxId : 1;
	
	private static String dateFormatString = "yyyy/MM/dd HH:mm";
	
	private static OrderCatalog currentCatalog = fcul.pco.teletasca.main.App.orderCatalog;
	private static ClientCatalog currentClientCatalog = fcul.pco.teletasca.main.App.clientCatalog;
	private static DishCatalog currentDishCatalog = fcul.pco.teletasca.main.App.dishCatalog;
	

	/**
	 * Initializes an Order instance.
	 *
	 * @param date: a Calendar instance (with time).
	 * @param client: an instance of Client.
	 * @throws DuplicatedIdException 
	 */
	public Order(Calendar date, Client client) throws DuplicatedIdException {
		this(Order.counter++, date, client);
	}

	/**
	 * Creates a private Order instance with a specific id, 
	 * only for managing purposes.
	 *
	 * @param id: unique id of the order
	 * @param date: date of the order
	 * @param client: who made the order.
	 * @throws DuplicatedIdException 
	 */
	private Order(int id, Calendar date, Client client) throws DuplicatedIdException {
		if (currentCatalog.getOrderById(id) == null) {
			this.id = id;
			this.date = date;
			this.client = client;
			dishList = new ArrayList<Dish>();
			
			if (id > MaxId) {
				MaxId = id;
				counter = MaxId + 1;
			}
		} else {
			System.out.println(id);
			throw new DuplicatedIdException("A encomenda já existe");
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
     * Removes a dish from the order. 
     * @param d 
     */
    private void removeDish(Dish d) {
    	this.dishList.remove(d);
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
	 * A getter for the order date.
	 * 
	 * @return the date
	 */
	public Calendar getDate() {
		return this.date;
	}
	
	public double sumDishesPrice() {
		double price = 0.0;
		for (Dish dish : dishList) {
			price += dish.getPrice();
		}
		return price;
	}

	/**
	 * Creates an Order instance from a string.
	 *
	 * @param s: a string that contains the id, the client's email address, the
	 *            date and the list of Dishes ids that compose the order.
	 * @return an Order instance
	 * @throws InvalidIdException 
	 * @throws InvalidDateException 
	 * @requires s is a string that contains the Id, the client's email address,
	 *           the date and a list of dishes Id separated by commas (,). The
	 *           string must contain at least four commas.
	 */
	public static Order fromString(String s) throws InvalidIdException, InvalidDateException {

		final String[] stringList = s.split(",");
		final int orderId = Integer.parseInt(stringList[0].trim());
		final String clientEmail = stringList[1].trim();

		final Calendar orderDate = Calendar.getInstance();
		final SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString, new Locale("pt", "PT"));
		

		try {
			dateFormat.setLenient(false);
			orderDate.setTime(dateFormat.parse(stringList[2].trim()));
		} catch (ParseException | IllegalArgumentException e) {
			throw new InvalidDateException("Erro no parsing da data. Usar formato: " + dateFormatString);
		}
		
		final Client c = currentClientCatalog.getClientByEmail(clientEmail);
		
		if (c == null) {
			throw new InvalidIdException("O cliente não existe no catálogo"); 
		}

		final Order newOrder = new Order(orderId, orderDate, c);

		for (int i = 3; i < stringList.length; i++) {
			String idString = stringList[i];
			boolean unavailable = false;
			if (idString.contains(" - ")) {
				idString = idString.split(" - ")[0];
				unavailable = true;				
			}
			final int dishID = Integer.parseInt(idString);
			final Dish d = currentDishCatalog.getDishById(dishID);
			if (d == null) {
				throw new InvalidIdException("O prato não existe no catálogo"); 
			}
			if (unavailable) {
				d.setAvailable(false);
			}
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
	 *         date and the list of Dishes id's that compose the order, following
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
		final SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString, new Locale("pt", "PT"));
		final String dateString = dateFormat.format(dateValue);
		builder.append(dateString);

		for (final Dish dish : this.dishList) {
			builder.append(",");
			builder.append(dish.getId());
			if (!dish.isAvailable()) {
				builder.append(" - não disponível");
			}
		}
		return builder.toString();
	}

	/**
	 * Generates hashCode for a given order instance based on the unique id.
	 */
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + this.id;
		return result;
	}

	/**
	 * Indicates whether some other order is "equal to" this one.
	 * 
	 * @return true if the orders are the same, false otherwise.
	 */
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
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