package equipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import item.Item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class BreastplateOfTestingSimpleAttributeAndMethodTests {
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
    void testToMakeSureBreastplateHasCorrectAttributeValues(){
        assertArrayEquals(new int[]{0, 2, 5, 3},((Equipment) BREASTPLATE_OF_TESTING).getAttributes());
    }
    @Test
    void testToMakeSureBreastplateHasCorrectCustomAttributeValues(){
    	BreastplateOfTesting bpt = new BreastplateOfTesting(300,6,43,7,100,9);
    	BreastplateOfTesting bpt2 = new BreastplateOfTesting(60);
        assertArrayEquals(new int[]{6, 43, 7, 100},((Equipment) bpt).getAttributes());
        assertEquals(300,bpt.getArmor());
        assertEquals(9,bpt.getRequiredLevel());
        assertEquals(60,bpt2.getMaxDurability());
    }
    @ParameterizedTest
	@ValueSource(ints = {-1, -10, -1030, Integer.MIN_VALUE, -25647, -28})
    void testToMakeSureNegativeAttributesAndDurabilityAndArmorAreNotAcceptable(int value){
    	assertThrows(IllegalStateException.class, () -> {
        	BreastplateOfTesting bpt = new BreastplateOfTesting(value,4,6,4,2,4);
		});
    	assertThrows(IllegalStateException.class, () -> {
        	BreastplateOfTesting bpt = new BreastplateOfTesting(6,value,6,4,2,4);
		});
    	assertThrows(IllegalStateException.class, () -> {
        	BreastplateOfTesting bpt = new BreastplateOfTesting(6,4,value,4,2,4);
		});
    	assertThrows(IllegalStateException.class, () -> {
        	BreastplateOfTesting bpt = new BreastplateOfTesting(9,4,6,value,2,4);  
		});
    	assertThrows(IllegalStateException.class, () -> {
        	BreastplateOfTesting bpt = new BreastplateOfTesting(3,4,6,4,value,4);
		});
    	assertThrows(IllegalArgumentException.class, () -> {
        	BreastplateOfTesting bpt = new BreastplateOfTesting(3,4,6,4,5,value);
		});
    	assertThrows(IllegalStateException.class, () -> { 
        	BreastplateOfTesting bpt = new BreastplateOfTesting(value);
		});
    }
    @Test
    void testToMakeSureZeroIsNotAcceptableAsDurability() {
    	assertThrows(IllegalStateException.class, () -> {
        	BreastplateOfTesting bpt = new BreastplateOfTesting(0);
		});
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
    @ParameterizedTest
	@ValueSource(ints = {100, 95, 0, 21, 1, 19})
    void testForCorrectDurabilityValueOfDamagedBreastplate(int value) {
    	BREASTPLATE_OF_TESTING.damageDurability(value);
    	assertEquals(100-value, BREASTPLATE_OF_TESTING.getDurability());
    }
    @ParameterizedTest
	@ValueSource(ints = {100, 101, 1030, Integer.MAX_VALUE, 25647, 428})
    void testToMakeSureBreastplatesDurabilityIsSetZeroByDamageGreaterThanDurability(int value) {
    	BREASTPLATE_OF_TESTING.damageDurability(value);
    	assertEquals(0,BREASTPLATE_OF_TESTING.getDurability());
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