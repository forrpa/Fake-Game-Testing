package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import quest.Quest;
import quest.TalkToGuildLeader;

class ForbiddenFruitTest {

	final static Quest QUEST = new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true);
	final static ForbiddenFruit RED_APPLE = new ForbiddenFruit("Red Apple", "Eating apple starts quest Talk to Guild leader", QUEST);
	final static int FULL_POINT_VALUE = 10;
	
	@Test
	void constructorSetsAttributes() {
		assertEquals("Red Apple", RED_APPLE.getName());
		assertEquals("Eating apple starts quest Talk to Guild leader", RED_APPLE.getDescription());
		assertEquals(QUEST, RED_APPLE.getQuest());
		assertEquals(FULL_POINT_VALUE, RED_APPLE.getManaPoint());
		assertEquals(FULL_POINT_VALUE, RED_APPLE.getHealthPoint());
		assertEquals(FULL_POINT_VALUE, RED_APPLE.getExperiencePoint());
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		assertEquals("\"Red Apple: Eating apple starts quest Talk to Guild leader\", Mana: 10, Health: 10, Experience: 10, Quest: Talk to Guild leader", RED_APPLE.toString());
	}
}
