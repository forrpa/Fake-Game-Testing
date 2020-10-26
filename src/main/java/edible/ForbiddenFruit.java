package edible;

import quest.Quest;

public class ForbiddenFruit extends Edible {
	
	private final static int TEN_MAX_EDIBLE_POINT_VALUE = 10;
	private final Quest questUnlocked;
	private Potion poison;

	public ForbiddenFruit(String name, String description, Quest questUnlocked) {
		super(name, description, TEN_MAX_EDIBLE_POINT_VALUE, TEN_MAX_EDIBLE_POINT_VALUE, TEN_MAX_EDIBLE_POINT_VALUE);
		this.questUnlocked = questUnlocked;
	}

	public final Quest getQuestUnlocked() {
		return questUnlocked;
	}

	public final Potion getPoison() {
		return poison;
	}

	public final void setPoison(Potion potion) { 
		if(poison != null && potion == poison) 
			throw new IllegalArgumentException("Forbidden Fruit is already poisoned with this Potion. Cannot apply twice, only replace.");
		this.poison = potion;
	}
	
	@Override
	protected final int[] consume(int playerManaPoint, int playerHealthPoint, int playerExperiencePoint) {
		int[] points = {playerManaPoint + getManaPoint(), playerHealthPoint + getHealthPoint(), playerExperiencePoint + getExperiencePoint()};
		if(poison != null) {
			points[0] = playerManaPoint + poison.getManaPoint(); 
			points[1] = playerHealthPoint + poison.getHealthPoint();
			points[2] = playerExperiencePoint + poison.getExperiencePoint();
			setPoison(null);
		}
		return points.clone();
	}
	
	@Override
	public final String toString() {
		return String.format("\"%s: %s\", Mana: %d, Health: %d, Experience: %d, Quest Unlocked: %s", getName(), getDescription(), getManaPoint(), getHealthPoint(), getExperiencePoint(), questUnlocked.getName());
	}
	
}
