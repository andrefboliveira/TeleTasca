package fcul.pco.teletasca.domain;

import java.util.ArrayList;

/**
 * The client class represent a restaurant client that, after being registered,
 * can log in, order dishes, and consult the list his list of orders.
 *
 * @author Thibault Langlois Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 *
 */
public class Client {

	private static ArrayList<String> emailList = new ArrayList<String>();

	private String name;
	private String email;

	/**
	 * Initializes an instance of Client
	 *
	 * @param name
	 *            : the client's name
	 * @param email
	 *            : the client's email address
	 * @requires email address is unique in the system
	 */
	public Client(String name, String email) {

		if (!Client.emailList.contains(email)) {
			this.name = name;
			this.email = email;
			Client.emailList.add(email);
		} else {
			System.err.println("Cliente " + email + " já existe");
		}
	}

	/**
	 *
	 * @return returns the client's email address
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Creates an instance of Client from a String.
	 *
	 * @param s
	 *            a String
	 * @return an instance of Client
	 * @requires s is a string that contains the email address of the client and
	 *           its name, separated by a comma (,). The string must contain
	 *           exactly one comma.
	 * @ensures the returned value c is such that c.getEmail is equal to the
	 *          email address specified in s and c.name is equal to the name
	 *          specified in s.
	 */
	public static Client fromString(String s) {
		final String[] stringlist = s.split(",");
		final String clientEmail = stringlist[0].trim();
		final String clientName = stringlist[1].trim();

		return new Client(clientName, clientEmail);

	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(this.email);
		builder.append(",");
		builder.append(this.name);
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + this.email.hashCode();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Client)) {
			return false;
		}
		final Client other = (Client) obj;
		return this.email.equals(other.getEmail());

	}
}
