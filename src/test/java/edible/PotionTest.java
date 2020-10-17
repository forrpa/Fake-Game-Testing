package edible;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PotionTest {

	final static Potion LOVE_BREW = new Potion("Love Potion", "Boosts magic power and boosts health", 5, 2, 0);

	@Test
	void constructorSetsAttributes() {
		assertEquals("Love Potion", LOVE_BREW.getName());
		assertEquals("Boosts magic power and boosts health", LOVE_BREW.getDescription());
		assertEquals(5, LOVE_BREW.getManaPoint());
		assertEquals(2, LOVE_BREW.getHealthPoint());
		assertEquals(0, LOVE_BREW.getExperiencePoint());
	}
}
