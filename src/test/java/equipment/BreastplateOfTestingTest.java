package equipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BreastplateOfTestingTest {
    @Test
    void nameTest(){
        BreastplateOfTesting bpt = new BreastplateOfTesting();
        assertTrue(bpt.getName().equals("Breastplate of Testing"));
    }
    @Test
    void attributeTest(){
        BreastplateOfTesting bpt = new BreastplateOfTesting();
        assertArrayEquals(new int[]{0, 2, 5, 3},bpt.getAttributes());
    }
}