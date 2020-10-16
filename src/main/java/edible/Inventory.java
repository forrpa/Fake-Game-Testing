package edible;

import java.util.HashMap;

public class Inventory {

	private HashMap<Item, Integer> inventory = new HashMap<>();

	public void addItem(Item item) {
		int count = 1;
		if(inventory.containsKey(item)) count += inventory.get(item);
		inventory.put(item, count);
	}
	
	public void removeItem(Item item) {
		// if item not exists throw ISE
		int count = inventory.get(item);
		inventory.put(item, count - 1);
	}

}
