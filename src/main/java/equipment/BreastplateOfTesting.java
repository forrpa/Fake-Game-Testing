package equipment;

public class BreastplateOfTesting extends Chest{
	private int armor = 150;
	
    public BreastplateOfTesting() {
		super("Breastplate of Testing", "An excellent breastplate for testing things with!", 0, 2, 5, 3);
	}
    public int getArmor() {
    	return this.armor;
    }
}