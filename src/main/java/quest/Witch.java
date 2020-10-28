package quest;

import player.Player;
import unit.Talkable;
import unit.Villager;

public class Witch extends Villager implements Talkable {

    private static final Coordinates coordinates = new Coordinates(514, 112);
    private final SuperPotion potion = new SuperPotion();
    boolean potionGiven;

    public Witch() {
        super("Secret Cave Witch", 1000, 1000, true, null, null);
    }

    public void givePotion(Player player){
        if (player.getInventoryCount(new BatFang()) == 5){
            player.removeFromInventory(new BatFang());
            player.removeFromInventory(new BatFang());
            player.removeFromInventory(new BatFang());
            player.removeFromInventory(new BatFang());
            player.removeFromInventory(new BatFang());
            player.getCupboard().store(potion);
            potionGiven = true;
        } else {
            potionGiven = false;
        }
    }

    public boolean isFound(Player player){
        return (player.getXCoordinate() == coordinates.getX() && player.getYCoordinate() == coordinates.getY());
    }

    @Override
    public String toString() {
        return String.format("%s: Max health: %d, Attack Power: %d, Is grounded: %b, Coordinates: %d,%d, Has given potion: %b", getName(), getMaxHealthPoint(), getAttackPower(), isGrounded, coordinates.getX(), coordinates.getY(), potionGiven);
    }
}
