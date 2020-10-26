package weapon;
import equipment.Gear;

public abstract class Weapon extends Gear{
    private final int damage;
	
	public Weapon(String name, String desc, int recLevel, int dmg, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, dur,str,agi,intell,sta);
		this.damage = dmg;
	}
	public final int getDamage() {return this.damage;}
	@Override
	public final String toString() {
		return this.getName()+"\n"+this.getDescription()+"\n"+this.getType()+"\n"+"Damage: "+this.damage+"\n"+"Strength: "+this.getAttributes()[0]+"\n"+"Agility: "+this.getAttributes()[1]+"\n"+"Intelligence: "+this.getAttributes()[2]+"\n"+"Stamina: "+this.getAttributes()[3]+"\n"+"Required Level: "+this.getRequiredUnitLevel()+"\n"+"Durability: "+this.getDurability()+"/"+this.getMaxDurability();
	}
	public abstract String getType();
	public abstract WeaponSize getSize();
}
