package quest;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class MagicPeachTest {

    MagicPeach magicPeach = new MagicPeach();
    Player player = new Player("Tank", "Human", 200, 1850);

    @Test
    void testConstructor(){
        assertEquals("Magic Peach", magicPeach.getName());
        assertEquals("What will happen if you eat this seemingly normal fruit?", magicPeach.getDescription());
        assertEquals(0, magicPeach.getManaPoint());
        assertEquals(10, magicPeach.getHealthPoint());
        assertEquals(10, magicPeach.getExperiencePoint());
    }

    @Test
    void hasBeenFoundByPlayerReturnsFalseWhenPlayerHasNotFoundIt (){
        assertFalse(magicPeach.hasBeenFoundByPlayer(player));
    }

    @Test
    void toStringReturnsCorrectString(){
        assertEquals("Magic Peach: What will happen if you eat this seemingly normal fruit? Coordinates: 123,2899, has been found: false", magicPeach.toString());
    }

}