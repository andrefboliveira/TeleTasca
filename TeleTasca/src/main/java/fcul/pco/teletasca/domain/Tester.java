package fcul.pco.teletasca.domain;
//import fcul.pco.teletasca.domain.NutritionFacts;
//import fcul.pco.teletasca.domain.Dish;
//import fcul.pco.teletasca.domain.DishCatalog;

<<<<<<< HEAD
public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		NutritionFacts nut = new NutritionFacts(1, 1, 500, 5, 0.1, 46.7);
		fcul.pco.teletasca.main.App.dishCatalog = DishCatalog.getDishCatalog();
		System.out.println(fcul.pco.teletasca.main.App.dishCatalog);
=======
public class tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		NutritionFacts nut = new NutritionFacts(1, 1, 500, 5, 0.1, 46.7);
		fcul.pco.teletasca.main.App.dishCatalog = DishCatalog.getDishCatalog();
>>>>>>> branch 'Fase2' of https://github.com/prokod3r/TeleTasca
		Dish pratoteste = new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
		System.out.println(pratoteste);
	}

}
