package equipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BreastplateOfTestingTest {
    @Test
    void testToMakeSureBreastplateHasCorrectName(){
        BreastplateOfTesting bpt = new BreastplateOfTesting();
        assertTrue(bpt.getName().equals("Breastplate of Testing"));
    }
    @Test
    void testToMakeSureBreastplateHasCorrectAttributValues(){
        BreastplateOfTesting bpt = new BreastplateOfTesting();
        assertArrayEquals(new int[]{0, 2, 5, 3},bpt.getAttributes());
    }
    @Test
    void testToMakeSureBreastplateIsSubclassOfChest() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertTrue(bpt instanceof Chest);
    }
    @Test
    void testToMakeSureBreastplateIsSubclassOfEquipment() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertTrue(bpt instanceof Equipment);
    }
    @Test
    void testToMakeSureBreastplateHasCorrectDescription() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertTrue(bpt.getDescription().equals("An excellent breastplate for testing things with!"));
    }
    @Test
    void testToMakeSureBreastplateHasCorrectArmorValue() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertEquals(150, bpt.getArmor());
    }
    @Test
    void testForCorrectMaxDurabilityOfBreastplate() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertEquals(100, bpt.getMaxDurability());
    }
    @Test
    void testForCorrectDurabilityValueOfDamagedBreastplate() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	bpt.damageDurability(13);
    	assertEquals(87, bpt.getDurability());
    }
}