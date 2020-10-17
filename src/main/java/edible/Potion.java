package edible;

public class Potion extends Edible {
	
	//private boolean cookable;

	public Potion(String name, String description, int manaPoint, int healthPoint, int experiencePoint) {
		super(name, description, manaPoint, healthPoint, experiencePoint);
		//this.cookable = true;
	}
	
//	public Potion(String name, String description, int manaPoint, int healthPoint, int experiencePoint, boolean cookable) {
//		super(name, description, manaPoint, healthPoint, experiencePoint);
//		setCookable(cookable);
//	}
	
//	public void setCookable(boolean cookable) {
//		this.cookable = cookable;
//	}
//
//	public boolean isCookable() {
//		return cookable;
//	}

}
