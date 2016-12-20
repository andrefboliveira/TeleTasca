package fcul.pco.teletasca.exceptions;

/**
 * The Bad Data Format Exception class represents an exception to be 
 * launched if 
 *
 * @author Thibault Langlois
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
public class BadDataFormatException extends Exception {

	/**
	 * A method to launch a defined message for the exception to be launched.
	 * 
	 * @param message, a string parameter
	 */
	public BadDataFormatException(String message) {
		super(message);
	}

}