
package fcul.pco.teletasca.persistence;

import fcul.pco.teletasca.domain.Client;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is responsible for saving and loading the Client catalog.
 * The filenames are defined in the ApplicationConfiguration class.
 * 
 * @author Thibault Langlois 
 * Alunos: 
 * @author André Oliveira 45648 
 * @author Tânia Maldonado 44745
 * 
 */
public class ClientCatalog {
	
	private static String fileName = "ClientCatalog.csv";


    public static void save(Map<String,Client> clients) throws IOException {
    	PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    	String header = "email,name";
    	writer.write(header);
    	for (Client c : clients.values()) {
    		writer.write("\n");
    		writer.write(c.toString());
		}
    	writer.close();
    	System.out.println("Wrote ClientCatalog to " + fileName);
    }

    public static Map<String,Client> load() throws FileNotFoundException {
    	Map<String, Client> clientsCatalog = new HashMap<String, Client>();    	
    	Scanner inputFile = new Scanner(new FileReader(fileName));
    	
    	inputFile.nextLine();
    	while (inputFile.hasNextLine()) {
    		String line = inputFile.nextLine();
    		Client c = Client.fromString(line);
    		String c_email = c.getEmail();
    		clientsCatalog.put(c_email, c);
		}
    	
    	inputFile.close();
    	
    	return clientsCatalog;
    }

}
