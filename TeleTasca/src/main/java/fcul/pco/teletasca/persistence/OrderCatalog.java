// -*- coding: utf-8 -*-


package fcul.pco.teletasca.persistence;

import fcul.pco.teletasca.domain.Dish;
import fcul.pco.teletasca.domain.Order;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * This class is responsible for saving and loading the Order catalog.
 * The filenames are defined in the ApplicationConfiguration class.
 * 
 * @author Thibault Langlois 
 * Alunos: 
 * @author André Oliveira 45648 
 * @author Tânia Maldonado 44745
 */
public class OrderCatalog {
	
	private static String file = fcul.pco.teletasca.main.ApplicationConfiguration.ROOT_DIRECTORY + fcul.pco.teletasca.main.ApplicationConfiguration.ORDER_CATALOG_FILENAME;

    public static void save(List<Order> orders) throws IOException {
    	PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
    	String header = "id,client email,date of order,dish";
    	writer.write(header);
    	for (Order o : orders) {
    		writer.write("\n");
    		writer.write(o.toString());
		}
    	writer.close();
    }

    public static List<Order> load() throws FileNotFoundException {
    	List<Order> orderCatalog = new ArrayList<Order>();
    	Scanner inputFile = new Scanner(new FileReader(file));
    	
    	inputFile.nextLine();
    	while (inputFile.hasNextLine()) {
    		String line = inputFile.nextLine();
    		Order o = Order.fromString(line);
    		orderCatalog.add(o);
		}
    	inputFile.close();
    	return orderCatalog;
    }
    

}
