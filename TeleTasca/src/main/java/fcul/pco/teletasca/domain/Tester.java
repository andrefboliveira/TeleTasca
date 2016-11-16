package fcul.pco.teletasca.domain;

import java.io.IOException;
import java.util.Calendar;

// DELETE
public class Tester {
	public static void main(String[] args) {
		
		

//		
//		System.out.println(catalogo.getClientOrders(c1));
		
//		// Test Dish
		Dish d1 = new Dish("Cozido", 5.95);
		Dish d2 = new Dish("Bolonhesa", 7.0);
//		Dish d3 = Dish.fromString("3, Bacalhau à Brás, 5.95");
//		
//		DishCatalog cat = new DishCatalog();
//		DishCatalog newCat = new DishCatalog();
//		cat.addDish(d1);
//		cat.addDish(d2);
//		cat.addDish(d3);
//		try {
//			cat.save();
//			newCat.load();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(newCat.getDishById(1));
//		
//		System.out.println(d1.getId());
//		System.out.println(d1.getName());
//		System.out.println(d1.toString());
//		
//		System.out.println(d2.getId());
//		System.out.println(d2.getName());
//		System.out.println(d2.toString());
//		
//		System.out.println(d3.getId());
//		System.out.println(d3.getName());
//		System.out.println(d3.toString());
//		
//		
//		
		// Test Client
//		Client c1 = new Client("André", "andrefboliveira@gmail.com");
//		Client c2 = new Client("Tânia", "tanmald@hotmail.com");
//		Client c3 = new Client("Maria", "tanmald@hotmail.com");
//		Client c4 = new Client("Joao", "andrefboliveira@gmail.com");
//		Client c5 = Client.fromString("Carolina, carolina@gmail.com");
//		ClientCatalog cat = new ClientCatalog();
//		ClientCatalog newCat = new ClientCatalog();
//		cat.addClient(c1);
//		cat.addClient(c2);
//		cat.addClient(c5);
//		try {
//			cat.save();
//			newCat.load();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(newCat.getClientByEmail("andrefboliveira@gmail.com").getEmail());
//		
//		System.out.println(c1.getEmail());
//		System.out.println(c2.getEmail());
//		System.out.println(c3.getEmail());
//		System.out.println(c4.getEmail());
//		System.out.println(c5.getEmail());
		
//		//Test OrderCatalog
		Calendar tempo = Calendar.getInstance();
		Client c1 = new Client("André", "andrefboliveira@gmail.com");
		Client c2 = new Client("Tânia", "tanmald@hotmail.com");
		Order o1 = new Order(tempo, c1);
		// Order o2 = new Order(tempo, c2);
		fcul.pco.teletasca.main.App.clientCatalog = new ClientCatalog();
		fcul.pco.teletasca.main.App.clientCatalog.addClient(c1);
		fcul.pco.teletasca.main.App.clientCatalog.addClient(c2);

		fcul.pco.teletasca.main.App.dishCatalog = new DishCatalog();
		fcul.pco.teletasca.main.App.dishCatalog.addDish(d1);
		fcul.pco.teletasca.main.App.dishCatalog.addDish(d2);
		Order o3 = Order.fromString("3,tanmald@hotmail.com,2016/11/16 13:00,2,1");
		o1.addDish(d1);
//		o2.addDish(d2);
//		o2.addDish(d1);
		System.out.println(o1);
//		System.out.println(o2);
		System.out.println(o3);
//		OrderCatalog catalogo = new OrderCatalog();
//		catalogo.addOrder(o1);
//		catalogo.addOrder(o2);
//		
//		
		
	}

}
