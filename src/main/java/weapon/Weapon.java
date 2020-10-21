package weapon;
import equipment.Gear;

public abstract class Weapon extends Gear{
	private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
    private int damage;
	
	public Weapon(String name, String desc, int recLevel, int dmg, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, dur);
		this.strength = str;
		this.agility = agi;
		this.intelligence = intell;
		this.stamina = sta;
		this.damage = dmg;
	}
	public int[] getAttributes() {return new int[] {this.strength, this.agility, this.intelligence, this.stamina};}
	public int getDamage() {return this.damage;}
	@Override
	public String toString() {
		return this.getName()+"\n"+this.getDescription()+"\n"+this.getType()+"\n"+"Damage: "+this.damage+"\n"+"Strength: "+this.strength+"\n"+"Agility: "+this.agility+"\n"+"Intelligence: "+this.intelligence+"\n"+"Stamina: "+this.stamina+"\n"+"Required Level: "+this.getRequiredLevel()+"\n"+"Durability: "+this.getDurability()+"/"+this.getMaxDurability();
	}
	public abstract String getType();
	public abstract WeaponSize getSize();
}
