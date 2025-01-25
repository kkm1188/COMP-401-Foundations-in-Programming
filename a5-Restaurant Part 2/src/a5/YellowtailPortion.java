package a5;

public class YellowtailPortion extends IngredientPortionImpl {
	
	
	private static Ingredient yellowtail = new Yellowtail();
	double amount;

	public YellowtailPortion(double amount) {

		super(yellowtail, amount);
		this.amount = amount;
		if(amount < 0) {
			throw new IllegalArgumentException("Value cannot be null");
		} 

	}

	public IngredientPortion combine(IngredientPortion other) {
		if(other == null) {
			return this;
		} else if(!this.getIngredient().equals(other.getIngredient())) {
			throw new IllegalArgumentException("Not equal");
		} else {
			return new YellowtailPortion(this.amount + other.getAmount());

		}
	}
}