package equipment;

public abstract class Chest extends Equipment{

	public Chest(String name, String desc, int recLevel, int armor, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, armor, dur, str, agi, intell, sta);
	}
	@Override
	public String getSlot() {
		return "chest";
	}
	

}