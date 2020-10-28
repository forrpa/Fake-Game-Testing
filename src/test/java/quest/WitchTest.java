package quest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WitchTest {

    Witch witch = new Witch();

    @Test
    void testConstructor(){
        assertEquals("Secret Cave Witch", witch.getName());
        assertEquals(1000, witch.getMaxHealthPoint());
        assertEquals(1000, witch.getAttackPower());
        assertTrue(witch.isGrounded());
        assertNull(witch.getResistance());
        assertNull(witch.getResistance());
    }

    //Testa metoder....

    @Test
    void toStringReturnsCorrectString(){
        assertEquals("Secret Cave Witch: Max health: 1000, Attack Power: 1000, Is grounded: true, Coordinates: 514,112, Has given potion: false", witch.toString());
    }

}