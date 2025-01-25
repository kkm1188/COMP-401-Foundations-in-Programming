package a5;

public class Nigiri implements Sushi {
	
	public enum NigiriType {TUNA, YELLOWTAIL, EEL, CRAB, SHRIMP}
	NigiriType type;
	String type2;
	IngredientPortion[] array = new IngredientPortion[2];
	double calories;
	double cost;
	
	
	public Nigiri(NigiriType type) {
		this.type = type;
	}

	@Override
	public String getName() {
		if(type == NigiriType.TUNA) {
			type2 = "tuna";
		} else if (type == NigiriType.YELLOWTAIL) {
			type2 = "yellowtail";
		} else if (type == NigiriType.EEL) {
			type2 = "eel";
		} else if (type == NigiriType.CRAB) {
			type2 = "crab";
		} else if (type == NigiriType.SHRIMP) {
			type2 = "shrimp";
		}
		return  type2 + " nigiri";
	}

	@Override
	public IngredientPortion[] getIngredients() {
		if (type == NigiriType.TUNA) {
			array[0] = new TunaPortion(0.75);
			array[1] = new RicePortion(0.5);
		} else if (type == NigiriType.YELLOWTAIL) {
			array[0] = new YellowtailPortion(0.75);
			array[1] = new RicePortion(0.5);
		} else if (type == NigiriType.EEL) {
			array[0] = new EelPortion(0.75);
			array[1] = new RicePortion(0.5);
			
		} else if (type == NigiriType.CRAB) {
			array[0] = new CrabPortion(0.75);
			array[1] = new RicePortion(0.5);
		} else if (type == NigiriType.SHRIMP) {
			array[0] = new ShrimpPortion(0.75);
			array[1] = new RicePortion(0.5);
		}
		return array;
	}

	@Override
	public int getCalories() {
		if (type == NigiriType.TUNA) {
			calories = Math.round(0.75 * new Tuna().getCaloriesPerOunce()) + Math.round(0.5 * new Rice().getCaloriesPerOunce());
		} else if (type == NigiriType.YELLOWTAIL)  {
			calories = Math.round(0.75 * new Yellowtail().getCaloriesPerOunce()+ Math.round(0.5 * new Rice().getCaloriesPerOunce()));
		} else if (type == NigiriType.EEL) {
			calories = Math.round (0.75 * new Eel().getCaloriesPerOunce() + Math.round(0.5 * new Rice().getCaloriesPerOunce()));
		} else if (type == NigiriType.CRAB) {
			calories = Math.round(0.75 * new Crab().getCaloriesPerOunce() + Math.round(0.5 * new Rice().getCaloriesPerOunce()));
		} else if(type == NigiriType.SHRIMP) {
			calories = Math.round(0.75 * new Shrimp().getCaloriesPerOunce() + Math.round(0.5 * new Rice().getCaloriesPerOunce()));
		}
		return (int) calories;
	}

	@Override
	public double getCost() {
		if (type == NigiriType.TUNA) {
			cost = (new Tuna().getPricePerOunce() * 0.75) + (new Rice().getPricePerOunce() * 0.50);
		} else if (type == NigiriType.YELLOWTAIL)  {
			cost = (new Yellowtail().getPricePerOunce() * 0.75) + (new Rice().getPricePerOunce() * 0.50);
		} else if (type == NigiriType.EEL) {
			cost = (new Eel().getPricePerOunce() * 0.75) + (new Rice().getPricePerOunce() * 0.50);
		} else if (type == NigiriType.CRAB) {
			cost = (new Crab().getPricePerOunce() * 0.75) + (new Rice().getPricePerOunce() * 0.50);
		} else if(type == NigiriType.SHRIMP) {
			cost = (new Shrimp().getPricePerOunce() * 0.75) + (new Rice().getPricePerOunce() * 0.50);
		}
		return cost;
	}

	@Override
	public boolean getHasRice() {
		return true;
	}

	@Override
	public boolean getHasShellfish() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getIsVegetarian() {
		// TODO Auto-generated method stub
		return false;
	};
	
}