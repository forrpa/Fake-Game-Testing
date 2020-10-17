package weapon;

public class WidowsWail extends Sword{
	private WeaponSize ONEHANDED;
	
	public WidowsWail() {
		super("Widow's Wail", "A valyrian steel blade that once belonged to a little shit called Joffrey Baratheon.", new int[]{78, 99}, 102, 17, 14, 0, 10);
	}
	public WeaponSize getWeaponSize() {return this.ONEHANDED;}
}
