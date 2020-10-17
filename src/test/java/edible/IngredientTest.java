package edible;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IngredientTest {

	final static Ingredient CLAW_OF_HIPOGRIFF = new Ingredient("Claw of Hipogriff", "Rare poisonous ingredient");
	
	@Test
	void constructorSetsAttributes() {
		assertEquals("Claw of Hipogriff", CLAW_OF_HIPOGRIFF.getName());
		assertEquals("Rare poisonous ingredient", CLAW_OF_HIPOGRIFF.getDescription());
	}

}
