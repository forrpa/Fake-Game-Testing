package test.java.se.su.dsv.projektarbete.unit;

import main.java.se.su.dsv.projektarbete.unit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void batNameBatSetByConstructor(){
        Monster bat = new Bat("Jabba the Bat", 5, 2);
        assertEquals("Jabba the Bat", bat.getName());
    }
    @Test
    void batNameSetTo556ByConstructorThrowsIllegalFormatException(){
        assertThrows(IllegalStateException.class, () -> new Bat("556", 5, 2));
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
    @Test
    void wolfMonsterAttackOtherWolfFor3HealthOtherWolfHas5HealthRemaining(){
        Monster wolf = new Wolf();
        Monster otherWolf = new Wolf();
        assertTrue(wolf.Attack(otherWolf));
        assertEquals(5, otherWolf.getHealth());
    }
    @Test
    void wolfMonsterAttackOtherWolfFor3ThriceOtherWolfHas0HealthRemaining(){
        Monster wolf = new Wolf();
        Monster otherWolf = new Wolf();
        for (int i = 0;i < 3; i++){
            assertTrue(wolf.Attack(otherWolf));
        }
        assertEquals(0, otherWolf.getHealth());
    }
    @Test
    void wolfMonsterAttackOtherWolfWith0HealthFails(){
        Monster wolf = new Wolf();
        Monster otherWolf = new Wolf("Wolf", 0, 0);
        assertFalse(wolf.Attack(otherWolf));
    }
    @Test
    void wolfWith0HealthAttacksOtherWolfFails(){
        Monster wolf = new Wolf("Wolf", 0, 4);
        Monster otherWolf = new Wolf();
        assertFalse(wolf.Attack(otherWolf));
    }



}