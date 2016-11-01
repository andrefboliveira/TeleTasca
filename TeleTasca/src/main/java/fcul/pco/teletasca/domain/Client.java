package fcul.pco.teletasca.domain;
/**
 * The client class represent a restaurant client that, after being registered,
 * can log in, order dishes, and consult the list his list of orders.
 * 
 * @author Thibault Langlois
 */
public class Client {
    
    
    /**
     * Initializes an instance of Client
     * @param name : the client's name
     * @param email : the client's email address
     * @requires email address is unique in the system
     */
    public Client(String name, String email) {
        
    }
    
    /**
     * 
     * @return returns the client's email address 
     */
    public String getEmail() {
        return "";
    }
    
    /**
     * Creates an instance of Client from a String.
     * 
     * @param s a String
     * @return an instance of Client
     * @requires s is a string that contains the email address of the client 
     * and its name, separated by a comma (,). The string must contain exactly 
     * one comma.
     * @ensures the returned value c is such that c.getEmail is equal to the 
     * email address specified in s and c.name is equal to the name specified 
     * in s.
     */
    public static Client fromString(String s) {
        // TODO 
        return null;
    }
    
    /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
    @Override
    public String toString() {
        // TODO
        return "";
    }

}