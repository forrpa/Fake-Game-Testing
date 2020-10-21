package edible;

import quest.Quest;

public class ForbiddenFruit extends Edible {
	
	private final static int FULL_POINT_BOOST = 10;
	private final Quest quest;
	private Potion poison;

	public ForbiddenFruit(String name, String description, Quest quest) {
		super(name, description, FULL_POINT_BOOST, FULL_POINT_BOOST, FULL_POINT_BOOST);
		this.quest = quest;
		setPoison(null);
	}

	@Override
	public String toString() {
		return String.format("\"%s: %s\", Mana: %d, Health: %d, Experience: %d, Quest: %s", getName(), getDescription(), getManaPoint(), getHealthPoint(), getRequiredLevel(), getQuest().getName());
	}

	public Quest getQuest() {
		return quest;
	}

	public Potion getPoison() {
		return poison;
	}

	public void setPoison(Potion poisoned) {
		this.poison = poisoned;
	}
	
	// only one of each type of forbidden fruit can be stored at a time, so no need to overrides equals with poison
	
	@Override
	public int[] consume(int playerManaPoint, int playerHealthPoint, int playerExperiencePoint) {
		int manaPoint = FULL_POINT_BOOST;
		int healthPoint = FULL_POINT_BOOST;
		int experiencePoint = FULL_POINT_BOOST;
		if(poison != null) {
			manaPoint = poison.getManaPoint();
			healthPoint = poison.getHealthPoint();
			experiencePoint = poison.getExperiencePoint();
			setPoison(null);
		}
		int[] points = {playerManaPoint + manaPoint, playerHealthPoint + healthPoint, playerExperiencePoint + experiencePoint};
		return points.clone();
	}
	
	
}
