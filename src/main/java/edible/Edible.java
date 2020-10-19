package edible;

public class Edible extends Item {

	public static void main(String[] args) {
		final Edible MAGIC_HEALING_MUSHROOM = new Edible("Magic Healing Mushroom", 
				"Small increases in magic capability and substantial health boost", 2, 6, 0);
		System.out.println(MAGIC_HEALING_MUSHROOM.hashCode());
	}
	
	private final int manaPoint;
	private final int healthPoint;
	private final int requiredLevel;
	
	public Edible(String name, String description, int manaPoint, int healthPoint,  int recLevel) {
		super(name, description);
		this.manaPoint = checkPointValueRange(manaPoint);
		this.healthPoint = checkPointValueRange(healthPoint);
		this.requiredLevel = checkPointValueRange(recLevel); //metoden känns inte rimlig vare sig för experience eller level. /Christian
		//this.requiredLevel = recLevel;
	}

	public int getManaPoint() {
		return manaPoint;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	
	private int checkPointValueRange(int pointValue) {
		if(pointValue < -10 || 10 < pointValue) throw new IllegalArgumentException("Value must be in range -10 to 10");
		return pointValue;
	}
	
	@Override
	public String toString() {
		return String.format("\"%s: %s\", Mana: %d, Health: %d, Experience: %d", getName(), getDescription(), getManaPoint(), getHealthPoint(), getRequiredLevel());
	}

	@Override
	public int getRequiredLevel() {
		return this.requiredLevel;
	}

}
