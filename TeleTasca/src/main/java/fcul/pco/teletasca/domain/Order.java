package fcul.pco.teletasca.domain;

import java.awt.List;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
    }
    
    /**
     * Removes a dish from the order. 
     * @param d 
     */
    private void removeDish(Dish d) {
    	this.dish_list.remove(d);
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
		builder.append(this.date);
		for (Dish dish : dish_list) {
			builder.append(",");
			builder.append(dish);
		}
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
    	//TODO fazer

    	String[] stringlist = s.split(",");
        int orderId = Integer.parseInt(stringlist[0].trim());
        
                
        Calendar orderDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", new Locale("pt", "PT"));
        //orderDate.setTime(dateFormat.parse(stringlist[1].trim()));
        
        String clientEmail = stringlist[2].trim();
        
        List dishList;
        
        
        //return new Order(orderId, orderDate, clientEmail);
        return null;
    }

}