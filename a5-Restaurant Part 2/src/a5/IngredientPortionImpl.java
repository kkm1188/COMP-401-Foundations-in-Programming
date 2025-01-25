package a5;


public abstract class IngredientPortionImpl implements IngredientPortion{
	
	private Ingredient ingredient;
	private double amount;
	private boolean seenBefore = false;
	
	
	public IngredientPortionImpl(Ingredient ingredient, double amount) {
		this.ingredient = ingredient;
		this.amount = amount;

	}

	@Override
	public Ingredient getIngredient() {
		return ingredient;
	}

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public String getName() {
		return getIngredient().getName();
	}

	@Override
	public boolean getIsVegetarian() {
		return getIngredient().getIsVegetarian();
	}

	@Override
	public boolean getIsRice() {
		return getIngredient().getIsRice();
	}

	@Override
	public boolean getIsShellfish() {
		return getIngredient().getIsShellfish();
	}

	@Override
	public double getCalories() {
		return amount * ingredient.getCaloriesPerOunce();
		
	}
	@Override
	public double getCost() {
		return this.amount * ingredient.getPricePerOunce();
	}

	@Override
	public abstract IngredientPortion combine(IngredientPortion other);

}