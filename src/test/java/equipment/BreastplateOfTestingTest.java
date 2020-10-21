package equipment;

import org.junit.jupiter.api.Test;

import item.Item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class BreastplateOfTestingTest {
	final static Gear BREASTPLATE_OF_TESTING = new BreastplateOfTesting();
	@BeforeEach
	void repairBreastplate() {
		BREASTPLATE_OF_TESTING.repair();
	}
    @Test
    void testToMakeSureBreastplateHasCorrectName(){
        assertTrue(BREASTPLATE_OF_TESTING.getName().equals("Breastplate of Testing"));
    }
    @Test
    void testToMakeSureBreastplateHasCorrectAttributValues(){
        assertArrayEquals(new int[]{0, 2, 5, 3},((Equipment) BREASTPLATE_OF_TESTING).getAttributes());
    }
    @Test
    void testToMakeSureBreastplateIsSubclassOfChest() {
    	assertTrue(BREASTPLATE_OF_TESTING instanceof Chest);
    }
    @Test
    void testToMakeSureBreastplateIsSubclassOfEquipment() {
    	assertTrue(BREASTPLATE_OF_TESTING instanceof Equipment);
    }
    @Test
    void testToMakeSureBreastplateIsSubclassOfGear() {
    	assertTrue(BREASTPLATE_OF_TESTING instanceof Gear);
    }
    @Test
    void testToMakeSureBreastplateIsSubclassOfItem() {
    	assertTrue(BREASTPLATE_OF_TESTING instanceof Item);
    }
    @Test
    void testToMakeSureBreastplateHasCorrectDescription() {
    	assertTrue(BREASTPLATE_OF_TESTING.getDescription().equals("An excellent breastplate for testing things with!"));
    }
    @Test
    void testToMakeSureBreastplateHasCorrectArmorValue() {
    	assertEquals(150, ((Equipment) BREASTPLATE_OF_TESTING).getArmor());
    }
    @Test
    void testForCorrectMaxDurabilityOfBreastplate() {
    	assertEquals(100, BREASTPLATE_OF_TESTING.getMaxDurability());
    }
    @Test
    void testForCorrectDurabilityValueOfDamagedBreastplate() {
    	BREASTPLATE_OF_TESTING.damageDurability(13);
    	assertEquals(87, BREASTPLATE_OF_TESTING.getDurability());
    }
    @Test
    void testRepair() {
    	BREASTPLATE_OF_TESTING.damageDurability(13);
    	BREASTPLATE_OF_TESTING.repair();
    	assertEquals(100, BREASTPLATE_OF_TESTING.getDurability());
    }
    @Test
    void testArmorTypeOfBreastplate() {
    	assertEquals(ArmorType.PLATE, ((BreastplateOfTesting) BREASTPLATE_OF_TESTING).getArmorType());
    }
}