package equipment;

public class BreastplateOfTesting{
    private String name = "Breastplate of Testing";
    private int strength = 0;
    private int agility = 2;
    private int intelligence = 5;
    private int stamina = 3;
    private String type = "chest";
    
    public String getName(){return this.name;}
    public int[] getAttributes() {
    	return new int[]{this.strength, this.agility, this.intelligence, this.stamina};
    }
    public String getType() {
    	return this.type;
    }
}