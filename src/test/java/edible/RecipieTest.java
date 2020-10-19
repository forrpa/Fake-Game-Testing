package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RecipieTest {
	
	final static Ingredient FIRE_ROOT = new Ingredient("Fire Root", "Increases power of potions");
	final static Ingredient CLAW_OF_HIPOGRIFF = new Ingredient("Claw of Hipogriff", "Rare poisonous ingredient");
	final static Ingredient MORNING_STAR = new Ingredient("Morning Star", "Flower used in potions effecting health");
	final static Potion GREEN_VENOM = new Potion("Green Venom", "Sabbotages health on entering bloodstream", 0, -10, 0);
	final static Ingredient[] GREEN_VENOM_INGREDIENTS = {FIRE_ROOT, CLAW_OF_HIPOGRIFF, MORNING_STAR};
	final static int MANA_POINT_TO_COOK = 50;
	final static int EXPERIENCE_POINT_TO_COOK = 20;
	final static Recipie GREEN_VENOM_RECIPIE = new Recipie("Green Venom Recipie", 
			"Recipie for potion sabbotaging health on entering bloodstream", GREEN_VENOM, GREEN_VENOM_INGREDIENTS, MANA_POINT_TO_COOK, EXPERIENCE_POINT_TO_COOK);

	@Test
	void constructorSetsAttributes() {
		final Potion potion = new Potion("Green Venom", "Sabbotages health on entering bloodstream", 0, -10, 0);
		final Ingredient ingredient = new Ingredient("Fire Root", "Increases power of potions");
		final Ingredient ingredient1 = new Ingredient("Claw of Hipogriff", "Rare poisonous ingredient");
		final Ingredient ingredient2 = new Ingredient("Morning Star", "Flower used in potions effecting health");
		assertEquals("Green Venom Recipie", GREEN_VENOM_RECIPIE.getName());
		assertEquals("Recipie for potion sabbotaging health on entering bloodstream", GREEN_VENOM_RECIPIE.getDescription());
		assertEquals(potion, GREEN_VENOM_RECIPIE.getPotion());
		assertEquals(ingredient, GREEN_VENOM_RECIPIE.getIngredients()[0]);
		assertEquals(ingredient1, GREEN_VENOM_RECIPIE.getIngredients()[1]);
		assertEquals(ingredient2, GREEN_VENOM_RECIPIE.getIngredients()[2]);
		assertEquals(50, GREEN_VENOM_RECIPIE.getManaPointToCook());
		assertEquals(20, GREEN_VENOM_RECIPIE.getExperiencePointToCook());
	}
	
	@ParameterizedTest
	@CsvSource({"50, 20", "51, 21"})
	void enoughManaOrExperiencePointToCookReturnsPotion(int manaPoint, int experiencePoint) {
		Potion potion = new Potion("Green Venom", "Sabbotages health on entering bloodstream", 0, -10, 0);
		assertEquals(potion, GREEN_VENOM_RECIPIE.cook(manaPoint, experiencePoint));
	}
	
	@Test
	void tooFewManaOrExperiencePointToCookThrowsISE() {
		assertThrows(IllegalStateException.class, () -> GREEN_VENOM_RECIPIE.cook(49, 19));
		assertThrows(IllegalStateException.class, () -> GREEN_VENOM_RECIPIE.cook(49, EXPERIENCE_POINT_TO_COOK));
		assertThrows(IllegalStateException.class, () -> GREEN_VENOM_RECIPIE.cook(MANA_POINT_TO_COOK, 19));
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		assertEquals("\"Green Venom Recipie: Recipie for potion sabbotaging health on entering bloodstream\", "
				+ "Ingredients: [Fire Root, Claw of Hipogriff, Morning Star], "
				+ "Mana To Cook: 50, Experience To Cook: 20", GREEN_VENOM_RECIPIE.toString());
	}
	

}
