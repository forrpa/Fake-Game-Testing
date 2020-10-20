package edible;

import item.Item;

public class Ingredient extends Item {

	// found on special Quest?
	private final int REQUIRED_LEVEL = 0;
	
	public Ingredient(String name, String description) {
		super(name, description);
	}

	@Override
	public int getRequiredLevel() {
		return this.REQUIRED_LEVEL;
	}
	public String toString() {
		return String.format("\"%s: %s\"", getName(), getDescription());
	}

}
