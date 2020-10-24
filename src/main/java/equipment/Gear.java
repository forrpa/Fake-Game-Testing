package equipment;

import item.Item;

public abstract class Gear extends Item{
	private final int strength;
    private final int agility;
    private final int intelligence;
    private final int stamina;
	private final int MAX_DURABILITY;
    private int durability;
	
	public Gear(String name, String desc, int recLevel, int dur, int str, int agi, int intell, int sta) {
		super(name,desc,recLevel);
		checkIfValuesAreAcceptable(dur,str,agi,intell,sta,recLevel);
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
	private void checkIfValuesAreAcceptable(int dur, int str, int agi, int intell, int sta, int recLevel){
		if(dur <= 0||str<0||agi<0||intell<0||sta<0||recLevel<0) {
			throw new IllegalStateException();
		}
	}
}
