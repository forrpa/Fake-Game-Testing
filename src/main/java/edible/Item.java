package edible;

public abstract class Item {

	private String name;
	private String description;
	
	public Item(String name, String description ) {
		this.setName(name);
		this.setDescription(description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

