package edible;

import java.util.HashMap;


public class Inventory {
//	final static Item FLY_AGARIC = new Edible("Fly Agaric", "Poisonous mushroom with magic powers", 4, -3, 0);
//	final static Item LUCKY_CHERRY = new ForbiddenFruit("Lucky Cherry", "Eating cherry starts quest Talk to Guild leader", new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true));	
//	final static Item FIRE_ROOT = new Ingredient("Fire Root", "Increases power of potions");	
//	final static Item HEARTBREAK_POTION = new Potion("Heartbreak Potion", "Weakens magic powers, sabbotages health and gives life experience", -3, -10, 6);
//	final static Item POWER_POTION = new Potion("Power Potion", "Boosts mana, health and experience", 5, 5, 5);
//	
//	public static void main(String[] args) {
//		Inventory INVENTORY = new Inventory();
////		inventory.addItem(new Edible("Lucky Cherry", "Description", 0, 0, 0));
////		inventory.addItem(new Edible("Power Potion", "Description", 0, 0, 0));
////		inventory.addItem(new Edible("Love Potion", "Description", 0, 0, 0));
////		inventory.addItem(new Edible("Love Potion", "Description", 0, 0, 0));
//		
////		INVENTORY.addItem(FLY_AGARIC);
////		INVENTORY.addItem(FLY_AGARIC);
////		INVENTORY.addItem(LUCKY_CHERRY);
////		INVENTORY.addItem(HEARTBREAK_POTION);
////		INVENTORY.addItem(POWER_POTION);
//		System.out.println(INVENTORY.toString());
//		System.out.println("***");
//	}

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
