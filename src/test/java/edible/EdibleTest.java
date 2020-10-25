package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EdibleTest {
	
	final static Edible ONE_UP_CEREAL = new Edible("1-Up Cereal", "1-Up", 0, 10, 0);

	@Test
	void constructorSetsAttributes() {
		assertEquals("1-Up Cereal", ONE_UP_CEREAL.getName());
		assertEquals("1-Up", ONE_UP_CEREAL.getDescription());
		assertEquals(0, ONE_UP_CEREAL.getManaPoint());
		assertEquals(10, ONE_UP_CEREAL.getHealthPoint());
		assertEquals(0, ONE_UP_CEREAL.getExperiencePoint());
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
	
	@ParameterizedTest
	@ValueSource(ints = {-10, -2, -1, 0, 1, 4, 9, 10})
	void consumeReturnsPointPlusEdiblePoint(int point) {
		Edible edible = new Edible("Lucky Or Unlucky Popsicle", "Surprise health, experience and mana point!", point, point, point);
		int[] returnPoints = edible.consume(100, -1, 26);
		assertEquals(100 + point, returnPoints[0]);
		assertEquals(-1 + point, returnPoints[1]);
		assertEquals(26 + point, returnPoints[2]);
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		assertEquals("\"1-Up Cereal: 1-Up\","
				+ " Mana: 0, Health: 10, Experience: 0", ONE_UP_CEREAL.toString());
	}

}
