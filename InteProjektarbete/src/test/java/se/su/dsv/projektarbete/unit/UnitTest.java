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



}