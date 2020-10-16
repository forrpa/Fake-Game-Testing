package edible;

import quest.Quest;

public class ForbiddenFruit extends Edible {
	
	private final static int NEUTRAL_POINT_VALUE = 0;
	private final Quest quest;

	public ForbiddenFruit(String name, String description, Quest quest) {
		super(name, description, NEUTRAL_POINT_VALUE, NEUTRAL_POINT_VALUE, NEUTRAL_POINT_VALUE);
		this.quest = quest;
	}

	@Override
	public String toString() {
		return String.format("\"%s: %s\", Quest: %s", getName(), getDescription(), getQuest().getName());
	}

	public Quest getQuest() {
		return quest;
	}
	
}