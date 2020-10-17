package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import quest.TalkToGuildLeader;

class InventoryTest {
	
	final static Item FLY_AGARIC = new Edible("Fly Agaric", "A poisonous mushroom with magic powers", 3, -1, 0);
	final static Item LOVE_POTION = new Edible("Love Potion", "Boosts magic powers and boosts health", 4, 4, 0);
	final static Item HEARTBREAK_POTION = new Edible("Heartbreak Potion", "Weakens magic powers, sabbotages health and gives life experience", -3, -10, 6);
	final static Item POWER_POTION = new Edible("Power Potion", "Boosts all points", 5, 5, 5);
	final static Item SUPER_SMOOTHIE = new Edible("Super Smoothie", "1-Up", 0, 10, 0);
	final static Item FORTUNE_COOKIE = new Edible("Fortune Cookie", "Hides a wisdom", 0, 0, 1);
	final static Item LUCKY_CHERRY = new ForbiddenFruit("Lucky Cherry", "Eating cherry starts quest Talk to Guild leader", new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true));
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
	void getOutItemLastOfTypeRemovesKey() {
		fail("Not yet implemented");
	}
	
	@RepeatedTest(10)
	void getOutItemNotLastOfTypeDecreasesCountByOne() {
		fail("Not yet implemented");
	}
	
	@Test
	void getOutItemNotExisitingThrowsIAE() {
		fail("Not yet implemented");
	}
	
	@Test
	void getOutItemInvetoryEmptyThrowsISE() {
		fail("Not yet implemented");
	}
	
	@Test
	void toStringInformsOfEmptyInevntory() {
		assertEquals("Inventory is empty!", INVENTORY.toString());
	}
	
	@ParameterizedTest
	@CsvSource({"Love Potion, Weakens magic powers", "", "Fortune Cookie, Gives wisdom"})
	void toStringFormatsAttributesCorrect() {
		INVENTORY.addItem(new Edible("Lucky Cherry", "", 0, 0, 0));
		INVENTORY.addItem(new Edible("Power Potion", "Boosts all points", 0, 0, 0));
		INVENTORY.addItem(new Edible("Love Potion", "Weakens magic powers", 0, 0, 0));
		INVENTORY.addItem(FLY_AGARIC);
		INVENTORY.addItem(FLY_AGARIC);
		assertEquals()
	}
	
}
