// -*- coding: utf-8 -*-
package fcul.pco.teletasca.persistence;

import java.io.IOException;

import fcul.pco.teletasca.domain.Dish;
import java.util.Map;

/**
 * This class is responsible for saving and loading the Dish catalog.
 * The filenames are defined in the ApplicationConfiguration class.
 * 
 * @author Thibault Langlois
 */
public class DishCatalog {

	public static void save(Map<Integer,Dish> dishes) throws IOException {
            // TODO
	}

	public static Map<Integer,Dish> load() throws IOException {
            // TODO
            return null;
	}
}
