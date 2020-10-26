package edible;

import item.Item;

public class Edible extends Item {
	
	private final static int NEGATIVE_TEN_MIN_EDIBLE_POINT_VALUE = -10;
	private final static int TEN_MAX_EDIBLE_POINT_VALUE = 10;
	private final int manaPoint;
	private final int healthPoint;
	private final int experiencePoint;
	
	public Edible(String name, String description, int manaPoint, int healthPoint, int experiencePoint) {
		super(name, description);
		this.manaPoint = checkPointValueRange(manaPoint);
		this.healthPoint = checkPointValueRange(healthPoint);
		this.experiencePoint = checkPointValueRange(experiencePoint);
	}

	public final int getManaPoint() {
		return manaPoint;
	}
 
	public final int getHealthPoint() {
		return healthPoint; 
	}

	public final int getExperiencePoint() {
		return experiencePoint;
	}
	
	private int checkPointValueRange(int pointValue) {
		if(pointValue < NEGATIVE_TEN_MIN_EDIBLE_POINT_VALUE || TEN_MAX_EDIBLE_POINT_VALUE < pointValue) 
			throw new IllegalArgumentException("Value must be in range -10 to 10");
		return pointValue;
	}
	
	protected int[] consume(int playerManaPoint, int playerHealthPoint, int playerExperiencePoint) {
		int[] points = {playerManaPoint + manaPoint, playerHealthPoint + healthPoint, playerExperiencePoint + experiencePoint};
		return points.clone();
	}
	
	@Override
	public String toString() {
		return String.format("\"%s: %s\", Mana: %d, Health: %d, Experience: %d", getName(), getDescription(), getManaPoint(), getHealthPoint(), getExperiencePoint());
	}

}
