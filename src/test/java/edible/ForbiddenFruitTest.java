package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quest.Quest;
import quest.TalkToGuildLeader;

class ForbiddenFruitTest {

	final static Quest QUEST = new TalkToGuildLeader();
	final static ForbiddenFruit RED_APPLE = new ForbiddenFruit("Red Apple", "Eating apple starts quest Talk to Guild Leader", QUEST);
	final static int TEN_MAX_EDIBLE_POINT_VALUE = 10;
	final static Potion POWER_POTION = new Potion("Power Potion", "Boosts mana, health and experience", 5, 5, 5);
	final static Potion HEARTBREAK_POTION = new Potion("Heartbreak Potion", "Weakens magic powers, sabbotages health and gives life experience", -3, -10, 6);
	
	@Test
	void constructorSetsAttributes() {
		assertEquals("Red Apple", RED_APPLE.getName());
		assertEquals("Eating apple starts quest Talk to Guild Leader", RED_APPLE.getDescription());
		assertEquals(TEN_MAX_EDIBLE_POINT_VALUE, RED_APPLE.getManaPoint());
		assertEquals(TEN_MAX_EDIBLE_POINT_VALUE, RED_APPLE.getHealthPoint());
		assertEquals(TEN_MAX_EDIBLE_POINT_VALUE, RED_APPLE.getExperiencePoint());
		assertEquals(QUEST, RED_APPLE.getQuestUnlocked());
		assertNull(RED_APPLE.getPoison());
	}
	
	@BeforeEach
	void restPoisonNull() {
		RED_APPLE.setPoison(null);
	}
	
	@Test
	void setPoisonDifferentTypePotionReplacesPoisonAttribute() {
		RED_APPLE.setPoison(POWER_POTION); 
		assertEquals(POWER_POTION, RED_APPLE.getPoison());
		RED_APPLE.setPoison(HEARTBREAK_POTION);
		assertEquals(HEARTBREAK_POTION, RED_APPLE.getPoison());
	}
	
	@Test
	void setPoisonSamePotionThrowsIAE() {
		RED_APPLE.setPoison(POWER_POTION);
		assertThrows(IllegalArgumentException.class, () -> RED_APPLE.setPoison(POWER_POTION));
	}
	
	@Test
	void consumeOverridesEdibleConsume() {
		int[] returnPoints = RED_APPLE.consume(0, 55, -2);
		assertEquals(0 + RED_APPLE.getManaPoint(), returnPoints[0]);
		assertEquals(55 + RED_APPLE.getHealthPoint(), returnPoints[1]);
		assertEquals(-2 + RED_APPLE.getExperiencePoint(), returnPoints[2]);
	}
	
	@Test
	void consumePoisonNotNullReturnsPointPlusPotionPoint() {
		RED_APPLE.setPoison(POWER_POTION);
		int[] returnPoints = RED_APPLE.consume(0, 55, -2);
		assertEquals(0 + POWER_POTION.getManaPoint(), returnPoints[0]);
		assertEquals(55 + POWER_POTION.getHealthPoint(), returnPoints[1]);
		assertEquals(-2 + POWER_POTION.getExperiencePoint(), returnPoints[2]);
	}

	@Test
	void consumeSetsPotionToNull() {
		RED_APPLE.setPoison(POWER_POTION);
		RED_APPLE.consume(0, 0, 0);
		assertNull(RED_APPLE.getPoison());
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		assertEquals("\"Red Apple: Eating apple starts quest Talk to Guild Leader\", "
				+ "Mana: 10, Health: 10, Experience: 10, Quest Unlocked: Talk to Guild Leader", RED_APPLE.toString());
	}
}
