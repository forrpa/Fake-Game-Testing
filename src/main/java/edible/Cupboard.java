package edible;

import java.util.ArrayList;

import item.Inventory;
import item.Item;
import magic.MagicPlayer;
import player.Player;
import quest.Quest;

public class Cupboard {
	
	private Player player;
	private final Inventory edibles = new Inventory();
	private final Inventory potions = new Inventory();
	private final Inventory ingredients = new Inventory();
	private final Inventory recipies = new Inventory();
	private final Inventory forbiddenFruits = new Inventory();
	private final ArrayList<ForbiddenFruit> poisonedForbiddenFruits = new ArrayList<>();

	public Cupboard(Player player) {
		this.setPlayer(player);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Item getOutItem(Item item) {
		Inventory inventory = getInventoryOfClassififedItem(item);
		Item returnItem = inventory.getOutItem(item);
		if(item instanceof ForbiddenFruit) {
			for(ForbiddenFruit f : poisonedForbiddenFruits) {
				if(f.equals(item)) returnItem = f;
			}
			poisonedForbiddenFruits.remove(item);
		}
		return returnItem;
	}

	private void utilStoreMaxOneItem(Inventory inventory, Item item) {
		if(isInInventory(item)) throw new IllegalStateException("One of this Item is already stored in the Cupboard! Limit of one at a time for this type.");
		inventory.addItem(item);
	}
	
	private Inventory classifyCupboardItem(Item item) {
		if(item instanceof Ingredient) return ingredients;
		if(item instanceof Recipe) return recipies;
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
		if(item instanceof Recipe) {
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
	
	public Quest consume(Edible edible) {
		getOutItem(edible);
		int manaPoint = 0;
		if(player instanceof MagicPlayer) {
			MagicPlayer magicPlayer = (MagicPlayer) player;
			manaPoint = magicPlayer.getManaPoint();
		}
		int[] points = edible.consume(manaPoint, player.getHealthPoint(), player.getExperiencePoint());
		utilConsumeSetPlayerPoint(points[0], points[1], points[2]);
		if(edible instanceof ForbiddenFruit) {
			ForbiddenFruit fruit = (ForbiddenFruit) edible;
			return fruit.getQuestUnlocked();
		}
		return null;
	}
	
	public void poison(ForbiddenFruit fruit, Potion potion) {
		if(!isInInventory(fruit)) throw new NullPointerException("Forbidden Fruit to poison not in Cupboard.");
		if(!isInInventory(potion)) throw new NullPointerException("Potion to poison with not in Cupboard.");
		potions.getOutItem(potion);
		forbiddenFruits.getOutItem(fruit);
		fruit.setPoison(potion);
		store(fruit);
		poisonedForbiddenFruits.add(fruit);
	}
	
	public boolean haveAllIngredients(Recipe recipe) {
		Ingredient[] requiredIngredients = recipe.getIngredients();
		for(Ingredient i : requiredIngredients) {
			if(!ingredients.isInInventory(i)) return false;
		}
		return true;
	}
	
	public void cook(Recipe recipe) {
		if(player instanceof MagicPlayer) {
			MagicPlayer magicPlayer = (MagicPlayer) player;
			if(haveAllIngredients(recipe)) {
				recipies.getOutItem(recipe);
				Ingredient[] storedIngredients = recipe.getIngredients();
				for(Ingredient i : storedIngredients) ingredients.getOutItem(i);
				Potion potion = recipe.cook(magicPlayer.getManaPoint(), magicPlayer.getExperiencePoint());
				store(potion);
				store(recipe);
			}
		} else {
			throw new IllegalCallerException("Only Magic Players can cook from Recipies.");
		}
	}

	@Override
	public String toString() {
		return getPlayer().getPlayerClass() + ", " + player.getRace() + "\nEDIBLES:\n" + edibles.toString() + "\nFORBIDDEN FRUITS:\n" + forbiddenFruits.toString() + "\nPOTIONS:\n" + potions.toString() + "\nINGREDIENTS:\n" + ingredients.toString() + "\nRECIPIES:\n" + recipies.toString();

	}
}