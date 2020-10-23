package equipment;

public class TestChestArmor extends Chest{

private static final ArmorType ARMOR_TYPE = ArmorType.PLATE;
	
    public TestChestArmor(int armor) {
		super("TestChest", "for testing", 0, armor, 100, 0, 0, 0, 0);
	}
    @Override
    public ArmorType getArmorType() {return TestChestArmor.ARMOR_TYPE;}

}
