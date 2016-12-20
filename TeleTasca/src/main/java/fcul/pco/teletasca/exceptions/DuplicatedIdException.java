package fcul.pco.teletasca.exceptions;

/**
 * The Duplicated Id Exception class represents an exception to be 
 * launched if a duplicated id is found within a catalog.
 *
 * @author Thibault Langlois
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
public class DuplicatedIdException extends InvalidIdException {
	
	/**
	 * A method to launch a defined message for the exception to be launched.
	 * 
	 * @param message, a string parameter
	 */
	public DuplicatedIdException (String message) {
		super(message);
	}
}
