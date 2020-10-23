package weapon;

public abstract class OneHandedSword extends Weapon{
	private static final WeaponSize WEAPON_SIZE = WeaponSize.OneHanded;
	private static final String TYPE = "OneHandedSword";
	
	public OneHandedSword(String name, String desc, int recLevel, int dmg, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, dmg, dur, str, agi, intell, sta);
	}
	@Override
	public String getType() {
		return OneHandedSword.TYPE;
	}
	@Override
	public WeaponSize getSize() {
		return OneHandedSword.WEAPON_SIZE;
	}
}
