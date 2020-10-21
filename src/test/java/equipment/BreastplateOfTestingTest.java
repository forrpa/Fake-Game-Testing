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
    @Test
    void testForCorrectSlotValueFromBreastplate() {
    	assertTrue(((Chest) BREASTPLATE_OF_TESTING).getSlot().equals("chest"));
    }
    @Test
    void testForCorrectValueFromToStringMethodInBreastplate() {
    	assertTrue(BREASTPLATE_OF_TESTING.toString().equals("Breastplate of Testing"+"\n"+"An excellent breastplate for testing things with!"+"\n"+"PLATE"+" "+"chest"+"\n"+"Armor: "+150+"\n"+"Strength: "+0+"\n"+"Agility: "+2+"\n"+"Intelligence: "+5+"\n"+"Stamina: "+3+"\n"+"Required Level: "+7+"\n"+"Durability: "+100+"/"+100));
    }
    @Test
    void testForCorrectValueOfBreastplatesRequiredLevel() {
    	assertEquals(7, BREASTPLATE_OF_TESTING.getRequiredLevel());
    }
    @Test
    void testIfTwoBreastplatesAreConsideredTheSame() {
    	BreastplateOfTesting bpt = new BreastplateOfTesting();
    	assertEquals(bpt, BREASTPLATE_OF_TESTING);
    	assertTrue(bpt.equals(BREASTPLATE_OF_TESTING));
    }
}