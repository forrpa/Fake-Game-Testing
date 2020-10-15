package test.java.se.su.dsv.projektarbete.unit;

import main.java.se.su.dsv.projektarbete.unit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void monsterNameBatSetByConstructor(){
        Monster bat = new Monster("Bat");
        assertEquals("Bat", bat.getName());
    }
    @Test
    void monsterNameSetTo556ByConstructorThrowsIllegalFormatException(){
        assertThrows(IllegalStateException.class, () -> {
            new Monster("556");
        });
    }
    @Test
    void batMonsterAttackWolfSuccessful(){
        Monster bat = new Monster("Bat");
        Monster wolf = new Monster("Wolf");
        assertEquals(true, bat.Attack(wolf));
    }
    @Test
    void wolfMonsterAttackBatUnsuccessful(){
        Monster bat = new Monster("Bat");
        Monster wolf = new Monster("Wolf");
        assertEquals(false, wolf.Attack(bat));
    }



}