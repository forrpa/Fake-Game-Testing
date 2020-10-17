package edible;

public class Recipie extends Item {
	
	private final Potion potion;
	private final int manaPointsToCook;
	private final int experiencePointsToCook;

	public Recipie(String name, String description, int manaPointsToCook, int experiencePointsToCook, Potion potion) {
		super(name, description);
		this.potion = potion;
		this.manaPointsToCook = manaPointsToCook;
		this.experiencePointsToCook = experiencePointsToCook;
	}
	
	public Potion cook() {
		// check Players mana and exp points if insufficient throw ISE
		return potion;
	}
	

}
