package fcul.pco.teletasca.main;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.teletasca.domain.Client;
import fcul.pco.teletasca.domain.Dish;
import fcul.pco.teletasca.domain.Dish.DishType;
import fcul.pco.teletasca.domain.Drink;
import fcul.pco.teletasca.domain.Order;
import fcul.pco.teletasca.exceptions.DuplicatedIdException;
import fcul.pco.teletasca.exceptions.InvalidDateException;

/**
 * This class deals with the interactions with the user.
 *
 * @author Thibault Langlois
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 */
public class Menu {

	
	/**
	 * The main menu of the application. It serves to distinguish which kind of
	 * user is interacting with the application. It may be the restaurant
	 * manager or a client.
	 *
	 * @param in : a Scanner instance that correspond to the input of the program
	 * @throws IOException
	 */
	static void mainMenu(Scanner in) throws IOException {
		boolean end = false;
		System.out.println("**********************TELETASCA***********************");
		do {
			System.out.println("Você é: ");
			System.out.println("Cliente...................1");
			System.out.println("Gerente...................2");
			System.out.println("Terminar..................3");
			System.out.println("> ");

			final int option = Menu.nextInt(in);

			switch (option) {
				case 1:
					Menu.clientMenu(in);
					break;
				case 2:
					Menu.managerMenu(in);
					break;
				case 3:
					end = true;
					break;
				default:
					// end = false;
					break;
			}
		} while (!end);
	}

	/**
	 * A Client may open an account, log in, order dishes and consult his list
	 * of orders.
	 *
	 * @param in : a Scanner instance that correspond to the input of the program.
	 * @throws IOException
	 */
	private static void clientMenu(Scanner in) throws IOException {
		boolean end = false;
		do {
			System.out.println("Criar conta................1");
			System.out.println("Log in.....................2");
			System.out.println("Encomendar pratos..........3");
			System.out.println("Lista de encomendas........4");
			System.out.println("Terminar...................5");
			System.out.println("> ");

			final int option = Menu.nextInt(in);

			switch (option) {
				case 1:
					Menu.clientRegistrationMenu(in);
					break;
				case 2:
					Menu.clientLoginMenu(in);
					break;
				case 3:
					if (App.currentClient != null) {
						System.out.println(App.currentClient);
						Menu.makeOrder(in);
					} else {
						System.err.println("\nDeve fazer log in antes de fazer uma encomenda.\n");
						// end = false;
					}
					break;
				case 4:
					if (App.currentClient != null) {
						Menu.clientShowOrders();
					} else {
						System.err.println("\nDeve fazer log in antes de ver uma encomenda.\n");
						// end = false;
					}
					break;
				case 5:
					end = true;
					break;
				default:
					// end = false;
					break;
			}
		} while (!end);
	}

