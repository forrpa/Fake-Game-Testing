package equipment;

public class BreastplateOfTesting extends Chest{
	private ArmorType PLATE;
	
    public BreastplateOfTesting() {
		super("Breastplate of Testing", "An excellent breastplate for testing things with!", 7, 150, 100, 0, 2, 5, 3);
	}
    @Override
    public ArmorType getArmorType() {return this.PLATE;}
}