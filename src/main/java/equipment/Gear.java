package equipment;

import item.Item;

public abstract class Gear extends Item{
	private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
	private final int MAX_DURABILITY;
    private int durability;
	
	public Gear(String name, String desc, int recLevel, int dur, int str, int agi, int intell, int sta) {
		super(name,desc,recLevel);
		this.MAX_DURABILITY = dur;
		this.durability = dur;
		this.strength = str;
		this.agility = agi;
		this.intelligence = intell;
		this.stamina = sta;
	}
	public int getMaxDurability() {
    	return this.MAX_DURABILITY;
    }
    public int getDurability() {
		return this.durability;
    }
    public void damageDurability(int amount) {
    	if(amount > this.durability) {this.durability = 0;return;}
    	this.durability = this.durability - amount;
    }
    public void repair() {
    	this.durability = this.MAX_DURABILITY;
    }
    public int[] getAttributes() {
    	return new int[]{this.strength, this.agility, this.intelligence, this.stamina};
    }
}
