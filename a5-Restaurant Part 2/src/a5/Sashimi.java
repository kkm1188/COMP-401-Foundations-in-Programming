package a5;

import a5.Nigiri.NigiriType;

public class Sashimi implements Sushi{
	
	public enum SashimiType {TUNA, YELLOWTAIL, EEL, CRAB, SHRIMP};
	SashimiType type;
	String type2;
	IngredientPortion[] array = new IngredientPortion[1];
	double calories;
	double cost;
	
	
	public Sashimi(SashimiType type) {
		this.type = type;
	}

	@Override
	public String getName() {
		
		if(type == SashimiType.TUNA) {
			type2 = "tuna";
		} else if (type == SashimiType.YELLOWTAIL) {
			type2 = "yellowtail";
		} else if (type == SashimiType.EEL) {
			type2 = "eel";
		} else if (type == SashimiType.CRAB) {
			type2 = "crab";
		} else if (type == SashimiType.SHRIMP) {
			type2 = "shrimp";
		}
		return  type2 + " sashimi";
	}

	@Override
	public IngredientPortion[] getIngredients() {
		
		if(type == SashimiType.TUNA) {
			array[0] = new TunaPortion(0.75);
		} else if (type == SashimiType.YELLOWTAIL) {
			array[0] = new YellowtailPortion(0.75);
		} else if (type == SashimiType.EEL) {
			array[0] = new EelPortion(0.75);
		} else if (type == SashimiType.CRAB) {
			array[0] = new CrabPortion(0.75);
		} else if (type == SashimiType.SHRIMP) {
			array[0] = new ShrimpPortion(0.75);
		}
		return array;
	}

	@Override
	public int getCalories() {
		if(type == SashimiType.TUNA) {
			calories = Math.round(0.75 * new Tuna().getCaloriesPerOunce());
		} else if (type == SashimiType.YELLOWTAIL)  {
			calories = Math.round(0.75 * new Yellowtail().getCaloriesPerOunce());
		} else if (type == SashimiType.EEL) {
			calories = Math.round (0.75 * new Eel().getCaloriesPerOunce());
		} else if (type == SashimiType.CRAB) {
			calories = Math.round(0.75 * new Crab().getCaloriesPerOunce());
		} else if(type == SashimiType.SHRIMP) {
			calories = Math.round(0.75 * new Shrimp().getCaloriesPerOunce());
		}
		return (int) calories;
		
	}

	@Override
	public double getCost() {
		if (type == SashimiType.TUNA) {
			cost = new TunaPortion(0.75).getCost();
		} else if (type == SashimiType.YELLOWTAIL)  {
			cost = new YellowtailPortion(0.75).getCost();
		} else if (type == SashimiType.EEL) {
			cost = new EelPortion(0.75).getCost();
		} else if (type == SashimiType.CRAB) {
			cost = new CrabPortion(0.75).getCost();
		} else if(type == SashimiType.SHRIMP) {
			cost = new ShrimpPortion(0.75).getCost();
		}
		return cost;
	}

	@Override
	public boolean getHasRice() {
		return false;
	}

	@Override
	public boolean getHasShellfish() {
		if (type == SashimiType.TUNA) {
			return new Tuna().getIsShellfish();
		} else if (type == SashimiType.YELLOWTAIL)  {
			return new Yellowtail().getIsShellfish();
		} else if (type == SashimiType.EEL) {
			return new Eel().getIsShellfish();
		} else if (type == SashimiType.CRAB) {
			return new Crab().getIsShellfish();
		} else if(type == SashimiType.SHRIMP) {
			return new Shrimp().getIsShellfish();
		} else {
			return false;
		}
	}

	@Override
	public boolean getIsVegetarian() {
		return false;
	}
	
}