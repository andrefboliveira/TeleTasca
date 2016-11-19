
package fcul.pco.teletasca.main;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test.None;

import fcul.pco.teletasca.domain.Client;
import fcul.pco.teletasca.domain.ClientCatalog;
import fcul.pco.teletasca.domain.Dish;
import fcul.pco.teletasca.domain.Order;
import fcul.pco.teletasca.domain.OrderCatalog;

/**
 * This class deals with the interactions with the user.
 * 
 * @author Thibault Langlois 
 * Alunos: 
 * @author André Oliveira 45648 
 * @author Tânia Maldonado 44745
 */

// A classe Menu contém os menus e a interação com utilizador. 
// Deve ser completada (falta por exemplo a leitura dos dados),
// mas não deve alterar os menus !
// Para poder ler ficheiros de caso de uso com comentários deverá usar 
// os métodos (fornecidos) nextInt(Scanner in), nextDouble(Scanner in), 
// nextLine(Scanner in), nextDate(Scanner in) e nextTime(Scanner in).

public class Menu {
    
	// tratar null. ex: clientNewOrderMenu
	/**
     * The main menu of the application. It serves to distinguish which kind of 
     * user is interacting with the application. It may be the restaurant 
     * manager or a client.
     * 
     * @param in a Scanner instance that correspond to the input of the program
     * @throws IOException 
     */   
	static void mainMenu(Scanner in) throws IOException {
		boolean end = false;
		do {
			System.out.println("Você é: ");
			System.out.println("Cliente...................1");
			System.out.println("Gerente...................2");
			System.out.println("Terminar..................3");
			System.out.println("> ");

			int option = Menu.nextInt(in);
			
			switch (option) {
			case 1:
				clientMenu(in);
				break;
			case 2:
				managerMenu(in);
				break;
			case 3:
				end = true;
				break;
			default:
				end = false;
				break;
			}
		} while (!end);
	}
	

    /**
     * The restaurant manager may add or remove dishes, and consult the list of
     * orders that were made by clients.
     * 
     * @param in a Scanner instance that correspond to the input of the program.
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
            
            int option = Menu.nextInt(in);
            
            switch (option) {
			case 1:
				addDishMenu(in);
				break;
			case 2:
				removeDishMenu(in);
				break;
			case 4:
				managerShowOrders();
				break;
			case 5:
				end = true;
				break;
			default:
//				end = false;
				break;
			}
        } while (!end);
    }
    
	

    
	/**
     * The menu for adding dishes.
     * 
     * @param in
     * @throws IOException
     */
	private static void addDishMenu(Scanner in) throws IOException {
		System.out.print("Descrição do prato: ");
		String description = Menu.nextLine(in);

		System.out.print("Preço: ");
		double price = Menu.nextDouble(in);
		
		Dish d = new Dish(description, price);
        App.dishCatalog.addDish(d);
        App.dishCatalog.save();
	}
	
	
    /**
     * The menu for removing dishes.
     * 
     * @param in
     * @throws IOException
     */
	private static void removeDishMenu(Scanner in) throws IOException {
		Collection<Dish> dishesList = App.dishCatalog.getDishes();
		if (!dishesList.isEmpty()) {
			for (Dish dish : dishesList) {
				System.out.println(formatString(dish.getId(), dish.getName()));
			}
			
			System.out.println("Escolhe um prato: ");
			int chosenOption = Menu.nextInt(in);
			App.dishCatalog.removeDish(chosenOption);
			App.dishCatalog.save();
		} else {
			System.err.println("Não há pratos a apresentar.\n");
		}
	}
	
	/**
     * Shows all orders to the manager.
     * 
     * @param in
     * @throws IOException
     */
	private static void managerShowOrders() {
		List<Order> allOrders = App.orderCatalog.getOrders();
		showOrders(allOrders);
	}
    	

	/**
     * A Client may open an account, log in, order dishes and consult his 
     * list of orders.
     * 
     * @param in
     * @return
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
            
            int option = Menu.nextInt(in);
            
            switch (option) {
			case 1:
				clientRegistrationMenu(in);
				break;
			case 2:
				clientLoginMenu(in);
				break;
			case 3:
				if (App.currentClient != null) {
					clientNewOrderMenu(in);
				} else {
					System.err.println("Não fez login\n");
//					end = false;
				}
				break;
			case 4:
				if (App.currentClient != null) {
					clientShowOrders();
				} else {
					System.err.println("Não fez login\n");
//					end = false;
				}
				break;
			case 5:
				end = true;
				break;
			default:
//				end = false;
				break;
			}
        } while (!end);
    }
	
    /**
     * A menu for the registration of the client's account. 
     * 
     * @param in
     * @return
     * @throws IOException 
     */
    private static void clientRegistrationMenu(Scanner in) throws IOException {
        System.out.println("Nome:");
        String name = Menu.nextLine(in);
        
        System.out.println("Email:");
        String email = Menu.nextLine(in);
        
        Client c = new Client(name, email);
        App.clientCatalog.addClient(c);
        App.clientCatalog.save();
    }
    
    
    /**
     * The log in menu.
     * 
     * @param in
     * @throws IOException
     */
	private static void clientLoginMenu(Scanner in) throws IOException {		
		System.out.println("Email:");
		String email = Menu.nextLine(in);

		App.currentClient = App.clientCatalog.getClientByEmail(email);

		if (App.currentClient == null) {
			System.err.println("Não existe nenhum utilizador com o email indicado. \n");
		}
	}
	

