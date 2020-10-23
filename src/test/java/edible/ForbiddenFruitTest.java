package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import quest.Quest;
import quest.TalkToGuildLeader;

class ForbiddenFruitTest {

	final static Quest QUEST = new TalkToGuildLeader();
	final static ForbiddenFruit RED_APPLE = new ForbiddenFruit("Red Apple", "Eating apple starts quest Talk to Guild Leader", QUEST);
	
	@Test
	void constructorSetsAttributes() {
		final int fullPointValue = 10;
		assertEquals("Red Apple", RED_APPLE.getName());
		assertEquals("Eating apple starts quest Talk to Guild Leader", RED_APPLE.getDescription());
		assertEquals(QUEST, RED_APPLE.getQuestUnlocked());
		assertEquals(fullPointValue, RED_APPLE.getManaPoint());
		assertEquals(fullPointValue, RED_APPLE.getHealthPoint());
		assertEquals(fullPointValue, RED_APPLE.getExperiencePoint());
	}
	
//	@Test
//	void setPoisonSamePotionThrowsIAE() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void consumePoisonedOverridesGetManaHealthAndExperiencePoint() {
//		fail("Not yet implemented");
//	}
	
//	@Test
//	void consumeSetsPotionToNull() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		assertEquals("\"Red Apple: Eating apple starts quest Talk to Guild Leader\", "
				+ "Mana: 10, Health: 10, Experience: 10, Quest Unlocked: Talk to Guild Leader", RED_APPLE.toString());
	}
}
