// -*- coding: utf-8 -*-


package fcul.pco.teletasca.persistence;

import fcul.pco.teletasca.domain.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


/**
 * This class is responsible for saving and loading the Order catalog.
 * The filenames are defined in the ApplicationConfiguration class.
 * 
 * @author Thibault Langlois * Alunos: * @author André Oliveira 45648 * @author Tânia Maldonado 44745
 */
public class OrderCatalog {

    public static void save(List<Order> orders) throws IOException {
        // TODO
    }

    public static List<Order> load() throws FileNotFoundException {
        // TODO
        return null;
    }
    

}
