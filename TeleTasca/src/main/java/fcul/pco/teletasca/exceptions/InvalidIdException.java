package fcul.pco.teletasca.exceptions;

/**
 * The Invalid Id Exception class represents an exception to be 
 * launched if an invalid id is found within a catalog.
 *
 * @author Thibault Langlois
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
public class InvalidIdException extends Exception {
	
	/**
	 * A method to launch a defined message for the exception to be launched.
	 * 
	 * @param message, a string parameter
	 */
	public InvalidIdException(String message) {
		super(message);
	}
}
