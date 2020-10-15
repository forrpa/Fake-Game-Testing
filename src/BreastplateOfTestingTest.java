import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BreastplateOfTestingTest {
    @Test
    void nameTest(){
        BreastplateOfTesting bpt = new BreastplateOfTesting();
        assertTrue(bpt.getName().equals("Breastplate of Testing"));
    }
}