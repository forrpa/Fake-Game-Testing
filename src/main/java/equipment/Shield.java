package equipment;

public class Shield extends Equipment{

	public Shield(String name, String desc, int recLevel, int armor, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, armor, dur, str, agi, intell, sta);
	}
	
	@Override
	public String getSlot() {
		return "shield";
	}
	@Override
	public ArmorType getArmorType() {
		return ArmorType.SHIELD;
	}

}
