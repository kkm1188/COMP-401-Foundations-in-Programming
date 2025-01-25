package a2;

import java.util.ArrayList;
import java.util.Scanner;

public class A2Jedi {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);


		int numingredients = scan.nextInt();

		class Ingredients {
			String ingredientName;
			double pricePerOunce;
			boolean isVegetarian;
			int caloriesPerOunce;
			double numTotalOunces = 0.00;

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

		ArrayList<String> rolls = new ArrayList<String>();

		for(String t = scan.next(); !"EndOrder".equals(t); t = scan.next()) {
			if(!"EndOrder".equals(t)) {
				rolls.add(t);
			}
		}

		for(int u = 0; u < rolls.size(); u++) {
			for(int i = 0; i < rollsArray.length; i++) {
				for(int e = 0; e < rollsArray[i].ingredientsname.length; e++) {
					for(int r = 0; r < ingredientsArray.length; r++) {
						if(rolls.get(u).equals(rollsArray[i].name) && rollsArray[i].ingredientsname[e].equals(ingredientsArray[r].ingredientName)) {
							ingredientsArray[r].numTotalOunces += rollsArray[i].ouncesofingredient[e];

						}
					}
				}
			}

			// Your code here.
		}
		System.out.println("The order will require:");
		for (int y = 0; y < ingredientsArray.length; y++) {
			System.out.println(String.format("%.2f", ingredientsArray[y].numTotalOunces) + " ounces of " + ingredientsArray[y].ingredientName);
		}

	}

	// You can define helper methods here if needed.

}
