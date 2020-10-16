package edible;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EdibleTest {
	
	final static Edible MAGIC_HEALING_MUSHROOM = new Edible("Magic Healing Mushroom", "Small increases in magic capability and substantial health boost", 2, 6, 0);

	@Test
	void constructorSetsAttributes() {
		assertEquals("Magic Healing Mushroom", MAGIC_HEALING_MUSHROOM.getName());
		assertEquals("Small increases in magic capability and substantial health boost", MAGIC_HEALING_MUSHROOM.getDescription());
		assertEquals(2, MAGIC_HEALING_MUSHROOM.getManaPoint());
		assertEquals(6, MAGIC_HEALING_MUSHROOM.getHealthPoint());
		assertEquals(0, MAGIC_HEALING_MUSHROOM.getExperiencePoint());
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
		assertEquals("\"Magic Healing Mushroom: Small increases in magic capability and substantial health boost\", Mana: 2, Health: 6, Experience: 0", MAGIC_HEALING_MUSHROOM.toString());
	}

}
