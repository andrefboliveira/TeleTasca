
package fcul.pco.teletasca.main;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class deals with the interactions with the user.
 * 
 * @author Thibault Langlois * Alunos: * @author André Oliveira 45648 * @author Tânia Maldonado 44745
 */
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
            // TODO
        } while (!end);
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
        } while (!end);
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
        System.out.println("next int " + s);
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
        System.out.println("next double " + s);
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
        System.out.println("next line " + s);
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
