package quest;

import player.Player;
import unit.Monster;
import unit.Talkable;

public class StealthAndAttackEnemy extends Monster implements Talkable {

    private static final Coordinates coordinates = new Coordinates(254, 566);

    public StealthAndAttackEnemy() {
        super("Stealth and attack enemy", 8, 3, 100, null, null);
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
}
