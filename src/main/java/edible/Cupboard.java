package edible;

import player.Player;

public class Cupboard {
	
	private Player player;
	private final Inventory edibles = new Inventory();
	private final Inventory forbiddenFruits = new Inventory();
	private final Inventory potions = new Inventory();
	private final Inventory ingredients = new Inventory();
	private final Inventory recipies = new Inventory();

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
			throw new IllegalStateException("One of this item is already stored in the Cupboard! Limit of one at a time.");
		} catch(NullPointerException e) {
			inventory.addItem(item);
		}
	}

	public void store(Item item) {
		if(item instanceof ForbiddenFruit) {
			utilStoreMaxOneItem(forbiddenFruits, item);
			return;
		}
		if(item instanceof Potion) {
			potions.addItem(item);
			return;
		}
		if(item instanceof Edible) {
			edibles.addItem(item);
			return;
		}
		if(item instanceof Ingredient) {
			edibles.addItem(item);
			return;
		}
		if(item instanceof Recipie) {
			utilStoreMaxOneItem(recipies, item);
			return;
		}
		throw new IllegalStateException("Item is not of type that stores in the Cupboard.");
	}
	
	private void utilConsumeSetPlayerPoints(Edible edible) {
		getPlayer().setManaPoint(player.getManaPoint() + edible.getManaPoint());
		getPlayer().setHealthPoint(player.getHealthPoint() + edible.getHealthPoint());
		getPlayer().setExperiencePoint(player.getExperiencePoint() + edible.getRequiredLevel());
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
	
	public void cook(Recipie recipie) {
		Ingredient[] requiredIngredients = recipie.getIngredients();
		for(Ingredient i : requiredIngredients) ingredients.getOutItem(i);
		Potion potion = recipie.cook(player.getManaPoint(), player.getExperiencePoint());
		store(potion);
		store(recipie);
	}
	
//	TradeOffer Class needed to implement getOutItem and place in tradeOffer object to close deal
//		
	
	// steal cupboard and empty cupboard take param other player

	@Override
	public String toString() {
		return getPlayer().toString() + "\nEDIBLES:\n" + edibles.toString() + "\nFORBIDDEN FRUITS:\n" + forbiddenFruits.toString() + "\nPOTIONS:\n" + potions.toString() + "\nINGREDIENTS:\n" + ingredients.toString() + "\nRECIPIES:\n" + recipies.toString();

	}
}