package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import equipment.BucklerOfUselessness;
import equipment.Shield;
import item.Item;

class ItemTest {

	final static Item ROYAL_STEEL_SHIELD = new Shield("Royal Steel Shield", 
			"Strong shield made of steel from The Metal Kingdom's steel", 2, 10, 40, 40, 0, 0, 0);
	final static Item MAGIC_HEALING_MUSHROOM = new Edible("Magic Healing Mushroom", 
			"Small increases in magic capability and substantial health boost", 2, 6, 0);
	final static Item BUCKLER_OF_USELESSNESS = new BucklerOfUselessness();
	final static int ZERO_DEFAULT_LEVEL = 0;

	@Test
	void constructorAsSuperSetsAttributes() {
		assertEquals("Royal Steel Shield", ROYAL_STEEL_SHIELD.getName());
		assertEquals("Strong shield made of steel from The Metal Kingdom's steel", ROYAL_STEEL_SHIELD.getDescription());
		assertEquals(2, ROYAL_STEEL_SHIELD.getRequiredLevel());
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
	void equalsOverridesEqualsMethodForSubclassesofItem() {
		BucklerOfUselessness bOfU = (BucklerOfUselessness) BUCKLER_OF_USELESSNESS;
		Shield shield = new Shield(bOfU.getName(), bOfU.getDescription(), bOfU.getRequiredLevel(), 
				bOfU.getArmor(), bOfU.getDurability(), bOfU.getAttributes()[0], bOfU.getAttributes()[1], 
				bOfU.getAttributes()[2], bOfU.getAttributes()[3]);
		assertTrue(shield.equals(bOfU));
		assertTrue(shield.equals(BUCKLER_OF_USELESSNESS));
	}
	
	@ParameterizedTest
	@CsvSource({"1, 1, 1", "10, 10, 10", "1, 2, 3"})
	void equalsMatchesOnlyNameDescriptionAndRequiredLevelAttributes(int manaPoint, int healthPoint, int experiencePoint) {
		Edible mHM = (Edible) MAGIC_HEALING_MUSHROOM;
		Edible edible = new Edible(mHM.getName(), mHM.getDescription(), manaPoint, healthPoint, experiencePoint);
		assertTrue(edible.equals(mHM));
	}
	
	@Test
	void hashCodeOverridesEqualsMethodForSubclassesofItem() {
		assertEquals(41238703, BUCKLER_OF_USELESSNESS.hashCode());
	}
	
	@ParameterizedTest
	@CsvSource({"-1, -1, -1", "-10, -5, 0", "3, 4, 9"})
	void hashCodeHashesOnlyNameDescriptionAndRequiredLevelAttributes(int manaPoint, int healthPoint, int experiencePoint) {
		Edible edible = new Edible("Magic Healing Mushroom", "Small increases in magic capability and substantial health boost", manaPoint, healthPoint, experiencePoint);
		assertEquals(1112343108, edible.hashCode());
	}
}
