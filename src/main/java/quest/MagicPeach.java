package quest;

import edible.Edible;
import player.Player;

public class MagicPeach extends Edible {

    private boolean hasBeenFoundByPlayer = false;
    private static final Coordinates coordinates = new Coordinates(123, 2899);

    public MagicPeach() {
        super("Magic Peach", "What will happen if you eat this seemingly normal fruit?", 0, 10, 10);
    }

    public boolean hasBeenFoundByPlayer(Player player) {
        return hasBeenFoundByPlayer;
    }

    public boolean isFound(Player player){
        if (player.getXCoordinate() == coordinates.getX() && player.getYCoordinate() == coordinates.getY() && !hasBeenFoundByPlayer){
            hasBeenFoundByPlayer = true;
            return true;
        } else {
            return false;
        }
    }

    public void eat(Player player){
        player.increaseHealth(10);
        player.increaseExperiencePoint(10);
        //if player is magic player......
    }

    @Override
    public String toString() {
        return String.format("%s: %s Coordinates: %d,%d, has been found: %b", getName(), getDescription(), coordinates.getX(), coordinates.getY(), hasBeenFoundByPlayer);
    }
}
