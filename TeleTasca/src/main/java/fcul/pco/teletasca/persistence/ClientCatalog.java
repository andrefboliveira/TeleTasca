
package fcul.pco.teletasca.persistence;

import fcul.pco.teletasca.domain.Client;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

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

    public static void save(Map<String,Client> clients) throws IOException {
        // TODO
    }

    public static Map<String,Client> load() throws FileNotFoundException {
        // TODO
        return null;
    }

}
