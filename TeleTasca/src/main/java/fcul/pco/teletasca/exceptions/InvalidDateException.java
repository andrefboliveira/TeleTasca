package fcul.pco.teletasca.exceptions;

/**
 * The Duplicated Id Exception class represents an exception to be 
 * launched if an invalid date is found within a catalog.
 *
 * @author Thibault Langlois
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
public class InvalidDateException extends BadDataFormatException {
	
	/**
	 * A method to launch a defined message for the exception to be launched.
	 * 
	 * @param message, a string parameter
	 */
	public InvalidDateException (String message){
		super(message);
	}
}
