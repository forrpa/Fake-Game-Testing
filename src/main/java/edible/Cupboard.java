package edible;

import player.Player;

public class Cupboard {
	
	// thinking player class has an attribute Cupboard...so can be called for example player1.getCupboard().store(redApple)
	// and player1.getCupboard().useUp(redApple)
	// and player1.getCupboard().toString()
	
	private Player player;
	private final Inventory inventory = new Inventory();
	private final Inventory cookedRecipies = new Inventory();
	
	// must start: instance of item type(subclass) checking, wrong type for certain subclass of Storage throws IAE
	// then: getInventory().addItem(item);

	// may make sense to return Item or getItem method in Inventory class?
	// must start to check availability: getInventory().removeItem(item);
	// then: player is effected in different ways depending on item type, removeItem method checks availability
	// ...edibles are used up for example but weapons can be put back

	public Cupboard(Player player) {
		this.setPlayer(player);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	// store associates and item with a certain player
	// each store method checks that only certain classes of objects can be stored in this inventory, only to shorten down LOC in one file as different classes of items are used in different ways -> read use method
	public void store(Item item) {
		// if item instance of Edible or Recipie false throw IAE
		// must start: instance of item type(subclass) checking, wrong type for certain subclass of Storage throws IAE
		// if recipie and already exists throw ISE
		getInventory().addItem(item);
	}
	
	private void consume(Edible edible) {
		//getPlayer().setManaPoint(edible.getManaPoint());
		getPlayer().setHealthPoint(edible.getHealthPoint());
		getPlayer().setExperiencePoint(edible.getExperiencePoint());
		if(edible instanceof ForbiddenFruit) {
			ForbiddenFruit fruit = (ForbiddenFruit) edible;
			fruit.getQuest().startQuest(player);
		}
	}
	
	private void cook(Recipie recipie) {
		//Potion potion = recipie.cook(player.getManaPoint(), player.getExperiencePoint());
		Potion potion = recipie.cook(player.getExperiencePoint());
		store(potion);
		store(recipie);
	}

	// use identifies the type of a item from a small selection of classes that make sense to group from a user perspective and calls the class specific interaction of an item and the player
	public void use(Item item) {
		getInventory().getOutItem(item);
		if(item instanceof Recipie) {
			Recipie recipie = (Recipie) item;
			//Potion potion = recipie.cook(player.getManaPoint(), player.getExperiencePoint());
			Potion potion = recipie.cook(player.getExperiencePoint());
			store(potion);
			store(recipie);
			return;
		}
		Edible edible = (Edible) item;
		consume(edible);
	}
	
	public Item trade(Edible edible, Player player) {
		// if player is player throw NPE
		Item item = null;
		return item;
	}
	
	@Override
	public String toString() {
		// if inventory empty throw ISE
		return getPlayer().toString() + "\n" + getInventory().toString();

	}
}