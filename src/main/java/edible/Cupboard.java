package edible;

import item.Inventory;
import item.Item;
import player.Player;
import quest.TalkToGuildLeader;
import magic.MagicPlayer;

public class Cupboard {
	
//	public static void main(String[] args) {
//		final TalkToGuildLeader QUEST = new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true);
//		final ForbiddenFruit LUCKY_CHERRY = new ForbiddenFruit("Lucky Cherry", "Eating cherry starts quest Talk to Guild leader", QUEST);
//		MagicPlayer MAGIC_PLAYER = new MagicPlayer("Vamp Witch", "Very badass witch from Rumania", 500, 100);
//		Cupboard CUPBOARD = new Cupboard(MAGIC_PLAYER); 
//		//CUPBOARD.store(new Edible("Fortune Cookie", "Hides a wisdom", 0, 0, 1));
//		//System.out.println(CUPBOARD);
//		//CUPBOARD.consume(new Edible("Fortune Cookie", "Hides a wisdom", 0, 0, 1));
//		CUPBOARD.store(LUCKY_CHERRY);
//		System.out.println("\n" +CUPBOARD);
//		CUPBOARD.store(LUCKY_CHERRY);
//		System.out.println("\n" +CUPBOARD);
//
//		System.out.println(CUPBOARD.getCount(LUCKY_CHERRY));
//		
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
		if(isInInventory(item)) throw new IllegalStateException("One of this Item is already stored in the Cupboard! Limit of one at a time for this type.");
		inventory.addItem(item);
	}
	
	private Inventory classifyCupboardItem(Item item) {
		if(item instanceof Ingredient) return ingredients;
		if(item instanceof Recipie) return recipies;
		// forbidden fruit and potion inherit from Edible so order of classification starting subclasses important!
		if(item instanceof ForbiddenFruit) return forbiddenFruits;
		if(item instanceof Potion) return potions;
		if(item instanceof Edible) return edibles;
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
	
	private void utilConsumeSetPlayerPoint(int manaPoint, int healthPoint, int experiencePoint) {
		player.setHealthPoint(healthPoint);
		player.setExperiencePoint(experiencePoint);
		if(player instanceof MagicPlayer) {
			MagicPlayer magicPlayer = (MagicPlayer) player;
			magicPlayer.setManaPoint(manaPoint);
		}
	}
	
	public void consume(Edible edible) {
		// owner of cupboard gets effected by consume already since attribute of this class used for method
		// steal will change owner of cupboard not personal cupboard of player
		Inventory inventory = getInventoryOfClassififedItem(edible);
		inventory.getOutItem(edible);
		int manaPoint = 0;
		if(player instanceof MagicPlayer) {
			MagicPlayer magicPlayer = (MagicPlayer) player;
			manaPoint = magicPlayer.getManaPoint();
		}
		// forbiddenFruit overrides consume taking into account poison
		int[] points = edible.consume(manaPoint, player.getHealthPoint(), player.getExperiencePoint());
		utilConsumeSetPlayerPoint(points[0], points[1], points[2]);
		if(edible instanceof ForbiddenFruit) {
			ForbiddenFruit fruit = (ForbiddenFruit) edible;
			fruit.getQuest().startQuest(player);
		}
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
			if(haveAllIngredients(recipie)) {
				recipies.getOutItem(recipie);
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