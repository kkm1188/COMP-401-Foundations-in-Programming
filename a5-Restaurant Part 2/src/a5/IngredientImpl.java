
package a5;

public class IngredientImpl implements Ingredient{
	
	private String name;
	private double pricePerOunce;
	private int caloriesPerOunce;
	private boolean isVegetarian;
	private boolean isRice;
	private boolean isShellfish;
	
	public IngredientImpl(String name, double pricePerOunce, int caloriesPerOunce,boolean isVegetarian,boolean isRice, boolean isShellfish) {
		this.name = name;
		this.pricePerOunce = pricePerOunce;
		this.caloriesPerOunce = caloriesPerOunce;
		this.isVegetarian = isVegetarian;
		this.isRice = isRice;
		this.isShellfish = isShellfish;
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getCaloriesPerDollar() {
		return caloriesPerOunce/pricePerOunce;
	}

	@Override
	public int getCaloriesPerOunce() {
		return caloriesPerOunce;
	}

	@Override
	public double getPricePerOunce() {
		return pricePerOunce;
	}

	@Override
	public boolean getIsVegetarian() {
		return isVegetarian;
	}

	@Override
	public boolean getIsRice() {
		return isRice;
	}

	@Override
	public boolean getIsShellfish() {
		return isShellfish;
	}

	@Override
	public boolean equals(Ingredient other) {
		if(other == null) {
			return false;
		} else {
			if(this.name.equals(other.getName()) && this.caloriesPerOunce == other.getCaloriesPerOunce() && (Math.abs(this.pricePerOunce - other.getPricePerOunce()) < 0.01) && this.isRice == other.getIsRice() && this.isShellfish == other.getIsShellfish() && this.isVegetarian == other.getIsVegetarian()) {
				return true;
			} else {
				return false;
				
			}
		}
	}
	

}