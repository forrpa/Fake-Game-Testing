package quest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StealthAndAttackEnemyTest {

    StealthAndAttackEnemy enemy = new StealthAndAttackEnemy();

    @Test
    void toStringReturnsCorrectString(){
        assertEquals("Name: Stealth and attack enemy, Max Health: 50, Current Health: 50, Attack Power: 3, Resistance: null, Weakness null, Coordinates: 254,566", enemy.toString());
    }

}