package a5;

public class CrabPortion extends IngredientPortionImpl {
	
	
	private static Ingredient crab = new Crab();
	double amount;

	public CrabPortion(double amount) {

		super(crab, amount);
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
			return new CrabPortion(this.amount + other.getAmount());

		}
	}
}