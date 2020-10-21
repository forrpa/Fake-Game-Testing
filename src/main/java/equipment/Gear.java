package equipment;

import item.Item;

public abstract class Gear extends Item{
	private final int MAX_DURABILITY;
    private int durability;
	
	public Gear(String name, String desc, int recLevel, int dur) {
		super(name,desc,recLevel);
		this.MAX_DURABILITY = dur;
		this.durability = dur;
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
}
