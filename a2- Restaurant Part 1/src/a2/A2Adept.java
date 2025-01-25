package a2;

import java.util.Scanner;

public class A2Adept {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);


		int numingredients = scan.nextInt();

		class Ingredients {
			String ingredientName;
			double pricePerOunce;
			boolean isVegetarian;
			int caloriesPerOunce;

			Ingredients (String ingredientName, double pricePerOunce, boolean isVegetarian, int caloriesPerOunce) {
				this.ingredientName = ingredientName;
				this.pricePerOunce = pricePerOunce;
				this.isVegetarian = isVegetarian;
				this.caloriesPerOunce = caloriesPerOunce;
			}

		}
		Ingredients[] ingredientsArray = new Ingredients[numingredients];

		//creating ingredient objects
		for(int a = 0; a < ingredientsArray.length; a++) {
			ingredientsArray[a] = new Ingredients(scan.next(), scan.nextDouble(), scan.nextBoolean(), scan.nextInt());
		}

		int numrolls = scan.nextInt();

		class Rolls{
			String name;
			int numNeededIngredients;
			String[] ingredientsname;
			double[] ouncesofingredient;
			double totalcalories = 0.00;
			double totalprice = 0.00;
			boolean vegetarian = true;

			Rolls (String name, int numNeededIngredients){
				this.name = name;
				this.numNeededIngredients = numNeededIngredients;
			}
		}
		Rolls[] rollsArray = new Rolls[numrolls];


		//go through each roll
		for (int u = 0; u < rollsArray.length; u++) {
			String rollname = scan.next();
			int numberingredients = scan.nextInt();
			rollsArray[u] = new Rolls(rollname, numberingredients);
			rollsArray[u].ingredientsname = new String [numberingredients];
			rollsArray[u].ouncesofingredient = new double[numberingredients];


			//create ingredients name string array
			for(int t = 0; t < numberingredients; t++) { 
				rollsArray[u].ingredientsname[t] = scan.next();

				//create ounces of ingredient array
				for(int r = 0; r < 1; r++) {
					rollsArray[u].ouncesofingredient[t] = scan.nextDouble();
				}
			}
		}

		// to change total price and total calories
		for(int v = 0; v < rollsArray.length; v++) {
			for (int i = 0; i < rollsArray[v].ingredientsname.length; i++) {
			for(int e = 0; e < ingredientsArray.length; e++) {
				if(rollsArray[v].ingredientsname[i].equals(ingredientsArray[e].ingredientName)) {
					rollsArray[v].totalcalories += ingredientsArray[e].caloriesPerOunce * rollsArray[v].ouncesofingredient[i];
					rollsArray[v].totalprice += (double) (ingredientsArray[e].pricePerOunce * rollsArray[v].ouncesofingredient[i]);
				} 
			}
		}
		}

		//to say if vegetarian or not
		for(int h = 0; h < rollsArray.length; h++) {
			for(int i = 0; i < rollsArray[h].ingredientsname.length; i++) {
			for(int y = 0; y < ingredientsArray.length; y++) {
				if(rollsArray[h].ingredientsname[i].equals(ingredientsArray[y].ingredientName) && !ingredientsArray[y].isVegetarian) {
					rollsArray[h].vegetarian = false;
				}

			}
		}
		}

		// print statements
		for(int b = 0; b < rollsArray.length; b++) {
			System.out.println(rollsArray[b].name + ":");
			System.out.println((int) (rollsArray[b].totalcalories + 0.5) + " calories");
			System.out.println("$" + String.format("%.2f", rollsArray[b].totalprice));
			if(rollsArray[b].vegetarian) {
				System.out.println("Vegetarian");
			} else {
				System.out.println("Non-Vegetarian");
			}


			// Your code here.
		}

		// You can define helper methods here if needed.

	}
}
