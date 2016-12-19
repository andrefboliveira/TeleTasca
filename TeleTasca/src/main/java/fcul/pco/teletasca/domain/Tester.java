package fcul.pco.teletasca.domain;
//import fcul.pco.teletasca.domain.NutritionFacts;
//import fcul.pco.teletasca.domain.Dish;
//import fcul.pco.teletasca.domain.DishCatalog;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		NutritionFacts nut = new NutritionFacts(1, 1, 500, 5, 0.1, 46.7);
		fcul.pco.teletasca.main.App.dishCatalog = DishCatalog.getDishCatalog();
		Dish pratoteste = new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
		new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
		new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
		new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
		new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
		new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);

		System.out.println(pratoteste);
		System.out.println(new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89));
	}

}
