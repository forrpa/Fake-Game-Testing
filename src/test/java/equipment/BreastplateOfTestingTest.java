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
    @Test
    void isChestTest() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertTrue(bpt instanceof Chest);
    }
    @Test
    void isClassAnEquipment() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertTrue(bpt instanceof Equipment);
    }
    @Test
    void hasCorrectDescription() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertTrue(bpt.getDescription().equals("An excellent breastplate for testing things with!"));
    }
    @Test
    void breastplateHasCorrectArmorValue() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertEquals(150, bpt.getArmor());
    }
    @Test
    void checkForMaxDurabilityOfBreastplate() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertEquals(100, bpt.getMaxDurability());
    }
    @Test
    void damagedDurCheck() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertEquals(87, bpt.getDurability());
    }
}