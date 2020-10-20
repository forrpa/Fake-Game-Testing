package weapon;
import item.Item;

public abstract class Weapon extends Item{
	private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
    private final int MAX_DURABILITY;
    private int durability;
    private int[] damageRange;
    private int requiredLevel;
	
	public Weapon(String name, String desc, int recLevel, int[] dmg, int dur, int str, int agi, int intell, int sta) {
		super(name,desc);
		this.strength = str;
		this.agility = agi;
		this.intelligence = intell;
		this.stamina = sta;
		this.MAX_DURABILITY = dur;
		this.durability = dur;
		this.damageRange = dmg;
		this.requiredLevel = recLevel;
	}
	public int getMaxDurability() {return this.MAX_DURABILITY;}
	public int getDurability() {return this.durability;}
	public int[] getAttributes() {return new int[] {this.strength, this.agility, this.intelligence, this.stamina};}
	public int[] getDamageRange() {return this.damageRange;}
	public int getRequiredLevel() {return this.requiredLevel;}
	public String toString() {
		return "";
	}
}
