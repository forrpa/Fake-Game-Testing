package equipment;

public class BreastplateOfTesting extends Chest{
	
    public BreastplateOfTesting() {
		super("Breastplate of Testing");
	}
    private int strength = 0;
    private int agility = 2;
    private int intelligence = 5;
    private int stamina = 3;
    
    public int[] getAttributes() {
    	return new int[]{this.strength, this.agility, this.intelligence, this.stamina};
    }
    public boolean isEquipment() {
    	return true;
    }
}