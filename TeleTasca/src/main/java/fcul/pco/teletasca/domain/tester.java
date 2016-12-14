package fcul.pco.teletasca.domain;
import fcul.pco.teletasca.domain.NutritionFacts;


public class tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NutritionFacts nut = new NutritionFacts(1, 1, 500, 5, 0.1, 46.7);
		System.out.println(nut.quickFacts());
	}

}
