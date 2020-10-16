package equipment;

public class BreastplateOfTesting extends Chest{
	private String description = "An excellent breastplate for testing things with!";
	
    public BreastplateOfTesting() {
		super("Breastplate of Testing", 0, 2, 5, 3);
	}
    public String getDescription() {
    	return this.description;
    }
}