package fcul.pco.teletasca.domain;
import fcul.pco.teletasca.domain.NutritionFacts;
import fcul.pco.teletasca.domain.Dish;

public class tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		NutritionFacts nut = new NutritionFacts(1, 1, 500, 5, 0.1, 46.7);
		Dish pratoteste = new Dish("arroz de pato", 5, 500, 1, 750, 8, 0.5, 89);
		System.out.println(pratoteste.quickFacts());
	}

}
