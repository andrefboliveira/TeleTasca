// -*- coding: utf-8 -*-

package fcul.pco.teletasca.persistence;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fcul.pco.teletasca.domain.Order;

/**
 * This class is responsible for saving and loading the Order catalog. The
 * filenames are defined in the ApplicationConfiguration class.
 *
 * @author Thibault Langlois 
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 */
public class OrderCatalog {

	private static String file = fcul.pco.teletasca.main.ApplicationConfiguration.ROOT_DIRECTORY
			+ fcul.pco.teletasca.main.ApplicationConfiguration.ORDER_CATALOG_FILENAME;

	/**
	 * A method for writing and saving the catalog.
	 * @param orders : a list of orders
	 * @throws IOException
	 */
	public static void save(List<Order> orders) throws IOException {
		final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(OrderCatalog.file)));

		final String header = "id,client email,date of order,dish";
		writer.write(header);

		for (final Order o : orders) {
			writer.write("\n");
			writer.write(o.toString());
		}
		writer.close();
	}

	/**
	 * A method for loading the catalog.
	 * @return the orders catalog
	 * @throws FileNotFoundException
	 */
	public static List<Order> load() throws FileNotFoundException {
		final List<Order> ordersCatalog = new ArrayList<Order>();

		final Scanner inputFile = new Scanner(new FileReader(OrderCatalog.file));
		inputFile.nextLine(); // to skip header

		while (inputFile.hasNextLine()) {
			final String line = inputFile.nextLine();
			final Order o = Order.fromString(line);
			ordersCatalog.add(o);
		}
		inputFile.close();
		return ordersCatalog;
	}

}
