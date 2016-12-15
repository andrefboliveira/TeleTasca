
package fcul.pco.teletasca.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The class represents the sets of Clients known (registered) on the system.
 * Note that load and save operations are actually done by the class
 * ClientCatalog in the persistence package. This fact should be transparent for
 * the rest of the application. domain.ClentCatalog is the only class that
 * "knows" that saving and loading (persistence operations) are actually done in
 * the persistence package.
 *
 * @author Thibault Langlois 
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 */
public class ClientCatalog {

	private Map<String, Client> clientsCatalog;
	
	private static ClientCatalog clientCatInst = new ClientCatalog();
	
	
	/**
	 * Creates an empty catalog.
	 */
	private ClientCatalog() {
		this.clientsCatalog = new HashMap<String, Client>();
	}
	
	/**
	 * @return
	 */
	public static ClientCatalog getClientCatalog(){
		return clientCatInst;
	}
	
	
	/**
	 * Saves the catalog to a file.
	 *
	 * @throws IOException
	 */
	public void save() throws IOException {
		fcul.pco.teletasca.persistence.ClientCatalog.save(this.clientsCatalog);
		// System.out.println("Wrote ClientCatalog\n");
	}

	/**
	 * Loads the catalog from a file.
	 *
	 * @throws FileNotFoundException
	 */
	public void load() throws FileNotFoundException {
		this.clientsCatalog = fcul.pco.teletasca.persistence.ClientCatalog.load();
		// System.out.println("Loaded ClientCatalog\n");
	}

	/**
	 * Given an email address, the corresponding Client is returned. If it is
	 * not found in the catalog, null is returned.
	 *
	 * @param email : a string that contains an email address.
	 * @return an instance of Client or null.
	 * @requires a string with the email as a parameter
	 * @ensures returns the corresponding client or null if it doesn't exist
	 */
	public Client getClientByEmail(String email) {
		if (this.clientsCatalog.containsKey(email)) {
			return this.clientsCatalog.get(email);
		} else {
			return null;
		}
	}

	/**
	 * Adds a client to the catalog.
	 *
	 * @param c
	 * @requires a Client as parameter
	 * @ensures the given client is added to the clients catalog.
	 */
	public void addClient(Client c) {
		final String cEmail = c.getEmail();
		if (this.getClientByEmail(cEmail) != null) {
			this.clientsCatalog.put(cEmail, c);
		} else {

		}
		
	}

}
