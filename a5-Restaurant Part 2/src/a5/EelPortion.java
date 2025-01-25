package a5;


public class EelPortion extends IngredientPortionImpl {
	
	
	private static Ingredient eel = new Eel();
	double amount;

	public EelPortion(double amount) {

		super(eel, amount);
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
			return new EelPortion(this.amount + other.getAmount());

		}
	}
}