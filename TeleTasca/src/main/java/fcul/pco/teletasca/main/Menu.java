
package fcul.pco.teletasca.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
    

	/**
     * The main menu of the application. It serves to distinguish which kind of 
     * user is interacting with the application. It may be the restaurant 
     * manager or a client.
     * 
     * @param in a Scanner instance that correspond to the input of the program
     * @throws IOException 
     */   
    static void mainMenu(Scanner in) throws IOException {
    	
        boolean end = true; // <- think !
        do {
            System.out.println("Você é: ");
            System.out.println("Cliente...................1");
            System.out.println("Gerente...................2");
            System.out.println("Terminar..................3");
            System.out.println("> ");
            // TODO
        
            
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
				mainMenu(in);
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
				showDishes(in);
				break;
			case 5:
				mainMenu(in);
				break;
			default:
				managerMenu(in);
				break;
			}
        } while (!end);
    }
    
	/**
     * The menu for showing available dishes.
     * 
     * @param in
     * @throws IOException
     */
    private static void showDishes(Scanner in) {
		int i = 0;
		HashMap<Integer, Dish> allDishes = new HashMap<Integer, Dish>();
		Collection<Dish> dishesList = App.dishCatalog.getDishes();
		if (dishesList.size() != 0) {
			for (Dish d : App.dishCatalog.getDishes()) {
				System.out.println(d.getName() + ".........." + i);
				allDishes.put(i, d);
				i++;
			}
		} else {
			System.out.println("Não há pratos a apresentar.");
		}
		System.out.println("\n");
	}

    
	/**
     * The menu for adding dishes.
     * 
     * @param in
     * @throws IOException
     */
	private static void addDishMenu(Scanner in) throws IOException {
		// TODO 
		System.out.println("Descrição do prato:");
		String description = Menu.nextLine(in);
		System.out.println("Preço:");
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
		// TODO 
		int i = 0;
		HashMap<Integer, Dish> allDishes = new HashMap<Integer, Dish>();
		Collection<Dish> dishesList = App.dishCatalog.getDishes();
		if (dishesList.size() != 0) {
			for (Dish d : App.dishCatalog.getDishes()) {
				System.out.println(d.getName() + "......." + i);
				allDishes.put(i, d);
				i++;
			}
			int chosenOpt = Menu.nextInt(in);
			Dish d = allDishes.get(chosenOpt);
			App.dishCatalog.removeDish(d.getId());
	        App.dishCatalog.save();
		} else {
			System.out.println("Não há pratos a apresentar.\n");
		}
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
            // TODO
            int option = Menu.nextInt(in);
            switch (option) {
			case 1:
				clientRegistrationMenu(in);
				break;
			case 2:
				clientLoginMenu(in);
				break;
			case 3:
				clientOrderMenu(in);
				break;
			case 4:
				clientOrderListMenu(in);
				break;
			case 5:
				mainMenu(in);
				break;
			default:
				clientMenu(in);
				break;
			}
        } while (!end);
    }
	
	/**
     * The menu for showing the client's list of orders.
     * 
     * @param in
     * @throws IOException
     */
    //TODO CONFIRMAR SE ISTO ESTÁ BEM
    private static void clientOrderListMenu(Scanner in) {
		int i = 0;
		HashMap<Integer, Order> allDishes = new HashMap<Integer, Order>();
		Collection<Order> dishesList = App.orderCatalog.getClientOrders(App.currentClient);
		if (dishesList.size() != 0) {
			for (Order o : App.orderCatalog.getClientOrders(App.currentClient)) {
				System.out.println(o + ".........." + i);
				allDishes.put(i, o);
				i++;
			}
		} else {
			System.out.println("Não há pratos a apresentar.\n");
		}
	}

	/**
     * The menu for ordering dishes.
     * 
     * @param in
     */
	private static void clientOrderMenu(Scanner in) {
		int i = 0;
		HashMap<Integer, Dish> allDishes = new HashMap<Integer, Dish>();
		Collection<Dish> dishesList = App.dishCatalog.getDishes();
		if (dishesList.size() != 0) {
			for (Dish d : App.dishCatalog.getDishes()) {
				System.out.println(d.getName() + ".........." + i);
				allDishes.put(i, d);
				i++;
			}
			int chosenOpt = Menu.nextInt(in);
			Dish d = allDishes.get(chosenOpt);
			int id = d.getId();
			
			//OrderCatalog.addOrder(id);		//oh caralho como é que converto int para order para dizer que quero adicinoar o prato com aquele id???
	        //App.orderCatalog.save();
		} else {
			System.out.println("Não há pratos a apresentar.\n");
		}
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
		// TODO Nao faz confirmação se o cliente existe. senão: informa e repete o menu
		System.out.println("Email:");
		String email = Menu.nextLine(in);
		App.currentClient = App.clientCatalog.getClientByEmail(email);
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
