package equipment;

public class Equipment{
	private String name;
	private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
    private String description;
    
	public Equipment(String name, String desc, int str, int agi, int intell, int sta) {
		this.name = name;
		this.strength = str;
		this.agility = agi;
		this.intelligence = intell;
		this.stamina = sta;
		this.description = desc;
	}
    public String getName(){return this.name;}
    public int[] getAttributes() {
    	return new int[]{this.strength, this.agility, this.intelligence, this.stamina};
    }
    public String getDescription() {
    	return this.description;
    }
}