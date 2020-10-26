package edible;

import item.Item;

public class Recipe extends Item {
	
	private final Potion potion;
	private final Ingredient[] ingredients;
	private final int manaPointToCook;
	private final int experiencePointToCook;

	public Recipe(String name, String description, Potion potion, Ingredient[] ingredients, int manaPointToCook, int experiencePointToCook) {
		super(name, description);
		this.potion = potion;
		this.ingredients = ingredients;
		this.manaPointToCook = manaPointToCook;
		this.experiencePointToCook = experiencePointToCook;
	}
	
	protected final Potion cook(int manaPoint, int experiencePoint) {
		if(manaPoint < manaPointToCook || experiencePoint < experiencePointToCook) 
			throw new IllegalStateException("Player doesn't have enough mana and/or experience to cook this potion yet.");
		return potion;
	}

	public final Potion getPotion() { 
		return potion;
	}
	
	public final Ingredient[] getIngredients() {
		return ingredients.clone();
	}

	public final int getManaPointToCook() {
		return manaPointToCook;
	}

	public final int getExperiencePointToCook() {
		return experiencePointToCook;
	}
	
	@Override
	public final String toString() {
		String ingredientList = "[";
		for(Ingredient e : ingredients) ingredientList += e.getName() + ", ";
		ingredientList = ingredientList.substring(0, ingredientList.length()-2) + "]";
		return String.format("\"%s: %s\", Ingredients: " + ingredientList + ", Mana To Cook: %d, Experience To Cook: %d", getName(), getDescription(), getManaPointToCook(), getExperiencePointToCook());
	}

}
