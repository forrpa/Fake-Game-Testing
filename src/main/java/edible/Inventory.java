package edible;

import java.util.HashMap;

import quest.TalkToGuildLeader;


public class Inventory {
	
	public static void main(String[] args) {
		final Inventory inventory = new Inventory();
		
		inventory.addItem(new Edible("Fly Agaric", "Poisonous mushroom with magic powers", 4, -3, 0));
		inventory.addItem(new Ingredient("Fire Root", "Increases power of potions"));
		inventory.addItem(new Ingredient("Fire Root", "Increases power of potions"));
		inventory.addItem(new Ingredient("Fire Root", "Increases power of potions"));
		inventory.addItem(new ForbiddenFruit("Lucky Cherry", "Eating cherry starts quest Talk to Guild leader", new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true)));
		inventory.addItem(new Potion("Heartbreak Potion", "Weakens magic powers, sabbotages health and gives life experience", -3, -10, 6));
		inventory.addItem(new Potion("Power Potion", "Boosts mana, health and experience", 5, 5, 5));
		System.out.println(inventory.toString());
	
	}

	private final HashMap<Item, Integer> inventory = new HashMap<>();

	public void addItem(Item item) {
		int count = 1;
		if(inventory.containsKey(item)) count += inventory.get(item);
		inventory.put(item, count);
	}
	
	public Item getOutItem(Item item) {
		if(inventory.isEmpty()) throw new NullPointerException("Inventory is empty.");
		if(!inventory.containsKey(item)) throw new NullPointerException("Item not in inventory.");
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
