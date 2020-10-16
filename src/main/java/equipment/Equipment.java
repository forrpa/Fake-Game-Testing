package equipment;

public class Equipment{
	private String name;
	private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
    private String description;
    private int armor;
    private final int MAX_DURABILITY;
    private int durability;
    
	public Equipment(String name, String desc, int armor, int dur, int str, int agi, int intell, int sta) {
		this.name = name;
		this.strength = str;
		this.agility = agi;
		this.intelligence = intell;
		this.stamina = sta;
		this.description = desc;
		this.armor = armor;
		this.MAX_DURABILITY = dur;
		this.durability = dur;
	}
    public String getName(){return this.name;}
    public int[] getAttributes() {
    	return new int[]{this.strength, this.agility, this.intelligence, this.stamina};
    }
    public String getDescription() {
    	return this.description;
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
    public void damageDurability() {
    	this.durability = this.durability - 13;
    }
}