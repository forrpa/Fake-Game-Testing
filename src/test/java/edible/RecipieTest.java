package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;

class RecipieTest {
	
	final static Ingredient FIRE_ROOT = new Ingredient("Fire Root", "Increases power of potions");
	final static Ingredient CLAW_OF_HIPOGRIFF = new Ingredient("Claw of Hipogriff", "Rare poisonous ingredient");
	final static Ingredient MORNING_STAR = new Ingredient("Morning Star", "Flower used in potions effecting health");
	final static Potion GREEN_VENOM = new Potion("Green Venom", "Sabbotages health on entering bloodstream", 0, -10, 0);
	final static Ingredient[] GREEN_VENOM_INGREDIENTS = {FIRE_ROOT, CLAW_OF_HIPOGRIFF, MORNING_STAR};
	final static Recipie GREEN_VENOM_RECIPIE = new Recipie("Green Venom Recipie", 
			"Recipie for potion sabbotaging health on entering bloodstream", GREEN_VENOM, GREEN_VENOM_INGREDIENTS, 50, 20);

	@Test
	void constructorSetsAttributes() {
		assertEquals("Green Venom Recipie", GREEN_VENOM_RECIPIE.getName());
		assertEquals("Recipie for potion sabbotaging health on entering bloodstream", GREEN_VENOM_RECIPIE.getDescription());
		assertEquals(GREEN_VENOM, GREEN_VENOM_RECIPIE.getPotion());
		assertEquals(FIRE_ROOT, GREEN_VENOM_RECIPIE.getIngredients()[0]);
		assertEquals(CLAW_OF_HIPOGRIFF, GREEN_VENOM_RECIPIE.getIngredients()[1]);
		assertEquals(MORNING_STAR, GREEN_VENOM_RECIPIE.getIngredients()[2]);
		assertEquals(50, GREEN_VENOM_RECIPIE.getManaPointToCook());
		assertEquals(20, GREEN_VENOM_RECIPIE.getExperiencePointToCook());
	}
	
//	@ParameterizedTest
//	@CsvSource({"Fire Root, Increases power of potions", "Claw of Hipogriff, Rare poisonous ingredient", "Morning Star, Flower used in potions effecting health"})
//	void constructorSetsAttributeIngredientArray(String name, String description) {
//		
//	}
	

}
