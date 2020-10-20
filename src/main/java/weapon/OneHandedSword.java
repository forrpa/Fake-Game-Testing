package weapon;

public abstract class OneHandedSword extends Weapon{

	public OneHandedSword(String name, String desc, int recLevel, int[] dmg, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, dmg, dur, str, agi, intell, sta);
	}
	public String getType() {
		return "OneHandedSword";
	}
}
