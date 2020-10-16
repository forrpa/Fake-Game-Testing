public class ForbiddenFruit extends Edible {
	
	private final Quest quest;

	public ForbiddenFruit(String name, String description, int manaPoint, int healthPoint, int experiencePoint, Quest quest) {
		super(name, description, manaPoint, healthPoint, experiencePoint);
		this.quest = quest;
	}

	@Override
	public String toString() {
		return String.format("\"%s: %s\", Mana: %d, Health: %d, Experience: %d, Quest: %s", getName(), getDescription(), getManaPoint(), getHealthPoint(), getExperiencePoint(), getQuest().toString());
	}

	public Quest getQuest() {
		return quest;
	}
	
}
