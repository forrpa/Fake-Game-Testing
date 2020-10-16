package edible;

import player.Player;

abstract class Storage {
	
	private Player player;
	private final Inventory inventory = new Inventory();
	
	public Storage(Player player) {
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
	
	// must start: instance of item type(subclass) checking, wrong type for certain subclass of Storage throws IAE
	// then: getInventory().addItem(item);
	abstract void store(Item item);

	// may make sense to return Item or getItem method in Inventory class?
	// must start to check availability: getInventory().removeItem(item);
	// then: player is effected in different ways depending on item type, removeItem method checks availability
	abstract void useUp(Item item);
	
	public String toString() {
		// if inventory empty throw ISE
		return getPlayer().toString() + "\n" + getInventory().toString();
	}

}
