package weapon;

public abstract class Weapon {
	private String name;
	private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
    private String description;
    private final int MAX_DURABILITY;
    private int durability;
    private int[] damageRange;
	
	public Weapon(String name, String desc, int[] dmg, int dur, int str, int agi, int intell, int sta) {
		this.name = name;
		this.description = desc;
		this.strength = str;
		this.agility = agi;
		this.intelligence = intell;
		this.stamina = sta;
		this.MAX_DURABILITY = dur;
		this.durability = dur;
		this.damageRange = dmg;
	}
	public String getName() {return this.name;}
	public String getDescription() {return this.description;}
	public int getMaxDurability() {return this.MAX_DURABILITY;}
	public int getDurability() {return this.durability;}
	public int[] getAttributes() {return new int[] {this.strength, this.agility, this.intelligence, this.stamina};}
	public int[] getDamageRange() {return this.damageRange;}
}
