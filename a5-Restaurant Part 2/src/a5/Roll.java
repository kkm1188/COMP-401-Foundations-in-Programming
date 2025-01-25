package a5;

import java.util.ArrayList;

public class Roll implements Sushi{

	String name;
	IngredientPortion[] roll_ingredients;
	int calories = 0;
	double cost = 0;
	boolean rice = false;
	boolean shellfish = false;
	boolean vegetarian = true;
	boolean seaweed = false;
	


	public Roll(String name, IngredientPortion[] roll_ingredients) {

		this.name = name;

		if(roll_ingredients == null) {
			throw new IllegalArgumentException("Cannot be null");
		}
		
		this.roll_ingredients = roll_ingredients.clone();

		for (int i = 0; i < roll_ingredients.length; i++) {
			if(roll_ingredients[i] == null)
				throw new IllegalArgumentException("Cannot be null");
		}

		IngredientPortion[] array3 = roll_ingredients.clone();
		ArrayList<IngredientPortion> jedi = new ArrayList<IngredientPortion>();

		for(int r = 0; r < roll_ingredients.length; r++) {
			for(int o = 0; o < roll_ingredients.length; o++) {
				if(o < r && r != 0 && array3[r] != null && array3[o] != null) {
					if (array3[r].getIngredient().equals(array3[o].getIngredient())){
						array3[o] = array3[o].combine(array3[r]);
						array3[r] = null;
					} 
				}
			}	
		}
		
		for(int q = 0; q < array3.length; q++) {
			if(array3[q] != null) {
				jedi.add(array3[q]);
			}
		}
		
		IngredientPortion[] array5 = new IngredientPortion[jedi.size()];
		for(int i = 0; i < jedi.size(); i++) {
			array5[i] = jedi.get(i);
		} 
		
		double amountCount = 0.00;
		for (int i = 0; i < array5.length; i++) {
			if(array5[i].getName().equals("seaweed")) {
				amountCount = array5[i].getAmount();
			} 
		}
		if(amountCount == 0.00) {
			jedi.add(new SeaweedPortion(0.1));
		} else if (amountCount < 0.1) {
			for (int i = 0; i < array5.length; i++) {
				if(jedi.get(i).getName().equals("seaweed")) {
					jedi.remove(i);
					jedi.add(new SeaweedPortion(0.1));
				} 
		}
		}
		
		IngredientPortion[] array2 = new IngredientPortion[jedi.size()];
		for(int i = 0; i < jedi.size(); i++) {
			array2[i] = jedi.get(i);
		
		
		this.roll_ingredients = array2;
		
		
		
		
		}
	}

	@Override
	public String getName() {
		return name;
	}


	@Override
	public IngredientPortion[] getIngredients() {
		return roll_ingredients.clone();
	}

	@Override
	public int getCalories() {
		for (int t = 0; t < roll_ingredients.length; t++) {
			calories += (int) (roll_ingredients[t].getCalories() + 0.5);
		}
		return calories;
	}

	@Override
	public double getCost() {
		for (int r = 0; r < roll_ingredients.length; r++) {
			cost += (Math.round(roll_ingredients[r].getCost() * 100.0)) / 100.0;
		}
		return ((int) ((cost * 100.0) + 0.5))/100.0;
	}

	@Override
	public boolean getHasRice() {
		for (int e = 0; e < roll_ingredients.length; e++) {
			if(roll_ingredients[e].getIsRice()) {
				rice = true;
			} 
		}
		return rice;
	}

	@Override
	public boolean getHasShellfish() {
		for (int w = 0; w < roll_ingredients.length; w++) {
			if(roll_ingredients[w].getIsShellfish()) {
				shellfish = true;
			} 
		}
		return shellfish;
	}

	@Override
	public boolean getIsVegetarian() {
		for (int q = 0; q < roll_ingredients.length; q++) {
			if(!roll_ingredients[q].getIsVegetarian()) {
				vegetarian = false;
			} 
		}
		return vegetarian;
	}
}