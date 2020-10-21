package equipment;

public abstract class Equipment extends Gear {
	private int strength;
    private int agility;
    private int intelligence;
    private int stamina;
    private int armor;
    
    
	public Equipment(String name, String desc, int recLevel, int armor, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, dur);
		this.strength = str;
		this.agility = agi;
		this.intelligence = intell;
		this.stamina = sta;
		this.armor = armor;
	}
    public int[] getAttributes() {
    	return new int[]{this.strength, this.agility, this.intelligence, this.stamina};
    }
    public int getArmor() {
    	return this.armor;
    }
    public abstract ArmorType getArmorType();
    public abstract String getSlot();
    @Override
    public String toString() {
    	return this.getName()+"\n"+this.getDescription()+"\n"+this.getArmorType()+" "+this.getSlot()+"\n"+"Armor: "+this.armor+"\n"+"Strength: "+this.strength+"\n"+"Agility: "+this.agility+"\n"+"Intelligence: "+this.intelligence+"\n"+"Stamina: "+this.stamina+"\n"+"Required Level: "+this.getRequiredLevel()+"\n"+"Durability: "+this.getDurability()+"/"+this.getMaxDurability();
    }

}