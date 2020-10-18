package equipment;
import edible.Item;

public abstract class Equipment extends Item{
	private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
    private int armor;
    private final int MAX_DURABILITY;
    private int durability;
    
	public Equipment(String name, String desc, int armor, int dur, int str, int agi, int intell, int sta) {
		super(name, desc);
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

}