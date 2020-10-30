package quest;

import player.Player;
import unit.Monster;
import unit.Talkable;

public class StealthAndAttackEnemy extends Monster implements Talkable {

    private static final Coordinates coordinates = new Coordinates(254, 566);

    public StealthAndAttackEnemy() {
        super("Stealth and attack enemy", 50, 3, 100, null, null);
    }

    public boolean isFound(Player player){
        if (player.getXCoordinate() == coordinates.getX() && player.getYCoordinate() == coordinates.getY()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean talk() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Max Health: %d, Current Health: %d, Attack Power: %d, Resistance: %s, Weakness %s, Coordinates: %d,%d", getName(), getMaxHealthPoint(), getHealthPoint(), getAttackPower(), getResistance(), getWeakness(), coordinates.getX(), coordinates.getY());
    }
}
