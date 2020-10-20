package item;

public abstract class Item {

	private final String name;
	private final String description;
	private final int requiredLevel;
	private final int zeroDefaultRequiredLevel = 0;
	
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
		this.requiredLevel = zeroDefaultRequiredLevel;
	}
	
	public Item(String name, String description, int requiredLevel) {
		this.name = name;
		this.description = description;
		if(requiredLevel < zeroDefaultRequiredLevel) 
			throw new IllegalArgumentException("Minimum Required Level is 0.");
		this.requiredLevel = requiredLevel;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public int getRequiredLevel() {
		return requiredLevel;
	}
	
	@Override
    public final boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item item = (Item) obj;
            return name == item.name && description == item.description && requiredLevel == item.requiredLevel;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;
        result = prime * result + name.hashCode();
        result = prime * result + description.hashCode();
        result = prime * result + requiredLevel;
        return result;
    }
	
    public abstract String toString();

}
