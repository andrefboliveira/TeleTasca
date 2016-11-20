/**
 * All functionalities related to the persistence of information are grouped in
 * this package. Persistence is necessary because the application must remember
 * all information given by users (manager and clients) between sessions. In a
 * real application this package would contain the interface to the database. In
 * our case we will simply write the information into text files.
 *
 * Domain classes (Client, Order, Dish) must provide two methods that are used
 * in this package: - void toString() that produces a string with all relevant
 * information - static XXX fromString(String s) that receives a string produced
 * by toString and returns a instance of class XXX.
 *
 * @author Thibault Langlois Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
package fcul.pco.teletasca.persistence;
