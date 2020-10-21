package edible;

import item.Inventory;
import item.Item;
import player.Player;
import magic.MagicPlayer;

public class Cupboard {
	
//	public static void main(String[] args) {
//		MagicPlayer MAGIC_PLAYER = new MagicPlayer("Vamp Witch", "Very badass witch from Rumania", 500, 100);
//		Cupboard CUPBOARD = new Cupboard(MAGIC_PLAYER); 
////		MAGIC_PLAYER.setCupboard(CUPBOARD);
//		CUPBOARD.store(new Edible("Fortune Cookie", "Hides a wisdom", 0, 0, 1));
//		System.out.println(CUPBOARD);
//		CUPBOARD.consume(new Edible("Fortune Cookie", "Hides a wisdom", 0, 0, 1));
//		System.out.println(CUPBOARD);
//		
//	}
	
	private Player player;
	private final Inventory edibles = new Inventory();
	private final Inventory potions = new Inventory();
	private final Inventory ingredients = new Inventory();
	private final Inventory recipies = new Inventory();
	private final Inventory forbiddenFruits = new Inventory();

	public Cupboard(Player player) {
		this.setPlayer(player);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	private void utilStoreMaxOneItem(Inventory inventory, Item item) {
		try {
			inventory.getOutItem(item);
			throw new IllegalStateException("One of this Item is already stored in the Cupboard! Limit of one at a time for this type.");
		} catch(NullPointerException e) {
			inventory.addItem(item);
		}
	}
	
	private Inventory classifyCupboardItem(Item item) {
		if(item instanceof Edible) return edibles;
		if(item instanceof Potion) return potions;
		if(item instanceof Ingredient) return ingredients;
		if(item instanceof Recipie) return recipies;
		if(item instanceof ForbiddenFruit) return forbiddenFruits;
		return null;
	}

	public boolean isInInventory(Item item) {
		Inventory inventory = classifyCupboardItem(item);
		if(inventory == null) return false;
		if(inventory.isInInventory(item)) return true;
		return false;
	}
	
	private Inventory getInventoryOfClassififedItem(Item item) {
		Inventory inventory = classifyCupboardItem(item);
		if(inventory == null) throw new IllegalArgumentException("Item is not of type that stores in the Cupboard.");
		return inventory;
	}
	
	public int getCount(Item item) {
		Inventory inventory = getInventoryOfClassififedItem(item);
		return inventory.getCount(item);
	}
	
	public void store(Item item) {
		if(item instanceof Recipie) {
			utilStoreMaxOneItem(recipies, item);
			return;
		}
		if(item instanceof ForbiddenFruit) {
			utilStoreMaxOneItem(forbiddenFruits, item);
			return;
		}
		Inventory inventory = getInventoryOfClassififedItem(item);
		inventory.addItem(item);
	}
	
	private void utilConsumeSetPlayerPoints(Edible edible) {
		player.setHealthPoint(player.getHealthPoint() + edible.getHealthPoint());
		player.setExperiencePoint(player.getExperiencePoint() + edible.getRequiredLevel());
		if(player instanceof MagicPlayer) {
			MagicPlayer magicPlayer = (MagicPlayer) player;
			magicPlayer.setManaPoint(magicPlayer.getManaPoint() + edible.getManaPoint());
		}
	}
	
	public void consume(Edible edible) {
		if(edible instanceof ForbiddenFruit) {
			forbiddenFruits.getOutItem(edible);
			ForbiddenFruit fruit = (ForbiddenFruit) edible;
			Potion potion = fruit.getPoison();
			if(potion != null) utilConsumeSetPlayerPoints(potion);
			else utilConsumeSetPlayerPoints(fruit);
			fruit.setPoison(null);
			fruit.getQuest().startQuest(player);
			return;
		}
		if(edible instanceof Potion) {
			potions.getOutItem(edible);
			utilConsumeSetPlayerPoints(edible);
			return;
		}
		edibles.getOutItem(edible);
		utilConsumeSetPlayerPoints(edible);
	}
	
	public void poison(Potion potion, ForbiddenFruit fruit) {
		potions.getOutItem(potion);
		forbiddenFruits.getOutItem(fruit);
		fruit.setPoison(potion);
		store(fruit);
	}
	
	public boolean haveAllIngredients(Recipie recipie) {
		Ingredient[] requiredIngredients = recipie.getIngredients();
		for(Ingredient i : requiredIngredients) {
			if(!ingredients.isInInventory(i)) return false;
		}
		return true;
	}
	
	public void cook(Recipie recipie) {
		if(player instanceof MagicPlayer) {
			MagicPlayer magicPlayer = (MagicPlayer) player;
			recipies.getOutItem(recipie);
			if(haveAllIngredients(recipie)) {
				Ingredient[] storedIngredients = recipie.getIngredients();
				for(Ingredient i : storedIngredients) ingredients.getOutItem(i);
				Potion potion = recipie.cook(magicPlayer.getManaPoint(), magicPlayer.getExperiencePoint());
				store(potion);
				store(recipie);
			}
		} else {
			throw new IllegalCallerException("Only Magic Players can cook from Recipies.");
		}
	}
	
//	TradeOffer Class needed to implement getOutItem and place in tradeOffer object to close deal
//		
	
	// steal cupboard and empty cupboard take param other player

	@Override
	public String toString() {
		return getPlayer().getPlayerClass() + ", " + player.getRace() + "\nEDIBLES:\n" + edibles.toString() + "\nFORBIDDEN FRUITS:\n" + forbiddenFruits.toString() + "\nPOTIONS:\n" + potions.toString() + "\nINGREDIENTS:\n" + ingredients.toString() + "\nRECIPIES:\n" + recipies.toString();

	}
}