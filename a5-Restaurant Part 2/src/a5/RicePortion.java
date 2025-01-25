package a5;


public class RicePortion extends IngredientPortionImpl {
	
	
	private static Ingredient rice = new Rice();
	double amount;

	public RicePortion(double amount) {

		super(rice, amount);
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
			return new RicePortion(this.amount + other.getAmount());

		}
	}
}