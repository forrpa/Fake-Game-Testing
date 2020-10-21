package item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import edible.Edible;
import edible.ForbiddenFruit;
import edible.Ingredient;
import edible.Potion;
import quest.TalkToGuildLeader;

class InventoryTest {
	
	final static Inventory INVENTORY = new Inventory();
	final static Edible FLY_AGARIC = new Edible("Fly Agaric", "Poisonous mushroom with magic powers", 4, -3, 0);
	final static Ingredient FIRE_ROOT = new Ingredient("Fire Root", "Increases power of potions");
	final static ForbiddenFruit LUCKY_CHERRY = new ForbiddenFruit("Lucky Cherry", "Eating cherry starts quest Talk to Guild leader", 
			new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true));
	static int FLY_AGARIC_COUNT = 0;
	
	@BeforeAll
	static void setFlyAgaricCountTo10() throws Exception {
		for(int i = 0; i < 10; i++) {
			INVENTORY.addItem(FLY_AGARIC);
			FLY_AGARIC_COUNT++;
		}
		INVENTORY.addItem(FIRE_ROOT);
	}
	
	@RepeatedTest(9)
	void getOutItemNotLastOfTypeDecreasesCountByOne() {
		INVENTORY.getOutItem(FLY_AGARIC);
		FLY_AGARIC_COUNT--;
		assertEquals(FLY_AGARIC_COUNT,INVENTORY.getCount(FLY_AGARIC));
	}

	@RepeatedTest(10)
	void addItemofSameTypeIncreasesCountByOne() {
		INVENTORY.addItem(FLY_AGARIC);
		FLY_AGARIC_COUNT++;
		assertEquals(FLY_AGARIC_COUNT,INVENTORY.getCount(FLY_AGARIC));
	}
	
	@Test
	void getOutItemLastOfTypeRemovesKey() {
		assertEquals(1, INVENTORY.getCount(FIRE_ROOT));
		INVENTORY.getOutItem(FIRE_ROOT);
		assertFalse(INVENTORY.isInInventory(FIRE_ROOT));
	}
	
	@Test
	void getOutItemNotInInventoryThrowsNPE() {
		assertThrows(NullPointerException.class, () -> INVENTORY.getOutItem(LUCKY_CHERRY));
	}
	
	@Test
	void getOutItemInvetoryEmptyThrowsNPE() {
		assertThrows(NullPointerException.class, () -> new Inventory().getOutItem(FLY_AGARIC));
			// way to check message of null pointer exception?
	}
	
	@Test
	void toStringInformsOfEmptyInevntory() {
		assertEquals("Inventory is empty!", new Inventory().toString());
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		INVENTORY.addItem(FIRE_ROOT);
		INVENTORY.addItem(LUCKY_CHERRY);
		INVENTORY.addItem(new Potion("Heartbreak Potion", "Weakens magic powers, sabbotages health and gives life experience", -3, -10, 6));
		INVENTORY.addItem(new Potion("Power Potion", "Boosts mana, health and experience", 5, 5, 5));
		assertEquals("{Heartbreak Potion: 1 }\n{Fire Root: 1 }\n{Lucky Cherry: 1 }\n{Power Potion: 1 }\n{Fly Agaric: 11 }", INVENTORY.toString());
	}
	
	@Test
	void toStringFastEnough() {
		fail("Not yet implemented");
	}
}
