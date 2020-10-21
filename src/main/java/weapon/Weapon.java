package weapon;
import equipment.Gear;

public abstract class Weapon extends Gear{
	private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
    private final int MAX_DURABILITY;
    private int durability;
    private int[] damageRange;
	
	public Weapon(String name, String desc, int recLevel, int[] dmg, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel);
		this.strength = str;
		this.agility = agi;
		this.intelligence = intell;
		this.stamina = sta;
		this.MAX_DURABILITY = dur;
		this.durability = dur;
		this.damageRange = dmg;
	}
	public int getMaxDurability() {return this.MAX_DURABILITY;}
	public int getDurability() {return this.durability;}
	public int[] getAttributes() {return new int[] {this.strength, this.agility, this.intelligence, this.stamina};}
	public int[] getDamageRange() {return this.damageRange;}
	@Override
	public String toString() {
		return this.getName()+"\n"+this.getDescription()+"\n"+this.getType()+"\n"+"Damage: "+this.damageRange[0]+"-"+this.damageRange[1]+"\n"+"Strength: "+this.strength+"\n"+"Agility: "+this.agility+"\n"+"Intelligence: "+this.intelligence+"\n"+"Stamina: "+this.stamina+"\n"+"Required Level: "+this.getRequiredLevel()+"\n"+"Durability: "+this.durability+"/"+this.MAX_DURABILITY;
	}
	public abstract String getType();
	public abstract WeaponSize getSize();
}
