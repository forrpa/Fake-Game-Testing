package test.java.se.su.dsv.projektarbete.unit;

import main.java.se.su.dsv.projektarbete.unit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    @Test
    void monsterNameBatSetByConstructor(){
        Monster bat = new Monster("Bat");
        assertEquals("Bat", bat.getName());
    }

}