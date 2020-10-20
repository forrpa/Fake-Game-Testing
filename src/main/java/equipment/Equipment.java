package equipment;
import item.Item;

public abstract class Equipment extends Gear {
	private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
    private int armor;
    private final int MAX_DURABILITY;
    private int durability;
    
	public Equipment(String name, String desc, int recLevel, int armor, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel);
		this.strength = str;
		this.agility = agi;
		this.intelligence = intell;
		this.stamina = sta;
		this.armor = armor;
		this.MAX_DURABILITY = dur;
		this.durability = dur;
	}
    public int[] getAttributes() {
    	return new int[]{this.strength, this.agility, this.intelligence, this.stamina};
    }
    public int getArmor() {
    	return this.armor;
    }
    public int getMaxDurability() {
    	return this.MAX_DURABILITY;
    }
    public int getDurability() {
		return this.durability;
    }
    public void damageDurability(int amount) {
    	this.durability = this.durability - amount;
    }
    public void repair() {
    	this.durability = this.MAX_DURABILITY;
    }
    public abstract ArmorType getArmorType();
    public abstract String getSlot();
    @Override
    public String toString() {
    	return "";
    }

}