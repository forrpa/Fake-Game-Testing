package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import quest.TalkToGuildLeader;

class InventoryTest {
	
	static int FLY_AGARIC_COUNT = 0;
	final static Item FLY_AGARIC = new Edible("Fly Agaric", "Poisonous mushroom with magic powers", 4, -3, 0);
	final static Inventory INVENTORY = new Inventory();
	
	@RepeatedTest(10)
	void addItemofSameTypeIncreasesCountByOne() {
		INVENTORY.addItem(FLY_AGARIC);
		FLY_AGARIC_COUNT++;
		assertEquals(FLY_AGARIC_COUNT,INVENTORY.getCount(FLY_AGARIC));
	}
	
	@RepeatedTest(9)
	void getOutItemNotLastOfTypeDecreasesCountByOne() {
		INVENTORY.getOutItem(FLY_AGARIC);
		FLY_AGARIC_COUNT--;
		assertEquals(FLY_AGARIC_COUNT,INVENTORY.getCount(FLY_AGARIC));
	}
	
	@Test
	void getOutItemNotExisitingThrowsNPE() {
		assertThrows(NullPointerException.class, () -> INVENTORY.getOutItem(
				new ForbiddenFruit("Lucky Cherry", "Eating cherry starts quest Talk to Guild leader", new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true))));
	}
	
	@Test
	void getOutItemInvetoryEmptyThrowsNPE() {
		assertThrows(NullPointerException.class, () -> {
			Inventory inventory = new Inventory();
			inventory.getOutItem(new Ingredient("Fire Root", "Increases power of potions"));
			// way to check message of null pointer exception?
		});
	}
	
	@Test
	void toStringInformsOfEmptyInevntory() {
		final Inventory inventory = new Inventory();
		assertEquals("Inventory is empty!", inventory.toString());
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		final Inventory inventory = new Inventory();
		inventory.addItem(FLY_AGARIC);
		inventory.addItem(FLY_AGARIC);
		inventory.addItem(new Ingredient("Fire Root", "Increases power of potions"));
		inventory.addItem(new ForbiddenFruit("Lucky Cherry", "Eating cherry starts quest Talk to Guild leader", new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true)));
		inventory.addItem(new Potion("Heartbreak Potion", "Weakens magic powers, sabbotages health and gives life experience", -3, -10, 6));
		inventory.addItem(new Potion("Power Potion", "Boosts mana, health and experience", 5, 5, 5));
		assertEquals("{Fly Agaric: 2 }\n{Lucky Cherry: 1 }\n{Fire Root: 1 }\n{Power Potion: 1 }\n{Heartbreak Potion: 1 }", inventory.toString());
	}
	
	@Test
	void toStringFastEnough() {
		fail("Not yet implemented");
	}
}
