package edible;

public abstract class Item {

	private final String name;
	private final String description;
	
	public Item(String name, String description ) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item item = (Item) obj;
            return name == item.name && description == item.description;
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
        return result;
    }
	
    public abstract String toString();
}

