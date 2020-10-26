package edible;

import item.Item;

public class Ingredient extends Item {
	
	public Ingredient(String name, String description) {
		super(name, description);
	}

	@Override
	public final String toString() {
		return String.format("\"%s: %s\"", getName(), getDescription());
	}

}