	/**
     * The menu for ordering dishes.
     * 
     * @param in
	 * @throws IOException 
     */
	private static void clientNewOrderMenu(Scanner in) throws IOException {
		Calendar date = getDate(in);
		List<Integer> chosenDishes = chooseDishes(in);
		
		if (!chosenDishes.isEmpty()) {
			Order newOrder = new Order(date, App.currentClient);

			for (Integer chosenDishId : chosenDishes) {
				Dish d = App.dishCatalog.getDishById(chosenDishId);
				newOrder.addDish(d);
			}

			App.orderCatalog.addOrder(newOrder);
			App.orderCatalog.save();
		}
	}
	
	
	/**
     * The menu for showing the client's list of orders.
     * 
     * @throws IOException
     */
    private static void clientShowOrders() {
    	List<Order> clientOrders = App.orderCatalog.getClientOrders(App.currentClient);
    	showOrders(clientOrders);
	}
	
	
	/**
     * The menu for showing orders.
     * 
     * @param in
     * @throws IOException
     */
	private static void showOrders(List<Order> listOrders) {
		if (!listOrders.isEmpty()) {
			for (Order o : listOrders) {
				System.out.println(o);
			}
		} else {
			System.err.println("Não há encomendas a apresentar.");
		}
	}

	private static Calendar getDate (Scanner in) {
		System.out.println("Data da entrega [dd/mm/yyyy]: ");
		int[] dateInt = Menu.nextDate(in);
		int day = dateInt[0];
		int month = dateInt[1];
		int year = dateInt[2];

		System.out.println("Hora da entrega [hh:mm]: ");
		int[] timeInt = Menu.nextTime(in);
		int hour = timeInt[0];
		int minutes = timeInt[1];

		Calendar date = Calendar.getInstance();
		date.set(year, month, day, hour, minutes);

		return date;
	}
	
	private static List<Integer> chooseDishes (Scanner in) {
		List<Integer> chosenDishes = new ArrayList<Integer>();
		int chosenOption;
		
		Collection<Dish> dishesList = App.dishCatalog.getDishes();
		if (!dishesList.isEmpty()) {
			do {
				for (Dish d : dishesList) {
					System.out.println(formatString(d.getId(), d.getName()));
				}
				System.out.println("Escolhe um prato (0 para terminar): ");
				chosenOption = Menu.nextInt(in);
				chosenDishes.add(chosenOption);
			} while (chosenOption > 0);
			return chosenDishes;

		} else {
			System.err.println("Não há pratos a apresentar.");
			return null;
		}  
	}
	
	
	private static String formatString(int id, String name) {
    	int maxLength = 40;
    	int nameLength = name.length();
    	String idString = String.valueOf(id);
    	int idLength = idString.length();
    	int numberRepeats = maxLength-(nameLength+idLength);
    	String dots = String.join("", Collections.nCopies(numberRepeats, "."));
    	return name + dots + idString;
    }
		
    
    /*
     * The following methods read several kinds of values from a Scanner. 
     * The Scanner may correspond to System.in of to an input file. This allows 
     * automatic testing of the application through "use cases" that are tested 
     * using the executeUseCase method in the App class.    
     * The reason for using these methods instead of Scanner's nextXXX() methods 
     * is they allow comments in the use case files. Comments are begin with # 
     * and end at the end of the line.
     * 
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
    
    private static String nextLine(Scanner in) {
        String s = in.nextLine();
        while (s.startsWith("#")) {
            s = in.nextLine();
        }
        if (s.contains("#")) {
            int p = s.indexOf("#");
            s = s.substring(0, p).trim();
        }
        // System.out.println("next line " + s);
        return s;
    }
    
    private static int [] nextDate(Scanner in) {
        String s = in.nextLine();
        while (s.startsWith("#")) {
            s = in.nextLine();
        }
        if (s.contains("#")) {
            int p = s.indexOf("#");
            s = s.substring(0, p).trim();
        }
        String [] a = s.split("/");
        int [] d = new int[3];
        d[0] = Integer.parseInt(a[0]);
        d[1] = Integer.parseInt(a[1]);
        d[2] = Integer.parseInt(a[2]);
        return d;
    }
    
    private static int[] nextTime(Scanner in) {
        int d,m,y;
        String s = in.nextLine();
        while (s.startsWith("#")) {
            s = in.nextLine();
        }
        if (s.contains("#")) {
            int p = s.indexOf("#");
            s = s.substring(0, p).trim();
        }
        String [] a = s.split(":");
        int [] time = new int[2];
        time[0] = Integer.parseInt(a[0]);
        time[1] = Integer.parseInt(a[1]);
        return time;
    } 
}
