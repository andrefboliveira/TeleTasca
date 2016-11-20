// -*- coding: utf-8 -*-
package fcul.pco.teletasca.persistence;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.teletasca.domain.Client;

/**
 * This class is responsible for saving and loading the Client catalog. The
 * filenames are defined in the ApplicationConfiguration class.
 *
 * @author Thibault Langlois
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
public class ClientCatalog {

	private static String file = fcul.pco.teletasca.main.ApplicationConfiguration.ROOT_DIRECTORY
			+ fcul.pco.teletasca.main.ApplicationConfiguration.CLIENT_CATALOG_FILENAME;

	/**
	 * @param clients
	 * @throws IOException
	 */
	public static void save(Map<String, Client> clients) throws IOException {
		final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(ClientCatalog.file)));

		final String header = "email,name";
		writer.write(header);

		for (final Client c : clients.values()) {
			writer.write("\n");
			writer.write(c.toString());
		}
		writer.close();
	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Map<String, Client> load() throws FileNotFoundException {
		final Map<String, Client> clientsCatalog = new HashMap<String, Client>();
		final Scanner inputFile = new Scanner(new FileReader(ClientCatalog.file));
		inputFile.nextLine(); // to skip header

		while (inputFile.hasNextLine()) {
			final String line = inputFile.nextLine();
			final Client c = Client.fromString(line);
			final String c_email = c.getEmail();
			clientsCatalog.put(c_email, c);
		}
		inputFile.close();
		return clientsCatalog;
	}

}
