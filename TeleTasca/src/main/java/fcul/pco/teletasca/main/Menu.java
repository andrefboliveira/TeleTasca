
package fcul.pco.teletasca.main;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.teletasca.domain.Client;
import fcul.pco.teletasca.domain.ClientCatalog;

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
				//TODO aceder ao menu dos pratos para adicionar
				break;
			case 2:
				//TODO aceder ao menu dos pratos para remover
				break;
			case 3:
				//TODO mostrar lista de encomendas
				break;
			case 4:
				mainMenu(in);
				break;
			default:
				managerMenu(in);
				break;
			}
        } while (!end);
    }
    
//    private static void dishesMenu(Scanner in) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	private static void removeDishMenu(Scanner in) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	private static void addDishMenu(Scanner in) {
//		// TODO Auto-generated method stub
//		
//	}

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
				//TODO acessar lista de encomendas
				break;
			default:
				clientMenu(in);
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
//        boolean end = false;
        System.out.println("Nome:");
        String name = Menu.nextLine(in);
        System.out.println("Email:");
        String email = Menu.nextLine(in);
        //TODO guardar nome e email no catálogo de clientes
        Client c = new Client(name, email);
        ClientCatalog catalog = new ClientCatalog();
        //
        if (ClientCatalog.getClientByEmail(email) != null) {
               // Okay, there's a key but the value is null
        } else {
               // Definitely no such key
        }
        }
        catalog.addClient(c);
		
        
    }
//        do {
//            
//            System.out.println("Log in.....................2");
//            System.out.println("Encomendar pratos..........3");
//            System.out.println("Lista de encomendas........4");
//            System.out.println("Terminar...................5");
//            System.out.println("> ");
//            // TODO
//        } while (!end);
    
    
    /**
     * The log in menu.
     * 
     * @param in
     * @throws IOException
     */
	private static void clientLoginMenu(Scanner in) throws IOException {
		// TODO 
		System.out.println("Email:");
		String email = Menu.nextLine(in);
		//ver se o email inserido está no catálogo de clientes
	}
	
	
	/**
	 * The menu to choose the dishes to order.
	 * @param in
	 * @throws IOException
	 */
    private static void clientOrderMenu(Scanner in) throws IOException {
		// TODO Auto-generated method stub	
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
