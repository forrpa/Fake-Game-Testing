import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class EdibleTest {
	
	final static Edible ITEM = new Edible("Name", "Description", 0, 0, 0);

	@Test
	void constructorSetsAttributes() {
		assertEquals("Name", ITEM.getName());
		assertEquals("Description", ITEM.getDescription());
		assertEquals(0, ITEM.getManaPoint());
		assertEquals(0, ITEM.getManaPoint());
		assertEquals(0, ITEM.getManaPoint());
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
