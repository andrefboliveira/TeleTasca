package fcul.pco.teletasca.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents an order composed by a date, a client and a list 
 * of dishes.
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
	private ArrayList<Dish> dish_list = new ArrayList<Dish>();
//	private Map<Integer, Dish> dishList = new TreeMap<Integer, Dish>();
    private static String dateFormatString = "yyyy/MM/dd HH:mm";



	/**
     * Initializes an Order instance
     * 
     * @param date a Calendar instance (with time).
     * @param client an instance of Client.
     */
    public Order(Calendar date, Client client) {
    	this(counter , date, client);
    	counter++;
    }
    
    /**
     * Creates an Order instance.
     * 
     * @param id of the order
     * @param date of order
     * @param client who made the order.
     */
    private Order(int id, Calendar date, Client client) {
        this.id = id;
        this.date = date;
        this.client = client;
    }
    
    /**
     * Adds a dish to the order.
     * @param d a Dish instance
     */
    public void addDish(Dish d) {
        this.dish_list.add(d);
//    	this.dishList.put(d.getId(), d);
    }
    
    /**
     * Removes a dish from the order. 
     * @param d 
     */
    private void removeDish(Dish d) {
    	this.dish_list.remove(d);
//    	this.dishList.remove(d.getId());
    }
    
    /**
     * Returns the client that made this order.
     * @return a Client instance.
     */
    public Client getClient() {
        return this.client;
    }
    
    
    /**
     * A getter for the order Id.
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

    /**
     * Prints correctly to the .csv file.
     * @requires and id, the client email and a date.
     * @return a string that contains the id, the client's email address, 
     * the date and the list of Dishes ids that compose the order, following .csv format.
     */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.id);
		builder.append(",");
		builder.append(this.client.getEmail());
		builder.append(",");
		
		Date dateValue = this.date.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString, new Locale("pt", "PT"));
		String dateString = dateFormat.format(dateValue);
		builder.append(dateString);
		
		for (Dish dish : dish_list) {
			builder.append(",");
			builder.append(dish.getId());
		}
//		for (int dishID : dishList.keySet()) {
//			builder.append(",");
//			builder.append(dishID);
//		}
		return builder.toString();
	}
    
    /**
     * Creates an Order instance from a string.
     * 
     * @param s: a string that contains the id, the client's email address, 
     * the date and the list of Dishes ids that compose the order. 
     * @return an Order instance
     * @requires s is a string that contains the Id, the client's email address, the date and a list of dishes Id
     * separated by commas (,). The string must contain at least four commas.
     */
    public static Order fromString(String s) {

    	String[] stringlist = s.split(",");
        int orderId = Integer.parseInt(stringlist[0].trim());
        String clientEmail = stringlist[1].trim();
        
        Calendar orderDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString, new Locale("pt", "PT"));
        try {
			orderDate.setTime(dateFormat.parse(stringlist[2].trim()));
		} catch (ParseException e) {
			System.err.println("Error parsing date. Use format: " + dateFormatString);
			e.printStackTrace();
		}
        Client c = fcul.pco.teletasca.main.App.clientCatalog.getClientByEmail(clientEmail);
         
        Order newOrder = new Order(orderId, orderDate, c);
        
        for (int i = 3; i < stringlist.length; i++) {
			int dishID = Integer.parseInt(stringlist[i]);
			Dish d = fcul.pco.teletasca.main.App.dishCatalog.getDishById(dishID);
			newOrder.addDish(d);
		}
        
        return newOrder;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		return this.id == other.id;
	}

}