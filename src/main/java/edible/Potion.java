package edible;

public class Potion extends Edible {
	
	private final boolean cookable;

	public Potion(String name, String description, int manaPoint, int healthPoint, int experiencePoint) {
		super(name, description, manaPoint, healthPoint, experiencePoint);
		this.cookable = false;
	}
	
	public Potion(String name, String description, int manaPoint, int healthPoint, int experiencePoint, boolean cookable) {
		super(name, description, manaPoint, healthPoint, experiencePoint);
		this.cookable = cookable;
		
	}

}
