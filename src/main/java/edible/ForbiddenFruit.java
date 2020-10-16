package edible;

import inte_projektarbete.quest.Quest;

public class ForbiddenFruit extends Edible {
	
	private final Quest quest;

	public ForbiddenFruit(String name, String description, Quest quest) {
		super(name, description, 0, 0, 0);
		this.quest = quest;
	}

	@Override
	public String toString() {
		return String.format("\"%s: %s\", Quest: %s", getQuest().toString());
	}

	public Quest getQuest() {
		return quest;
	}
	
}