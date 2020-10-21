package edible;

import quest.Quest;

public class ForbiddenFruit extends Edible {
	
	private final static int FULL_POINT_BOOST = 10;
	private final Quest questUnlocked;
	private Potion poison;

	public ForbiddenFruit(String name, String description, Quest questUnlocked) {
		super(name, description, FULL_POINT_BOOST, FULL_POINT_BOOST, FULL_POINT_BOOST);
		this.questUnlocked = questUnlocked;
		setPoison(null);
	}

	@Override
	public String toString() {
		return String.format("\"%s: %s\", Mana: %d, Health: %d, Experience: %d, Quest Unlocked: %s", getName(), getDescription(), getManaPoint(), getHealthPoint(), getExperiencePoint(), questUnlocked.getName());
	}

	public Quest getQuestUnlocked() {
		return questUnlocked;
	}

	public Potion getPoison() {
		return poison;
	}

	public void setPoison(Potion potion) {
		if(poison != null && potion == poison) throw new IllegalArgumentException("Forbidden Fruit is already poisoned with this Potion. Cannot apply twice, only replace.");
		this.poison = potion;
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
