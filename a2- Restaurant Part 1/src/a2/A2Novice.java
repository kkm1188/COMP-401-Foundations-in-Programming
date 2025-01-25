package a2;

import java.util.Scanner;

public class A2Novice {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Your code here.
		
		int numingredients = scan.nextInt();
		
		class Ingredients {
			String ingredientName;
			double pricePerOunce;
			boolean isVegetarian;
			double caloriesPerOunce;
			
			Ingredients (String ingredientName, double pricePerOunce, boolean isVegetarian, int caloriesPerOunce) {
				this.ingredientName = ingredientName;
				this.pricePerOunce = pricePerOunce;
				this.isVegetarian = isVegetarian;
				this.caloriesPerOunce = caloriesPerOunce;
			}
			
		}
		
		Ingredients[] ingredientsArray = new Ingredients[numingredients];
		
		for(int a = 0; a < ingredientsArray.length; a++) {
			ingredientsArray[a] = new Ingredients("m", 0.00, false, 0);
		}
		
		for (int i = 0; i < ingredientsArray.length; i++) {
			ingredientsArray[i].ingredientName = scan.next();
			ingredientsArray[i].pricePerOunce = scan.nextDouble();
			ingredientsArray[i].isVegetarian = scan.nextBoolean();
			ingredientsArray[i].caloriesPerOunce = scan.nextInt();
		}
		int numvegetarian = 0;
		
		for (int r = 0; r < ingredientsArray.length; r++ ) {
			if (ingredientsArray[r].isVegetarian) {
				numvegetarian += 1;
			}
		}
		
		Ingredients maxcalories = new Ingredients("o", 0.00, false, 0);
		
		for (int c = 0; c < ingredientsArray.length; c++) {
			if ((ingredientsArray[c].caloriesPerOunce/ingredientsArray[c].pricePerOunce) > maxcalories.caloriesPerOunce) {
				maxcalories = ingredientsArray[c];
				ingredientsArray[c].caloriesPerOunce = (ingredientsArray[c].caloriesPerOunce)/(ingredientsArray[c].pricePerOunce);
			}
		}
		
		Ingredients mincalories = new Ingredients("p", 0.00, false, 100000);
		
		for(int v = 0; v < ingredientsArray.length; v++) {
			if(ingredientsArray[v].caloriesPerOunce/ingredientsArray[v].pricePerOunce < mincalories.caloriesPerOunce) {
				mincalories = ingredientsArray[v];
				ingredientsArray[v].caloriesPerOunce = (ingredientsArray[v].caloriesPerOunce)/(ingredientsArray[v].pricePerOunce);
			}
		}
		System.out.println("Number of vegetarian ingredients: " + numvegetarian);
		System.out.println("Highest cals/$: " + maxcalories.ingredientName);
		System.out.println("Lowest cals/$: " + mincalories.ingredientName);
		
	}
	
	// You can define helper methods here if needed.
	
}
