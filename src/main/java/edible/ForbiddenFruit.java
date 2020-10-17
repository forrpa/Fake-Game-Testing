package edible;

import quest.Quest;

public class ForbiddenFruit extends Edible {
	
	private final static int FULL_POINT_BOOST = 10;
	private final Quest quest;

	public ForbiddenFruit(String name, String description, Quest quest) {
		super(name, description, FULL_POINT_BOOST, FULL_POINT_BOOST, FULL_POINT_BOOST);
		this.quest = quest;
	}

	@Override
	public String toString() {
		return String.format("\"%s: %s\", Mana: %d, Health: %d, Experience: %d, Quest: %s", getName(), getDescription(), getManaPoint(), getHealthPoint(), getExperiencePoint(), getQuest().getName());
	}

	public Quest getQuest() {
		return quest;
	}
	
}