package equipment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BucklerOfUselessnessSimpleAttributeAndMethodTests {
	@Test
	void testIfArmorSlotIsCorrect() {
		Shield bou = new BucklerOfUselessness();
		assertTrue(bou.getSlot().equals("shield"));
	}
	@Test
	void testIfArmorTypeIsCorrect() {
		Shield bou = new BucklerOfUselessness();
		assertEquals(ArmorType.SHIELD, bou.getArmorType());
	}
}