	/**
	 * A menu for the registration of the client's account.
	 *
	 * @param in : a Scanner instance that correspond to the input of the program.
	 * @throws IOException
	 */
	private static void clientRegistrationMenu(Scanner in) throws IOException {
		System.out.println("Nome:");
		final String name = Menu.nextLine(in);

		System.out.println("Email:");
		final String email = Menu.nextLine(in);

		
		try {
			Client c = new Client(name, email);
			App.clientCatalog.addClient(c);
		} catch (DuplicatedIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		App.clientCatalog.save();
	}

	/**
	 * The log in menu. Makes the current client be the client instance that
	 * matches the given email (if it exists, or null otherwise)
	 *
	 * @param in: a Scanner instance that correspond to the input of the program.
	 */
	private static void clientLoginMenu(Scanner in) {
		System.out.println("Email:");
		final String email = Menu.nextLine(in);

		App.currentClient = App.clientCatalog.getClientByEmail(email);

		if (App.currentClient == null) {
			System.err.println("\nNão existe nenhum utilizador com o email indicado.\n");
		}
	}

	/**
	 * The menu for ordering dishes.
	 * 
	 * @param in: a Scanner instance that correspond to the input of the program.
	 */
	private static void makeOrder(Scanner in) {
		//TODO
		Calendar date = null;
		try {
			date = Menu.getDate(in);
		} catch (InvalidDateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Order newOrder = null;
		try {
			newOrder = new Order(date, App.currentClient);
		} catch (DuplicatedIdException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Dish dish;
		int option;
		
		boolean end = true;
		do {
			System.out.println("Menu normal .............1");
			System.out.println("Menu light ..............2");
			System.out.println("Menu para dois ......... 3");
			System.out.println("> ");

			option = Menu.nextInt(in);

			switch (option) {
				case 1:
					do {
						dish = selectDish(in, false);
						if (dish != null) {
							newOrder.addDish(dish);
							
						}
					} while (dish != null);
					break;
				case 2:
					do {
						dish = selectLightDish(in);
						if (dish != null) {
							newOrder.addDish(dish);
						}
					} while (dish != null);
					break;
				case 3:
					do {
						dish = selectDish(in, true);
						if (dish != null) {
							newOrder.addDish(dish);
						}
					} while (dish != null);
					break;
				default:
					end = false;
					break;
			}
		} while (!end);
		
		Drink drink = offerDrink(newOrder.sumDishesPrice(), option);
		if (drink != null) {
			System.out.println("Parabens! A TeleTasca oferece-lhe uma bebida!");
			System.out.println(drink.quickFacts());			
		}
		
		try {
			App.orderCatalog.addOrder(newOrder);
			App.orderCatalog.save();
		} catch (DuplicatedIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * The menu for showing the client's list of orders.
	 */
	private static void clientShowOrders() {
		final List<Order> clientOrders = App.orderCatalog.getClientOrders(App.currentClient);
		Menu.showOrders(clientOrders);
	}

	/**
	 * The restaurant manager may add or remove dishes, and consult the list of
	 * orders that were made by clients.
	 *
	 * @param in: a Scanner instance that correspond to the input of the program.
	 * @throws IOException
	 */
	private static void managerMenu(Scanner in) throws IOException {
		boolean end = false;
		do {
			System.out.println("Adicionar um prato.........1");
			System.out.println("Remover um prato...........2");
			System.out.println("Consultar as encomendas....4");
			System.out.println("Terminar...................5");
			System.out.println("> ");

			final int option = Menu.nextInt(in);

			switch (option) {
				case 1:
					Menu.addDishMenu(in);
					break;
				case 2:
					Menu.removeDishMenu(in);
					break;
				case 4:
					Menu.managerShowOrders();
					break;
				case 5:
					end = true;
					break;
				default:
					// end = false;
					break;
			}
		} while (!end);
	}

	/**
	 * The menu for adding dishes.
	 *
	 * @param in: a Scanner instance that correspond to the input of the program.
	 * @throws IOException
	 */
	private static void addDishMenu(Scanner in) throws IOException {
		System.out.print("Descrição do prato: ");
		final String description = Menu.nextLine(in);
		System.out.print("\n");
		
		System.out.print("Preço: ");
		final double price = Menu.nextDouble(in);
		System.out.print("\n");
		
		System.out.print("Peso: ");
		final int servingSize = Menu.nextInt(in);
		System.out.print("\n");
		
		System.out.print("Número de pessoas: ");
		final int servings = Menu.nextInt(in);
		System.out.print("\n");
		
		System.out.print("Calorias: ");
		final int calories = Menu.nextInt(in);
		System.out.print("\n");
		
		System.out.print("Lípidos: ");
		final double fat = Menu.nextDouble(in);
		System.out.print("\n");
		
		System.out.print("Sal: ");
		final double sodium = Menu.nextDouble(in);
		System.out.print("\n");
		
		System.out.print("Hidratos de carbono: ");
		final double carbohydrate = Menu.nextDouble(in);
		System.out.print("\n");
		
		try {
			final Dish d = new Dish(description, price, true, servingSize, servings, calories, fat, sodium, carbohydrate);
			App.dishCatalog.addDish(d);
		} catch (DuplicatedIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		App.dishCatalog.save();
	}

	/**
	 * The menu for removing dishes.
	 *
	 * @param in: a Scanner instance that correspond to the input of the program.
	 * @throws IOException
	 */
	private static void removeDishMenu(Scanner in) throws IOException {
		final Collection<Dish> dishesList = App.dishCatalog.getAvailableDishes();
		if (dishesList != null && !dishesList.isEmpty()) {
			for (final Dish dish : dishesList) {
				System.out.println(Menu.formatString(dish.getName(), dish.getId()));
			}

			System.out.println("Escolhe um prato: ");
			final int chosenOption = Menu.nextInt(in);
			App.dishCatalog.removeDish(chosenOption);
			App.dishCatalog.save();
		} else {
			System.err.println("\nNão há pratos a apresentar.\n");
		}
	}

	/**
	 * A method to show all orders to the manager.
	 */
	private static void managerShowOrders() {
		final List<Order> allOrders = App.orderCatalog.getOrders();
		Menu.showOrders(allOrders);
	}

	/**
	 * A menu to show all orders.
	 *
	 * @param listOrders: a list with all the orders made
	 */
	private static void showOrders(List<Order> listOrders) {
		if (listOrders != null && !listOrders.isEmpty()) {
			for (final Order o : listOrders) {
				System.out.println(o);
			}
		} else {
			System.err.println("\nNão há encomendas a apresentar.\n");
		}
	}

	/**
	 * A method to get the date and time of the order.
	 * 
	 * @param in: a Scanner instance that correspond to the input of the program.
	 * @return the date and time of the order
	 * @throws InvalidDateException 
	 */
	private static Calendar getDate(Scanner in) throws InvalidDateException {
		System.out.println("Data da entrega [dd/mm/yyyy]: ");
		final int[] dateInt = Menu.nextDate(in);
		final int day = dateInt[0];
		final int month = dateInt[1];
		final int year = dateInt[2];

		System.out.println("Hora da entrega [hh:mm]: ");
		final int[] timeInt = Menu.nextTime(in);
		final int hour = timeInt[0];
		final int minutes = timeInt[1];

		final Calendar date = Calendar.getInstance();
		//date.setLenient(false);
		
		try {
			date.set(year, month, day, hour, minutes);
		}
		catch (IllegalArgumentException e) {
			throw new InvalidDateException("Erro no parsing da data. Usar formato indicado");
		}

		return date;
	}


	/**
	 * A method for choosing dishes from the catalog, according to the dish type. 
	 * 
	 * @param in: a Scanner instance that correspond to the input of the program.
	 * @return the chosen dish
	 */
	private static Dish selectDish(Scanner in, boolean menuTwoPersons) {
		Collection<Dish> dishesList;
		if (menuTwoPersons) {
			dishesList = App.dishCatalog.getDishesByType(DishType.FORTWO);
			if (dishesList.isEmpty()) {
				System.err.println("Não há pratos no menu para dois");
				menuTwoPersons = false;
				dishesList = App.dishCatalog.getAvailableDishes();
			}
		} else {
			dishesList = App.dishCatalog.getAvailableDishes();
		}
		
		if (dishesList.isEmpty()) {
			System.err.println("Não há pratos no menu");
			return null;
		}
		
		for (final Dish d : dishesList) {
			System.out.println(Menu.formatString(d.getName(), d.getId()));
		}
		System.out.println("Escolhe um prato (0 para terminar): ");
		
		int chosenOption = Menu.nextInt(in);
		return App.dishCatalog.getDishById(chosenOption);
	}
	
	/**
	 * A method for choosing light dishes from the (light dishes) catalog. 
	 * @param in: a Scanner instance that correspond to the input of the program.
	 * @return the chosen dish
	 */
	private static Dish selectLightDish(Scanner in) {
		Collection<Dish> dishesList = App.dishCatalog.getDishesByType(DishType.LIGHT);
		if (dishesList.isEmpty()) {
			System.err.println("Não há pratos no menu Light");
			selectDish(in, false);
		}
		for (final Dish d : dishesList) {
			System.out.println(d.quickFacts() + d.getId());
		}
		System.out.println("Escolhe um prato (0 para terminar): \n");
		
		int chosenOption = Menu.nextInt(in);
		return App.dishCatalog.getDishById(chosenOption);
	}
	
	/**
	 * A method for showing an offered drink, if that's the case.
	 * @param price, the total cost of a client's order
	 * @param menu, corresponds to the type of menu (light, standard, for two)
	 * @return the offered drink
	 */
	private static Drink offerDrink(double price, int menu) {
		
		// Menu para dois - 3;
		if (menu == 3 && price > 10.0) {
			return Drink.WINE;
		} 
		else if (price > 6.0) {
			switch (menu) {
				// Menu normal - 1
				case 1:
					return Drink.BEER;
					
				// Menu light - 2
				case 2:
					return Drink.COCAZERO;
			}
		}
		return null;
	}

	/**
	 * A method to format a string (justify text).
	 * 
	 * @param id: a int representing a unique id
	 * @param name: a string corresponding to a name
	 * @return the formatted string
	 */
	private static String formatString(String name, int id) {
		final int maxLength = 40;
		final int nameLength = name.length();
		final String idString = String.valueOf(id);
		final int idLength = idString.length();
		final int numberRepeats = maxLength - (nameLength + idLength);
		final String dots = String.join("", Collections.nCopies(numberRepeats, "."));
		return name + dots + idString;
	}

	/*
	 * The following methods read several kinds of values from a Scanner. The
	 * Scanner may correspond to System.in of to an input file. This allows
	 * automatic testing of the application through "use cases" that are tested
	 * using the executeUseCase method in the App class. The reason for using
	 * these methods instead of Scanner's nextXXX() methods is they allow
	 * comments in the use case files. Comments are begin with # and end at the
	 * end of the line.
	 */
	/**
	 * Reads an int input.
	 * @param in : a Scanner instance that corresponds to the input of the program.
	 * @return an integer
	 */
	private static int nextInt(Scanner in) {
		String s = in.nextLine();
		while (s.startsWith("#")) {
			s = in.nextLine();
		}
		if (s.contains("#")) {
			try (Scanner sc = new Scanner(s)) {
				s = sc.next();
			}
		}
		// System.out.println("next int " + s);
		return Integer.parseInt(s);
	}

	/**
	 * Reads a double input.
	 * @param in: a Scanner instance that corresponds to the input of the program.
	 * @return a double
	 */
	private static double nextDouble(Scanner in) {
		String s = in.nextLine();
		while (s.startsWith("#")) {
			s = in.nextLine();
		}
		if (s.contains("#")) {
			try (Scanner sc = new Scanner(s)) {
				s = sc.next();
			}
		}
		// System.out.println("next double " + s);
		return Double.parseDouble(s);
	}

	/**
	 * Reads a string input.
	 * @param in : a Scanner instance that corresponds to the input of the program.
	 * @return a string
	 */
	private static String nextLine(Scanner in) {
		String s = in.nextLine();
		while (s.startsWith("#")) {
			s = in.nextLine();
		}
		if (s.contains("#")) {
			final int p = s.indexOf("#");
			s = s.substring(0, p).trim();
		}
		// System.out.println("next line " + s);
		return s;
	}

	/**
	 * Reads a date input.
	 * @param in : a Scanner instance that corresponds to the input of the program.
	 * @return a date in the format of an array of integers.
	 */
	private static int[] nextDate(Scanner in) {
		String s = in.nextLine();
		while (s.startsWith("#")) {
			s = in.nextLine();
		}
		if (s.contains("#")) {
			final int p = s.indexOf("#");
			s = s.substring(0, p).trim();
		}
		final String[] a = s.split("/");
		final int[] d = new int[3];
		d[0] = Integer.parseInt(a[0]);
		d[1] = Integer.parseInt(a[1]);
		d[2] = Integer.parseInt(a[2]);
		return d;
	}

	/**
	 * Reads a time input.
	 * @param in : a Scanner instance that corresponds to the input of the program.
	 * @return a time in the format of an array of integers.
	 */
	private static int[] nextTime(Scanner in) {
		// int d, m, y;
		String s = in.nextLine();
		while (s.startsWith("#")) {
			s = in.nextLine();
		}
		if (s.contains("#")) {
			final int p = s.indexOf("#");
			s = s.substring(0, p).trim();
		}
		final String[] a = s.split(":");
		final int[] time = new int[2];
		time[0] = Integer.parseInt(a[0]);
		time[1] = Integer.parseInt(a[1]);
		return time;
	}
}
