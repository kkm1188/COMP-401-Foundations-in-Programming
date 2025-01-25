package a5;

public class TunaPortion extends IngredientPortionImpl {
	
	private static Ingredient tuna = new Tuna();
	double amount;

	public TunaPortion(double amount) {

		super(tuna, amount);
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
			return new TunaPortion(this.amount + other.getAmount());

		}
	}
}