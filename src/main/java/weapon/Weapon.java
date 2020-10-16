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

}
