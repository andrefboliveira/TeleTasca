// -*- coding: utf-8 -*-
package fcul.pco.teletasca.persistence;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import fcul.pco.teletasca.domain.Dish;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is responsible for saving and loading the Dish catalog.
 * The filenames are defined in the ApplicationConfiguration class.
 * 
 * @author Thibault Langlois 
 * Alunos: 
 * @author André Oliveira 45648 
 * @author Tânia Maldonado 44745
 */
public class DishCatalog {
	
	private static String fileName = "DishCatalog.csv";


	public static void save(Map<Integer,Dish> dishes) throws IOException {
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));	//usar nomes applicationconfiguration
    	String header = "id,description,price";
    	
    	writer.write(header);
    	for (Dish d : dishes.values()) {
    		writer.write("\n");
    		writer.write(d.toString());
		}
    	writer.close();
    	System.out.println("Wrote DishCatalog to " + fileName);
	}

	public static Map<Integer,Dish> load() throws IOException {
		Map<Integer,Dish> dishesCatalog = new HashMap<Integer,Dish>();    	
    	Scanner inputFile = new Scanner(new FileReader(fileName));
    	
    	inputFile.nextLine();
    	while (inputFile.hasNextLine()) {
    		String line = inputFile.nextLine();
    		Dish d = Dish.fromString(line);
    		int dishID = d.getId();
    		dishesCatalog.put(dishID, d);
		}
    	inputFile.close();
    	
    	return dishesCatalog;
	}
}
