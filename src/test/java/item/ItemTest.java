package item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
		assertEquals(2, ROYAL_STEEL_SHIELD.getRequiredUnitLevel());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {-1, -2, -100, 41, 50, 1000})
	void constructorAsSuperRequiredLevelOutOfRangeTrhowsIAE(int requiredUnitLevel) {
		final Shield rSS = (Shield) ROYAL_STEEL_SHIELD;
		assertTrue(requiredUnitLevel < ZERO_DEFAULT_LEVEL || 40 < requiredUnitLevel);
		assertThrows(IllegalArgumentException.class, () -> new Shield(rSS.getName(), rSS.getDescription(), requiredUnitLevel,
				rSS.getArmor(), rSS.getDurability(), rSS.getAttributes()[0], rSS.getAttributes()[1], rSS.getAttributes()[2], 
				rSS.getAttributes()[3]));
	}
	
	@Test
	void constructorAsSuperNullNameAttributeThrowsIAE() {
		final Shield rSS = (Shield) ROYAL_STEEL_SHIELD;
		assertThrows(IllegalArgumentException.class, () -> new Shield(rSS.getName(), null, rSS.getRequiredUnitLevel(), rSS.getArmor(), 
				rSS.getDurability(), rSS.getAttributes()[0], rSS.getAttributes()[1], rSS.getAttributes()[2], rSS.getAttributes()[3]));
	}
	
	@Test
	void constructorAsSuperNullDescriptionAttributeThrowsIAE() {
		final Shield rSS = (Shield) ROYAL_STEEL_SHIELD;
		assertThrows(IllegalArgumentException.class, () -> new Shield(null, rSS.getDescription(), rSS.getRequiredUnitLevel(), rSS.getArmor(), 
				rSS.getDurability(), rSS.getAttributes()[0], rSS.getAttributes()[1], rSS.getAttributes()[2], rSS.getAttributes()[3]));
	}
	
	
	@Test
	void constructorMinimalAsSuperSetsAttributesAndDefaultRequiredLevelZero() {
		assertEquals("Magic Healing Mushroom", MAGIC_HEALING_MUSHROOM.getName());
		assertEquals("Small increases in magic capability and substantial health boost", MAGIC_HEALING_MUSHROOM.getDescription());
		assertEquals(ZERO_DEFAULT_LEVEL, MAGIC_HEALING_MUSHROOM.getRequiredUnitLevel());
	}
	
	@Test
	void constructorMinimalAsSuperSetsDefaultRequiredLevelZero() {
		assertEquals(0, MAGIC_HEALING_MUSHROOM.getRequiredUnitLevel());
	}
	
	@Test
	void constructorMinimalAsSuperNullNameAttributeThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Ingredient(null, CLAW_OF_HIPOGRIFF.getDescription()));
	}
	
	@Test
	void constructorMinimalAsSuperNullDescriptionAttributeThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> new Ingredient(CLAW_OF_HIPOGRIFF.getName(), null));
	}
	
	@Test
	void equalsMatchesOnlyInstancesOfItemOrSubclasses() {
		class MockItem {
			public String name;
			public String description;
			public int requiredLevel;
			
			MockItem(String name, String description, int requiredLevel) {
				this.name = name;
				this.description = description;
				this.requiredLevel = requiredLevel;
			}
		}
		final MockItem mockItem = new MockItem(CLAW_OF_HIPOGRIFF.getName(), CLAW_OF_HIPOGRIFF.getDescription(), 
				CLAW_OF_HIPOGRIFF.getRequiredUnitLevel());
		assertFalse(CLAW_OF_HIPOGRIFF.equals(mockItem));
	}
	
	@Test
	void equalsRequiresIdenticalNameDescriptionAndRequiredLevel() {
		final String name = ROYAL_STEEL_SHIELD.getName();
		final String description = ROYAL_STEEL_SHIELD.getDescription();
		final int requiredUnitLevel = ROYAL_STEEL_SHIELD.getRequiredUnitLevel();
		final Shield royalSteelShield = (Shield) ROYAL_STEEL_SHIELD;
		final int armor = royalSteelShield.getArmor();
		final int durability = royalSteelShield.getDurability();
		final int strength = royalSteelShield.getAttributes()[0];
		final int agility = royalSteelShield.getAttributes()[1];
		final int intelligence = royalSteelShield.getAttributes()[2];
		final int stamina = royalSteelShield.getAttributes()[3];
		final Shield royalSteelShieldNameLowerCase = new Shield(name.toLowerCase(), description, requiredUnitLevel, armor, durability, strength,
				agility, intelligence, stamina);
		final Shield royalSteelShieldDescriptionUpperCase = new Shield(name, description.toUpperCase(), requiredUnitLevel, armor, durability, 
				strength, agility, intelligence, stamina);
		final Shield royalSteelShieldRequiredUnitLevelPlusOne = new Shield(name, description, requiredUnitLevel + 1, armor, 
				durability, strength, agility, intelligence, stamina);
		assertTrue(ROYAL_STEEL_SHIELD.equals(royalSteelShield));
		assertFalse(ROYAL_STEEL_SHIELD.equals(royalSteelShieldNameLowerCase));
		assertFalse(ROYAL_STEEL_SHIELD.equals(royalSteelShieldDescriptionUpperCase));
		assertFalse(ROYAL_STEEL_SHIELD.equals(royalSteelShieldRequiredUnitLevelPlusOne));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {10, 1, 0, 2, 6, 77})
	void equalsMatchesObjectsOfSubclassesBasedOnNameDescriptionAndRequiredLevelAttributes(int point) {
		final BucklerOfUselessness bOfU = (BucklerOfUselessness) BUCKLER_OF_USELESSNESS;
		final Shield shield = new Shield(bOfU.getName(), bOfU.getDescription(), bOfU.getRequiredUnitLevel(), 
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
