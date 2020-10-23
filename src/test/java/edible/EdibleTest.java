package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

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
	
	@Test
	void consumeReturnsNewPlayerPoint() {
		// equivalence classes build-out test: 0, negative, positive in range....so far only one test per edible to test class checking
		// then min and max limits should be set in Player class and MagicPlayer class method setXPoint
		// new test for assertThrow Illegal state "Game over" or "roof reached for X point reached for type of player"
		fail("Not yet implemented");
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		assertEquals("\"1-Up Cereal: 1-Up\","
				+ " Mana: 0, Health: 10, Experience: 0", ONE_UP_CEREAL.toString());
	}

}
