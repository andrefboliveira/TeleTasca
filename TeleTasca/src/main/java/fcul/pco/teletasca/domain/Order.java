package fcul.pco.teletasca.domain;

import java.util.Calendar;

/**
 * This class represents an order composed by a date, a client and a list 
 * of dishes.
 * 
 * @author Thibault Langlois * Alunos: * @author André Oliveira 45648 * @author Tânia Maldonado 44745
 */
public class Order {

    
    /**
     * Initializes an Order instance
     * 
     * @param date a Calendar instance (with time).
     * @param client an instance of Client.
     */
    public Order(Calendar date, Client client) {
        // TODO
    }
    
    private Order(int id, Calendar date, Client client) {
        // TODO
    }
    
    /**
     * Adds a dish to the order.
     * 
     * @param d a Dish instance
     */
    public void addDish(Dish d) {
        // TODO
    }
    
    /**
     * Returns the client that made this order.
     * @return a Client instance.
     */
    public Client getClient() {
        // TODO
        return null;
    }
    
    /**
     * Removes a dish from the order. 
     * @param d 
     */
    private void removeDish(Dish d) {
        // TODO
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
        // TODO
        return null;
    }
    
    
    @Override
    public String toString() {
       return ""; 
    }
}