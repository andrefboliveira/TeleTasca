package fcul.pco.teletasca.domain;

import java.util.ArrayList;


/**
 * The client class represent a restaurant client that, after being registered,
 * can log in, order dishes, and consult the list his list of orders.
 * 
 * @author Thibault Langlois
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 * 
 */
public class Client {
    
    private static ArrayList<String> email_list = new ArrayList<String>();
	
    private String name;
	private String email;


	/**
     * Initializes an instance of Client
     * @param name : the client's name
     * @param email : the client's email address
     * @requires email address is unique in the system
     */
    public Client(String name, String email) {
    	
    	if (!email_list.contains(email)) {
    		this.name = name;
    		this.email = email;
    		email_list.add(email);    		
    	} 
    	// Apagar
    	//TODO confirmar se está bem email unico
    	else {
    		System.out.println("Não criado");;
    	}
        
    }
    
    /**
     * 
     * @return returns the client's email address 
     */
    public String getEmail() {
        return this.email;
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.email);
		builder.append(",");
		builder.append(this.name);
		return builder.toString();
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
    	String[] stringlist = s.split(",");
        String clientName = stringlist[0].trim();
        String clientEmail = stringlist[1].trim();
        
        return new Client(clientName, clientEmail);
 
    }   

}