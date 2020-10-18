package quest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StealtAndAttackTest {

    @Test
    void testConstructor(){
        StealtAndAttack quest = new StealtAndAttack("Stealt and Attack", "You have to follow your enemy without being seen and then attack him", "pending", true);
        assertEquals("Stealt and Attack", quest.getName());
        assertEquals("You have to follow your enemy without being seen and then attack him", quest.getDescription());
        assertEquals("pending", quest.getState());
        assertTrue(quest.isMandatory());
    }

}