package item;

import java.util.HashMap;

public class Inventory {

	private final HashMap<Item, Integer> inventory = new HashMap<>();

	public void addItem(Item item) {
		int count = 1;
		if(inventory.containsKey(item)) count += inventory.get(item);
		inventory.put(item, count);
	}
	
	public Item getOutItem(Item item) {
		if(inventory.isEmpty()) throw new NullPointerException("Inventory is empty.");
		isInInventory(item);
		int count = inventory.get(item);
		inventory.put(item, count - 1);
		return item;
	}
	
	public boolean isInInventory(Item item) {
		if(!inventory.containsKey(item)) throw new NullPointerException("Item not in inventory.");
		return true;
	}
	
	public int getCount(Item item) {
		isInInventory(item);
		return inventory.get(item);
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

	//Jennifer
	public void removeItem(Item item){
		if(inventory.isEmpty()) throw new NullPointerException("Inventory is empty.");
		isInInventory(item);
		int count = inventory.get(item);
		if (count == 1) {
			inventory.remove(item);
		} else {
			inventory.put(item, count - 1);
		}
	}

}
