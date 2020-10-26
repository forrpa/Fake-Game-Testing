package unit;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void playerWithTenHealthGetsHealthDecreasedByFive(){
        Player player = new Player("Warrior", "Orc", 10, 0);
        player.decreaseHealth(5);
        assertEquals(5, player.getHealthPoint());
    }
    @Test
    void playerWith55000HealthThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Player("Warrior", "Orc", 55000, 0));
    }
    @Test
    void playerWith100HealthGetsMaxHealthIncreasedWith50(){
        Player player = new Player("Warrior", "Orc", 100, 0);
        player.increaseMaxHealthPoint(50);
        assertEquals(150, player.getMaxHealthPoint());
        assertEquals(100, player.getHealthPoint());
    }
}
