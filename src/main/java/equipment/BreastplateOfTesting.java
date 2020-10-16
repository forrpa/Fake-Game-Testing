package equipment;

public class BreastplateOfTesting extends Chest{
	private int durability = 100;
	
    public BreastplateOfTesting() {
		super("Breastplate of Testing", "An excellent breastplate for testing things with!", 150, 0, 2, 5, 3);
	}
    public int getDurability() {
    	return durability;
    }
}