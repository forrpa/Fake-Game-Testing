package edible;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InventoryTest {
	
	final static Item FLY_AGARIC = new Edible("Fly Agaric", "A poisonous mushroom with magic powers", 3, -1, 0);
	final static Inventory INVENTORY = new Inventory();
	
	@Test
	void constructorSetsAttributes() {
		fail("Not yet implemented");
	}
	
	@ParameterizedTest
	@CsvSource({"Love Potion, Weakens magic powers", "Love Potion, Weakens magic powers", "Fortune Cookie, Gives wisdom"})
	
	@Test
	void addItemOfNewTypeMakesNewKey() {
		fail("Not yet implemented");
	}
	
	@RepeatedTest(10)
	void addItemofSameTypeIncreasesCountByOne() {
		fail("Not yet implemented");
	}
	
	@Test
	void removeLastItemOfTypeRemovesKey() {
		fail("Not yet implemented");
	}
	
	@RepeatedTest(10)
	void removeItemNotLastOfTypeDecreasesCountByOne() {
		fail("Not yet implemented");
	}
	
	@Test
	void removeItemNotExisitingThrowsIAE() {
		fail("Not yet implemented");
	}
	
	@Test
	void removeItemInvetoryEmptyThrowsISE() {
		fail("Not yet implemented");
	}
	
	@ParameterizedTest
	@CsvSource({"Love Potion, Weakens magic powers", "Love Potion, Weakens magic powers", "Fortune Cookie, Gives wisdom"})
	void toStringFormatsAttributesCorrect(String name, String description) {
		Item item = new Edible(name, description, 0, 0, 0);
		fail("Not yet implemented");
	}

	
}
