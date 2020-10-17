package edible;

import player.Player;

public class Cupboard {
	
	// thinking player class has an attribute Cupboard...so can be called for example player1.getCupboard().store(redApple)
	// and player1.getCupboard().useUp(redApple)
	// and player1.getCupboard().toString()
	
	private Player player;
	private final Inventory inventory = new Inventory();
	
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

	public void store(Item item) {
		// must start: instance of item type(subclass) checking, wrong type for certain subclass of Storage throws IAE
		getInventory().addItem(item);
	}

	public void consume(Item item) {
		getInventory().removeItem(item);
		Edible edible = (Edible) item;
		//getPlayer().setManaPoint(edible.getManaPoint());
		getPlayer().setHealthPoint(edible.getHealthPoint());
		getPlayer().setExperiencePoint(edible.getExperiencePoint());
	}
	
	public Item giveAway(Player player) {
		Item item = null;
		return item;
	}
	
	public String toString() {
		// if inventory empty throw ISE
		return getPlayer().toString() + "\n" + getInventory().toString();

	}
}