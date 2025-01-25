package a5;

public class SeaweedPortion extends IngredientPortionImpl {
	
	
	private static Ingredient seaweed = new Seaweed();
	double amount;

	public SeaweedPortion(double amount) {

		super(seaweed, amount);
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
			return new SeaweedPortion(this.amount + other.getAmount());

		}
	}
}