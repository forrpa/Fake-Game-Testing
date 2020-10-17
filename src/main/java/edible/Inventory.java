package edible;

import java.util.HashMap;

public class Inventory {
	
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		inventory.addItem(new Edible("Lucky Cherry", "Description", 0, 0, 0));
		inventory.addItem(new Edible("Power Potion", "Description", 0, 0, 0));
		inventory.addItem(new Edible("Love Potion", "Description", 0, 0, 0));
		inventory.addItem(new Edible("Love Potion", "Description", 0, 0, 0));
		System.out.println(inventory.toString());
		System.out.println("***");
	}

	private final HashMap<Item, Integer> inventory = new HashMap<>();

	public void addItem(Item item) {
		int count = 1;
		if(inventory.containsKey(item)) count += inventory.get(item);
		inventory.put(item, count);
	}
	
	// subclasses need to think of casting item returned
	public Item getOutItem(Item item) {
		// if item not exists throw IAE
		int count = inventory.get(item);
		inventory.put(item, count - 1);
		return item;
	}
	
	@Override
	public String toString() {
		if(inventory.isEmpty()) return "Inventory is empty!";
		String dict = "";
		for(Item i : inventory.keySet()) {
			dict += "{" + i.getName() + ": " + inventory.get(i) + " }\n";
		}
		return dict.substring(0,dict.length()-1);
	}

}
