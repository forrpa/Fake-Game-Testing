package equipment;

public class Shield extends Equipment{
	private static final String SLOT = "shield";
	private static final ArmorType ARMOR_TYPE = ArmorType.SHIELD;
	
	public Shield(String name, String desc, int recLevel, int armor, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, armor, dur, str, agi, intell, sta);
	}
	
	@Override
	public final String getSlot() {
		return Shield.SLOT;
	}
	@Override
	public final ArmorType getArmorType() {
		return Shield.ARMOR_TYPE;
	}

}
