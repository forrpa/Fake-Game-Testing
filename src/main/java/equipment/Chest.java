package equipment;

public abstract class Chest extends Equipment{
	private static final String SLOT = "chest";
	
	public Chest(String name, String desc, int recLevel, int armor, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, armor, dur, str, agi, intell, sta);
	}
	@Override
	public final String getSlot() {
		return Chest.SLOT;
	}
	

}