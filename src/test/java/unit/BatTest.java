package unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BatTest {

    @Test
    void getGroundedMakesBatGrounded() {
        Bat bat = new Bat();
        assertFalse(bat.isGrounded);
        bat.getGrounded();
        assertTrue(bat.isGrounded);
    }

    @Test
    void getOffGround() {
        Bat bat = new Bat();
        bat.getGrounded();
        assertTrue(bat.isGrounded);
        bat.getOffGround();
        assertFalse(bat.isGrounded);
    }
}