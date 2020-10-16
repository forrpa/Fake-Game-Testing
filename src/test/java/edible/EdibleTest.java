package edible;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EdibleTest {
	
	final static Edible ITEM = new Edible("Magic Healing Mushroom", "Small increases in magic capability and substantial health boost", 2, 6, 0);

	@Test
	void constructorSetsAttributes() {
		assertEquals("Name", ITEM.getName());
		assertEquals("Description", ITEM.getDescription());
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
		assertEquals("\"Name: Description\", Mana: 0, Health: 0, Experience: 0", ITEM.toString());
	}

}
