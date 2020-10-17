package edible;


public class Recipie extends Item {
	
	private final Potion potion;
	private final Ingredient[] ingredients;
	private final int manaPointToCook;
	private final int experiencePointToCook;

	public Recipie(String name, String description, Potion potion, Ingredient[] ingredients, int manaPointToCook, int experiencePointToCook) {
		super(name, description);
		//if(!potion.isCookable()) throw new IllegalArgumentException("Potion not meant to be cookable in this version!");
		this.potion = potion;
		this.ingredients = ingredients;
		this.manaPointToCook = manaPointToCook;
		this.experiencePointToCook = experiencePointToCook;
	}
	
	//public Potion cook(int manaPoint, int experiencePoint) {
	public Potion cook(int experiencePoint) {
		// check Players mana and exp points if insufficient throw ISE
		return potion;
	}

	public Potion getPotion() {
		return potion;
	}
	
	public Ingredient[] getIngredients() {
		return ingredients;
	}

	public int getManaPointToCook() {
		return manaPointToCook;
	}

	public int getExperiencePointToCook() {
		return experiencePointToCook;
	}
	
	@Override
	public String toString() {
		String ingredientList = "[";
		for(Ingredient e : ingredients) ingredientList += e.getName() + ", ";
		ingredientList = ingredientList.substring(0, ingredientList.length()-1) + "]";
		return String.format("\"%s: %s\", Ingredients: " + ingredientList + ", Mana To Cook: %d, Experience To Cook: %d", getName(), getDescription(), getManaPointToCook(), getExperiencePointToCook());
	}

}
