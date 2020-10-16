package edible;

public class Edible extends Item {

	private final int manaPoint;
	private final int healthPoint;
	private final int experiencePoint;
	
	public Edible(String name, String description, int manaPoint, int healthPoint, int experiencePoint) {
		super(name, description);
		this.manaPoint = checkPointValueRange(manaPoint);
		this.healthPoint = checkPointValueRange(healthPoint);
		this.experiencePoint = checkPointValueRange(experiencePoint);
	}
	
	@Override
	public String toString() {
		return String.format("\"%s: %s\", Mana: %d, Health: %d, Experience: %d", getName(), getDescription(), getManaPoint(), getHealthPoint(), getExperiencePoint());
	}

	public int getManaPoint() {
		return manaPoint;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public int getExperiencePoint() {
		return experiencePoint;
	}
	
	private int checkPointValueRange(int pointValue) {
		if(pointValue < -10 || 10 < pointValue) throw new IllegalArgumentException("Value must be in range -10 to 10");
		return pointValue;
	}
}