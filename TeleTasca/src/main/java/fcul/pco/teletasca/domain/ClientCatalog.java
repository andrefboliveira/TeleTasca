
package fcul.pco.teletasca.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

	/**
	 * Creates an empty catalog.
	 */
	public ClientCatalog() {
		this.clientsCatalog = new HashMap<String, Client>();
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
	 * loads the catalog from a file.
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
	 * @param email
	 *            a string that contains an email address.
	 * @return an instance of Client or null.
	 * @requires DEFINIR O CONTRATO
	 * @ensures DEFINIR O CONTRATO
	 *
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
	 * @requires ESPECIFICAR AQUI O CONTRATO.
	 * @ensures ESPECIFICAR AQUI O CONTRATO.
	 */
	public void addClient(Client c) {
		final String cEmail = c.getEmail();
		this.clientsCatalog.put(cEmail, c);

		// Ensure uniqueness in the catalog (not necessary, handled by Client
		// constructor):
		/*
		 * if (!this.clientsCatalog.keySet().contains(cEmail)) {
		 * this.clientsCatalog.put(cEmail, c); }
		 */
	}

}
