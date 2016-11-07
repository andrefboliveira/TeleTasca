
package fcul.pco.teletasca.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class represents the sets of Clients known (registered) on the system.
 * Note that load and save operations are actually done by the class 
 * ClientCatalog in the persistence package. This fact should be transparent 
 * for the rest of the application. domain.ClentCatalog is the only class that 
 * "knows" that saving and loading (persistence operations) are actually done in 
 * the persistence package. 
 * 
 * @author Thibault Langlois 
 * Alunos: 
 * @author André Oliveira 45648 
 * @author Tânia Maldonado 44745
 */
public class ClientCatalog {
    
    private ArrayList <Client> clients;

	/**
     * Creates an empty catalog.
     */
    public ClientCatalog() {
    	clients = new ArrayList <Client>();
    }
    
//interface Book {
//	String getTitle();
//	String getAuthor();
//	String getTextAtTheBack();
//	Long getWeight(); // in grams, can be very heavy!
//}
//
//public class Catalog {
//	private ArrayList<Book> catalogue;
//	public Book createPaperback(final String title, final String author, 
//  	                          final String tatb, final Long weight) {
// 	Book b = new Book() {
//   	String getTitle() { return title; }
//  	String getAuthor() {return author; }
//    	String getTextAtTheBack() {return tatb;}
//    	Long getWeight() {return weight;}
//  }
//  catalogue.add(b);
//  return b;
//}
    
    
    
    /**
     * Saves the catalog to a file.
     * 
     * @throws IOException 
     */
    public void save() throws IOException {
    }

    /**
     * loads the catalog from a file.
     * 
     * @throws FileNotFoundException 
     */
    public void load() throws FileNotFoundException {
    }

    /**
     * Given an email address, the corresponding Client is returned. If it is 
     * not found in the catalog, null is returned.
     * 
     * @param email a string that contains an email address. 
     * @return an instance of Client or null.
     * @requires DEFINIR O CONTRATO
     * @ensures DEFINIR O CONTRATO
     * 
     */
    public Client getClientByEmail(String email) {
        // TODO
        return null;
    }
    
    /**
     * Adds a client to the catalog.
     * @param c
     * @requires ESPECIFICAR AQUI O CONTRATO.
     * @ensures ESPECIFICAR AQUI O CONTRATO.
     */
    public void addClient(Client c) {
    	Client c = new Client();	//como é que eu uso o client que criei na outra classe?
        clients.add(c); 	
        
    }

}

