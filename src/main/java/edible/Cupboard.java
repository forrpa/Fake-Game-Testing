package edible;

import player.Player;

public class Cupboard extends Storage {
	
	// thinking player class has an attribute Cupboard...so can be called for example player1.getCupboard().store(redApple)
	// and player1.getCupboard().useUp(redApple)
	// and player1.getCupboard().toString()

	public Cupboard(Player player) {
		super(player);
	}

	@Override
	void store(Item item) {
		// must start: instance of item type(subclass) checking, wrong type for certain subclass of Storage throws IAE
		getInventory().addItem(item);
	}

	@Override
	public void useUp(Item item) {
		getInventory().removeItem(item);
		Edible edible = (Edible) item;
		//getPlayer().setManaPoint(edible.getManaPoint());
		getPlayer().setHealthPoint(edible.getHealthPoint());
		getPlayer().setExperiencePoint(edible.getExperiencePoint());
	}
	
}
