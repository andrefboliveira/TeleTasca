package fcul.pco.teletasca.domain;

import java.util.Calendar;

//import fcul.pco.teletasca.domain.NutritionFacts;
//import fcul.pco.teletasca.domain.Dish;
//import fcul.pco.teletasca.domain.DishCatalog;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		NutritionFacts nut = new NutritionFacts(1, 1, 500, 5, 0.1, 46.7);
		fcul.pco.teletasca.main.App.dishCatalog = DishCatalog.getDishCatalog();
//		Dish pratoteste = new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
//		new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
//		new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
//		new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
//		new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
//		new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);

//		System.out.println(pratoteste);
//		System.out.println(new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89));
		fcul.pco.teletasca.main.App.orderCatalog = OrderCatalog.getOrderCatalog();
		fcul.pco.teletasca.main.App.clientCatalog = ClientCatalog.getClientCatalog();
		fcul.pco.teletasca.main.App.dishCatalog = DishCatalog.getDishCatalog();


		OrderCatalog catalog = fcul.pco.teletasca.main.App.orderCatalog;
		try {
			Dish d = new Dish("arroz de pato", 5, true, 500, 1, 750, 8, 0.5, 89);
			Client c1 = new Client("Andre", "andre@gmail.com");
			Client c2 = new Client("Tania", "tania@gmail.com");

			Order o1 = new Order(Calendar.getInstance(), c1);
			Order o2 = new Order(Calendar.getInstance(), c2);
			Order o3 = new Order(Calendar.getInstance(), c1);
			Order o4 = new Order(Calendar.getInstance(), c1);
			Order o5 = new Order(Calendar.getInstance(), c2);
			catalog.addOrder(o1);
			catalog.addOrder(o2);
			catalog.addOrder(o3);
			catalog.addOrder(o4);
			catalog.addOrder(o5);
			System.out.println(catalog.getOrders());



			
			
	} catch (Exception e) {
			System.err.println("erro");
			e.printStackTrace();
		}
		

	}

}
