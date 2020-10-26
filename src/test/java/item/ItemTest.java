package item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import edible.Edible;
import edible.Ingredient;
import equipment.BucklerOfUselessness;
import equipment.Shield;

class ItemTest {

	final static Item ROYAL_STEEL_SHIELD = new Shield("Royal Steel Shield", 
			"Strong shield made of steel from The Metal Kingdom's steel", 2, 10, 40, 40, 0, 0, 0);
	final static Item MAGIC_HEALING_MUSHROOM = new Edible("Magic Healing Mushroom", 
			"Small increases in magic capability and substantial health boost", 2, 6, 0);
	final static Item BUCKLER_OF_USELESSNESS = new BucklerOfUselessness();
	final static Item CLAW_OF_HIPOGRIFF = new Ingredient("Claw of Hipogriff", "Rare poisonous ingredient");
	final static int ZERO_DEFAULT_LEVEL = 0;

	@Test
	void constructorAsSuperSetsAttributes() {
		assertEquals("Royal Steel Shield", ROYAL_STEEL_SHIELD.getName());
		assertEquals("Strong shield made of steel from The Metal Kingdom's steel", ROYAL_STEEL_SHIELD.getDescription());
		assertEquals(2, ROYAL_STEEL_SHIELD.getRequiredLevel());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {-1, -2, -100})
	void constructorAsSuperRequiredLevelLessThanZeroTrhowsIAE(int requiredLevel) {
		final Shield rSS = (Shield) ROYAL_STEEL_SHIELD;
		assertTrue(requiredLevel < ZERO_DEFAULT_LEVEL);
		assertThrows(IllegalArgumentException.class, () -> {
			new Shield(rSS.getName(), rSS.getDescription(), requiredLevel, rSS.getArmor(), rSS.getDurability(), 
					rSS.getAttributes()[0], rSS.getAttributes()[1], rSS.getAttributes()[2], rSS.getAttributes()[3]);
		});
	}
	
	@Test
	void constructorMinimalAsSuperSetsAttributesAndDefaultRequiredLevelZero() {
		assertEquals("Magic Healing Mushroom", MAGIC_HEALING_MUSHROOM.getName());
		assertEquals("Small increases in magic capability and substantial health boost", MAGIC_HEALING_MUSHROOM.getDescription());
		assertEquals(ZERO_DEFAULT_LEVEL, MAGIC_HEALING_MUSHROOM.getRequiredLevel());
	}
	
	@Test
	void constructorMinimalAsSuperSetsDefaultRequiredLevelZero() {
		assertEquals(0, MAGIC_HEALING_MUSHROOM.getRequiredLevel());
	}
	
	@Test
	void equalsOverridesEqualsMethodForSubclasses() {
		final Ingredient clawOfHipogriff = (Ingredient) CLAW_OF_HIPOGRIFF;
		final Ingredient ingredient = new Ingredient(CLAW_OF_HIPOGRIFF.getName(), CLAW_OF_HIPOGRIFF.getDescription());
		assertTrue(ingredient.equals(clawOfHipogriff));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {-10, -1, 0, 2, 6, 77})
	void equalsMatchesObjectsOfSubclassesBasedOnNameDescriptionAndRequiredLevelAttributes(int point) {
		final BucklerOfUselessness bOfU = (BucklerOfUselessness) BUCKLER_OF_USELESSNESS;
		final Shield shield = new Shield(bOfU.getName(), bOfU.getDescription(), bOfU.getRequiredLevel(), 
				bOfU.getArmor() + point, bOfU.getDurability() + point, bOfU.getAttributes()[0] + point, bOfU.getAttributes()[1] + point, 
				bOfU.getAttributes()[2] + point, bOfU.getAttributes()[3] + point);
		assertTrue(shield.equals(bOfU));
	}
	
	@Test
	void hashCodeOverridesEqualsMethodForSubclasses() {
		assertEquals(41238703, BUCKLER_OF_USELESSNESS.hashCode());
	}
	
	@ParameterizedTest
	@CsvSource({"-1, -1, -1", "-10, -5, 0", "3, 4, 9"})
	void hashCodeHashesOnlyNameDescriptionAndRequiredLevelAttributes(int manaPoint, int healthPoint, int experiencePoint) {
		final Edible magicHealingMushroom = (Edible) MAGIC_HEALING_MUSHROOM;
		final Edible edible = new Edible(magicHealingMushroom.getName(), magicHealingMushroom.getDescription(), manaPoint, healthPoint, experiencePoint);
		assertEquals(1112343108, edible.hashCode());
	}
}
