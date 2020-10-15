package test.java.se.su.dsv.projektarbete.unit;
import main.java.se.su.dsv.projektarbete.unit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestgiverTest {
    @Test
    void nameSetToJohnJohnsonByConstructor(){
        Questgiver qg = new Questgiver("John Johnson");
        assertEquals(qg.getName(), "John Johnson");
    }


}