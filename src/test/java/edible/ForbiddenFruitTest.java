package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import quest.Quest;
import quest.TalkToGuildLeader;

class ForbiddenFruitTest {

	final static Quest QUEST = new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true);
	final static ForbiddenFruit RED_APPLE = new ForbiddenFruit("Red Apple", "Eating apple starts quest Talk to Guild leader", QUEST);
	
	@Test
	void constructorSetsAttributes() {
		assertEquals("Red Apple", RED_APPLE.getName());
		assertEquals("Eating apple starts quest Talk to Guild leader", RED_APPLE.getDescription());
		assertEquals(QUEST, RED_APPLE.getQuest());
	}

	@Test
	void constructorSetsFullPointValue() {
		assertEquals(10, RED_APPLE.getManaPoint());
		assertEquals(10, RED_APPLE.getHealthPoint());
		assertEquals(10, RED_APPLE.getExperiencePoint());
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		assertEquals("\"Red Apple: Eating apple starts quest Talk to Guild leader\", Mana: 10, Health: 10, Experience: 10, Quest: Talk to Guild leader", RED_APPLE.toString());
	}
}
