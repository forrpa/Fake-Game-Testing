package edible;


public class Recipie extends Item {
	
	private final Potion potion;
	private final Ingredient[] ingredients;
	private final int manaPointToCook;
	private final int requiredLevelToCook;

	public Recipie(String name, String description, Potion potion, Ingredient[] ingredients, int manaPointToCook, int requiredLevelToCook) {
		super(name, description);
		this.potion = potion;
		this.ingredients = ingredients;
		this.manaPointToCook = manaPointToCook;
		this.requiredLevelToCook = requiredLevelToCook;
	}
	
	public Potion cook(int manaPoint, int experiencePoint) {
		if(manaPoint < manaPointToCook || experiencePoint < requiredLevelToCook) 
			throw new IllegalStateException("Player doesn't have enough mana and/or experience to cook this potion yet.");
		return potion;
	}

	public Potion getPotion() {
		return potion;
	}
	
	public Ingredient[] getIngredients() {
		return ingredients.clone();
	}

	public int getManaPointToCook() {
		return manaPointToCook;
	}

	public int getRequiredLevel() {
		return requiredLevelToCook;
	}
	
	@Override
	public String toString() {
		String ingredientList = "[";
		for(Ingredient e : ingredients) ingredientList += e.getName() + ", ";
		ingredientList = ingredientList.substring(0, ingredientList.length()-1) + "]";
		return String.format("\"%s: %s\", Ingredients: " + ingredientList + ", Mana To Cook: %d, Experience To Cook: %d", getName(), getDescription(), getManaPointToCook(), getRequiredLevel());
	}

}
