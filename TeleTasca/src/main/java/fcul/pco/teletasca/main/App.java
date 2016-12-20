package fcul.pco.teletasca.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import fcul.pco.teletasca.domain.Client;
import fcul.pco.teletasca.domain.ClientCatalog;
import fcul.pco.teletasca.domain.DishCatalog;
import fcul.pco.teletasca.domain.OrderCatalog;
import fcul.pco.teletasca.exceptions.DuplicatedIdException;
import fcul.pco.teletasca.exceptions.InvalidDateException;
import fcul.pco.teletasca.exceptions.InvalidIdException;

/**
 * This is the application main class. It holds an instance of each of the
 * catalogs and an instance of Client that corresponds to the current user
 * (client).
 *
 * @author Thibault Langlois 
 * Alunos:
 * @author André Oliveira 45648
 * @author Tânia Maldonado 44745
 */
public class App {

	public static DishCatalog dishCatalog;
	public static ClientCatalog clientCatalog;
	public static OrderCatalog orderCatalog;
	public static Client currentClient = null;

	/**
	 * Initializes (creates) the catalogs.
	 */
	private static void initialize() {
		App.dishCatalog = fcul.pco.teletasca.domain.DishCatalog.getDishCatalog();
		App.clientCatalog = fcul.pco.teletasca.domain.ClientCatalog.getClientCatalog();
		App.orderCatalog = fcul.pco.teletasca.domain.OrderCatalog.getOrderCatalog();
	}

	/**
	 * This method may be called to use the application in default mode i.e.
	 * interacting with the keyboard.
	 *
	 * @throws IOException
	 */
	private static void interactiveMode() throws IOException {
		try (Scanner in = new Scanner(System.in)) {
			in.useLocale(Locale.US);
			Menu.mainMenu(in);
		}
	}

	/**
	 * This method allows the application to work in non interactive mode i.e.
	 * the input is read from a file. It should be used for testing. A file,
	 * called a use-case contains a sequence of inputs that allows testing some
	 * functionalities of the application. A use-case file may contain comments
	 * (useful to make it easy to understand). Comments begin with the #
	 * character and extend until the end of the line. 
	 * 
	 * Here is an example of use-case file: 
	 * -------- 
	 * # Caso de uso 1: o gerente adiciona um prato. 
	 * # user = gerente 
	 * 2 
	 * 1 # adicionar um prato 
	 * bacalhau à braz # descrição do prato 
	 * 5.99 # preço 
	 * 5 # terminar 
	 * 3 # terminar 
	 * -------
	 *
	 * @param useCaseFileName : A String that represents the name of a file 
	 * that contains a use-case.
	 * @throws IOException
	 * @requires the contents of the file must be correct with respect of the
	 *           menus (see class Menu) and the input data expected by the
	 *           application, unless the objective of the test is to verify an
	 *           illegal situation.
	 */
	private static void executeUseCase(String useCaseFileName) throws IOException {
		System.out.println("Test: " + useCaseFileName);
		final Scanner in = new Scanner(new File(useCaseFileName));
		in.useLocale(Locale.US);
		in.nextLine();
		Menu.mainMenu(in);
		in.close();
	}

	/**
	 * A method to execute all use cases.  
	 * 
	 * @throws IOException
	 */
	public static void executeAllUseCases() throws IOException {
//		App.executeUseCase("data/usecase1.dat");
//		App.executeUseCase("data/usecase2.dat");
//		App.executeUseCase("data/usecase3.dat");
//		App.executeUseCase("data/usecase4.dat");
//		App.executeUseCase("data/usecase5.dat");
//		App.executeUseCase("data/usecase6.dat");
//		App.executeUseCase("data/usecase7.dat");
//		App.executeUseCase("data/usecase8.dat");
//		App.executeUseCase("data/usecase9.dat");
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		App.initialize();
		// Loads the Catalogs; if they don't exist creates new empty ones
		try {
			App.dishCatalog.load();
		} catch (final FileNotFoundException e) {
			App.dishCatalog.save();
		} catch (DuplicatedIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			App.clientCatalog.load();
		} catch (final FileNotFoundException e) {
			App.clientCatalog.save();
		} catch (DuplicatedIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			App.orderCatalog.load();
		} catch (final FileNotFoundException e) {
			App.orderCatalog.save();
		} catch (DuplicatedIdException e) {
			// TODO Auto-generated catch block
			// Não faz nada porque há garantia que nunca é duplicado no inicio do programa
			e.printStackTrace();
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("InteractiveMode:\n");
		App.interactiveMode();
		System.out.println("UseCases:\n");
		executeAllUseCases();
	}
}