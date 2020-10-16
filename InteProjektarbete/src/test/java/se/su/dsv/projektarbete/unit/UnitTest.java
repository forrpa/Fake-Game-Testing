package test.java.se.su.dsv.projektarbete.unit;

import main.java.se.su.dsv.projektarbete.unit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void batNameBatSetByConstructor(){
        Monster bat = new Bat("Jabba the Bat");
        assertEquals("Jabba the Bat", bat.getName());
    }
    @Test
    void batNameSetTo556ByConstructorThrowsIllegalFormatException(){
        assertThrows(IllegalStateException.class, () -> new Bat("556"));
    }
    @Test
    void batMonsterAttackWolfSuccessful(){
        Monster bat = new Bat();
        Monster wolf = new Wolf();
        assertTrue(bat.Attack(wolf));
    }
    @Test
    void wolfMonsterAttackBatUnsuccessful(){
        Monster bat = new Bat();
        Monster wolf = new Wolf();
        assertFalse(wolf.Attack(bat));
    }
    @Test
    void batMonsterAttackWolfFor2HealthWolfHas6HealthRemaining(){
        Monster bat = new Bat();
        Monster wolf = new Wolf();
        assertTrue(bat.Attack(wolf));
        assertEquals(6,wolf.getHealth());
    }



}