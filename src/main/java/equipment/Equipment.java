package equipment;

public abstract class Equipment extends Gear {
    private final int armor;
    
	public Equipment(String name, String desc, int recLevel, int armor, int dur, int str, int agi, int intell, int sta) {
		super(name, desc, recLevel, dur,str,agi,intell,sta);
		checkIfValuesAreAcceptable(armor);
		this.armor = armor;
	}
    
    public int getArmor() {
    	return this.armor;
    }
    public abstract ArmorType getArmorType();
    public abstract String getSlot();
    @Override
    public String toString() {
    	return this.getName()+"\n"+this.getDescription()+"\n"+this.getArmorType()+" "+this.getSlot()+"\n"+"Armor: "+this.armor+"\n"+"Strength: "+this.getAttributes()[0]+"\n"+"Agility: "+this.getAttributes()[1]+"\n"+"Intelligence: "+this.getAttributes()[2]+"\n"+"Stamina: "+this.getAttributes()[3]+"\n"+"Required Level: "+this.getRequiredUnitLevel()+"\n"+"Durability: "+this.getDurability()+"/"+this.getMaxDurability();
    }
	private void checkIfValuesAreAcceptable(int armor) {
		if(armor<0) {throw new IllegalStateException();}
	}
}