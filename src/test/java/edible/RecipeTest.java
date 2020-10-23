package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RecipeTest {
	
	final static Ingredient FIRE_ROOT = new Ingredient("Fire Root", "Increases power of potions");
	final static Ingredient CLAW_OF_HIPOGRIFF = new Ingredient("Claw of Hipogriff", "Rare poisonous ingredient");
	final static Ingredient MORNING_STAR = new Ingredient("Morning Star", "Flower used in potions effecting health");
	final static Potion GREEN_VENOM = new Potion("Green Venom", "Sabbotages health on entering bloodstream", 0, -10, 0);
	final static Ingredient[] GREEN_VENOM_INGREDIENTS = {FIRE_ROOT, CLAW_OF_HIPOGRIFF, MORNING_STAR};
	final static int MANA_POINT_TO_COOK = 50;
	final static int EXPERIENCE_POINT_TO_COOK = 20;
	final static Recipe GREEN_VENOM_RECIPIE = new Recipe("Green Venom Recipie", 
			"Recipie for potion sabbotaging health on entering bloodstream", GREEN_VENOM, GREEN_VENOM_INGREDIENTS, MANA_POINT_TO_COOK, EXPERIENCE_POINT_TO_COOK);

	@Test
	void constructorSetsAttributes() {
		assertEquals("Green Venom Recipie", GREEN_VENOM_RECIPIE.getName());
		assertEquals("Recipie for potion sabbotaging health on entering bloodstream", GREEN_VENOM_RECIPIE.getDescription());
		assertEquals(GREEN_VENOM, GREEN_VENOM_RECIPIE.getPotion());
		assertEquals(FIRE_ROOT, GREEN_VENOM_RECIPIE.getIngredients()[0]);
		assertEquals(CLAW_OF_HIPOGRIFF, GREEN_VENOM_RECIPIE.getIngredients()[1]);
		assertEquals(MORNING_STAR, GREEN_VENOM_RECIPIE.getIngredients()[2]);
		assertEquals(MANA_POINT_TO_COOK, GREEN_VENOM_RECIPIE.getManaPointToCook());
		assertEquals(EXPERIENCE_POINT_TO_COOK, GREEN_VENOM_RECIPIE.getExperiencePointToCook());
	}
	
	@ParameterizedTest
	@CsvSource({"50, 20", "51, 21", "1000, 1000"})
	void enoughManaOrExperiencePointToCookReturnsPotion(int manaPoint, int experiencePoint) {
		assertTrue(MANA_POINT_TO_COOK <= manaPoint || EXPERIENCE_POINT_TO_COOK <= experiencePoint);
		assertEquals(GREEN_VENOM, GREEN_VENOM_RECIPIE.cook(manaPoint, experiencePoint));
	}
	
	@ParameterizedTest
	@CsvSource({"49, 19", "49, 20", "50, 19", "-50, -20"})
	void tooFewManaOrExperiencePointToCookThrowsISE(int manaPoint, int experiencePoint) {
		assertTrue(manaPoint < MANA_POINT_TO_COOK || experiencePoint < EXPERIENCE_POINT_TO_COOK);
		assertThrows(IllegalStateException.class, () -> GREEN_VENOM_RECIPIE.cook(manaPoint, experiencePoint));
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		assertEquals("\"Green Venom Recipie: Recipie for potion sabbotaging health on entering bloodstream\", "
				+ "Ingredients: [Fire Root, Claw of Hipogriff, Morning Star], "
				+ "Mana To Cook: 50, Experience To Cook: 20", GREEN_VENOM_RECIPIE.toString());
	}
	

}
