package edible;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EdibleTest {
	
	final static Edible ITEM = new Edible("Magic Healing Mushroom", "Small increases in magic capability and substantial health boost", 2, 6, 0);

	@Test
	void constructorSetsAttributes() {
		assertEquals("Magic Healing Mushroom", ITEM.getName());
		assertEquals("Small increases in magic capability and substantial health boost", ITEM.getDescription());
		assertEquals(2, ITEM.getManaPoint());
		assertEquals(6, ITEM.getHealthPoint());
		assertEquals(0, ITEM.getExperiencePoint());
	}

	@Test
	void checkPointValueOutOfRangeTrhowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Edible("", "", -11, 0, 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Edible("", "", 0, 11, 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Edible("", "", 0, 0, Integer.MAX_VALUE);
		});
	}
	
	@Test
	void checkToString() {
		assertEquals("\"Magic Healing Mushroom: Small increases in magic capability and substantial health boost\", Mana: 2, Health: 6, Experience: 0", ITEM.toString());
	}

}
