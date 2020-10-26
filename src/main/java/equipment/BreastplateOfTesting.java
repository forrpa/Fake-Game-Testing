package equipment;

public class BreastplateOfTesting extends Chest{
	private static final ArmorType ARMOR_TYPE = ArmorType.PLATE;
	
	public BreastplateOfTesting(int dur) { 
		super("Breastplate of Testing", "An excellent breastplate for testing things with!", 7, 150, dur, 0, 2, 5, 3);
	}
	public BreastplateOfTesting(int armor, int str, int agi, int intell, int sta, int recLevel) {
		super("Breastplate of Testing", "An excellent breastplate for testing things with!", recLevel, armor, 100, str, agi, intell, sta);
	}
    public BreastplateOfTesting() {
		super("Breastplate of Testing", "An excellent breastplate for testing things with!", 7, 150, 100, 0, 2, 5, 3);
	}
    @Override
    public final ArmorType getArmorType() {return BreastplateOfTesting.ARMOR_TYPE;}  
}