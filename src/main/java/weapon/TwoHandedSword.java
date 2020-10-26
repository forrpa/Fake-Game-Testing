package weapon;

public class TwoHandedSword extends Weapon{
	private static final WeaponSize WEAPON_SIZE = WeaponSize.TwoHanded;
	private static final String TYPE = "TwoHandedSword";
	
	public TwoHandedSword(String name, String desc, int recLevel, int dmg, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, dmg, dur, str, agi, intell, sta);
	}
	@Override
	public final String getType() {
		return TwoHandedSword.TYPE;
	}
	@Override
	public final WeaponSize getSize() {
		return TwoHandedSword.WEAPON_SIZE;
	}
	
}
