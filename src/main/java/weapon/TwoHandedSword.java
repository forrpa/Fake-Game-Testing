package weapon;

public class TwoHandedSword extends Weapon{

	public TwoHandedSword(String name, String desc, int recLevel, int[] dmg, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, dmg, dur, str, agi, intell, sta);
	}

	@Override
	public String getType() {
		return "TwoHandedSword";
	}
	
}
