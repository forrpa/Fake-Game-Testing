package item;

public abstract class Item {

	private final static int ZERO_DEFAULT_REQUIRED_UNIT_LEVEL = 0;
	private final static int THIRTY_ONE = 31;
	private final static int SEVENTEEN = 17;
	private final String name;
	private final String description;
	private final int requiredUnitLevel;
	
	public Item(String name, String description) {
		this.name =  verifyStringNotNull(name, "Name");
		this.description = verifyStringNotNull(description, "Description");
		this.requiredUnitLevel = ZERO_DEFAULT_REQUIRED_UNIT_LEVEL;
	}
	
	public Item(String name, String description, int requiredUnitLevel) {
		this.name = verifyStringNotNull(name, "Name");
		this.description = verifyStringNotNull(description, "Description");
		this.requiredUnitLevel = verifyRequiredUnitLevelInRange(requiredUnitLevel); 
	}

	private String verifyStringNotNull(String stringAttribute, String attributeLabel) {
		if(stringAttribute == null)
			throw new IllegalArgumentException("String attriute \'" + attributeLabel + "\' cannot be null.");
		return stringAttribute;
	}
	
	private int verifyRequiredUnitLevelInRange(int requiredUnitLevel) {
		if(40 < requiredUnitLevel)
			throw new IllegalArgumentException("Maximum Unit Level is 40.");
		if(requiredUnitLevel < ZERO_DEFAULT_REQUIRED_UNIT_LEVEL)
			throw new IllegalArgumentException("Minimum Unit Level is 0.");
		return requiredUnitLevel;
	}
	
	public final String getName() {
		return name;
	}

	public final String getDescription() {
		return description;
	}
	
	public final int getRequiredUnitLevel() {
		return requiredUnitLevel;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item item = (Item) obj;
            return name.equals(item.name) && description.equals(item.description) && requiredUnitLevel == item.requiredUnitLevel;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int result = SEVENTEEN;
        result = THIRTY_ONE * result + name.hashCode();
        result = THIRTY_ONE * result + description.hashCode();
        result = THIRTY_ONE * result + requiredUnitLevel;
        return result;
    }
	
    public abstract String toString();
    
}
