package edible;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import item.Item;

class ItemTest {

	final static Item MAGIC_HEALING_MUSHROOM = new Edible("Magic Healing Mushroom", 
			"Small increases in magic capability and substantial health boost", 2, 6, 0);
	
	@ParameterizedTest
	@CsvSource({"1, 1, 1", "10, 10, 10", "1, 2, 3"})
	void equalsMatchesNameAndDescriptionAttributesOnly(int manaPoint, int healthPoint, int experiencePoint) {
		Item edible = new Edible("Magic Healing Mushroom", "Small increases in magic capability and substantial health boost", manaPoint, healthPoint, experiencePoint);
		assertTrue(edible.equals(MAGIC_HEALING_MUSHROOM));
	}
	
	@ParameterizedTest
	@CsvSource({"-1, -1, -1", "-10, -5, 0", "3, 4, 9"})
	void hashCodeHashesNameAndDescriptionAttributesOnly(int manaPoint, int healthPoint, int experiencePoint) {
		Item edible = new Edible("Magic Healing Mushroom", "Small increases in magic capability and substantial health boost", manaPoint, healthPoint, experiencePoint);
		assertEquals(312976700, edible.hashCode());
	}

}
