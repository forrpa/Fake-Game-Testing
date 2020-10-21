package weapon;

public class TwoHandedSword extends Weapon{
	private WeaponSize weaponSize = WeaponSize.TwoHanded;
	
	public TwoHandedSword(String name, String desc, int recLevel, int[] dmg, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, dmg, dur, str, agi, intell, sta);
	}
	@Override
	public String getType() {
		return "TwoHandedSword";
	}
	@Override
	public WeaponSize getSize() {
		return this.weaponSize;
	}
	
}
