package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import quest.TalkToGuildLeader;

class InventoryTest {
	
	static int FLY_AGARIC_COUNT = 0;
	final static Item FLY_AGARIC = new Edible("Fly Agaric", "Poisonous mushroom with magic powers", 4, -3, 0);
	final static Item LUCKY_CHERRY = new ForbiddenFruit("Lucky Cherry", "Eating cherry starts quest Talk to Guild leader", new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true));	
	final static Item FIRE_ROOT = new Ingredient("Fire Root", "Increases power of potions");	
	final static Item HEARTBREAK_POTION = new Potion("Heartbreak Potion", "Weakens magic powers, sabbotages health and gives life experience", -3, -10, 6);
	final static Item POWER_POTION = new Potion("Power Potion", "Boosts mana, health and experience", 5, 5, 5);
	
	final static Inventory INVENTORY = new Inventory();
	
	@RepeatedTest(10)
	void addItemofSameTypeIncreasesCountByOne() {
		INVENTORY.addItem(FLY_AGARIC);
		FLY_AGARIC_COUNT++;
		assertEquals(FLY_AGARIC_COUNT,INVENTORY.getCount(FLY_AGARIC));
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
		final Inventory inventory = new Inventory();
		assertEquals("Inventory is empty!", inventory.toString());
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		INVENTORY.addItem(FLY_AGARIC);
		INVENTORY.addItem(FLY_AGARIC);
		INVENTORY.addItem(LUCKY_CHERRY);
		INVENTORY.addItem(HEARTBREAK_POTION);
		INVENTORY.addItem(POWER_POTION);
		assertEquals("{Fly Agaric: 2 }\n{Lucky Cherry: 1 }\n{Power Potion: 1 }\n{Heartbreak Potion: 1 }", INVENTORY.toString());
	}
	
	@Test
	void toStringFastEnough() {
		fail("Not yet implemented");
	}
}
