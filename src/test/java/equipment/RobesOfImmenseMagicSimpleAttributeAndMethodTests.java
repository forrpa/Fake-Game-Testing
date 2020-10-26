package equipment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import item.Item;

class RobesOfImmenseMagicSimpleAttributeAndMethodTests {
	final static Gear ROBES_OF_IMMENSE_MAGIC = new RobesOfImmenseMagic();
	@BeforeEach
	void repairRobes() { 
		ROBES_OF_IMMENSE_MAGIC.repair();
	}
    @Test
    void testToMakeSureRobesHasCorrectName(){
        assertTrue(ROBES_OF_IMMENSE_MAGIC.getName().equals("Robes of Immense Magic"));
    }
    @Test
    void testToMakeSureRobesHasCorrectAttributValues(){
        assertArrayEquals(new int[]{0, 0, 100, 20},((Equipment) ROBES_OF_IMMENSE_MAGIC).getAttributes());
    }
    @Test
    void testToMakeSureRobesIsSubclassOfChest() {
    	assertTrue(ROBES_OF_IMMENSE_MAGIC instanceof Chest);
    }
    @Test
    void testToMakeSureRobesIsSubclassOfEquipment() {
    	assertTrue(ROBES_OF_IMMENSE_MAGIC instanceof Equipment);
    }
    @Test
    void testToMakeSureRobesIsSubclassOfGear() {
    	assertTrue(ROBES_OF_IMMENSE_MAGIC instanceof Gear);
    }
    @Test
    void testToMakeSureRobesIsSubclassOfItem() {
    	assertTrue(ROBES_OF_IMMENSE_MAGIC instanceof Item);
    }
    @Test
    void testToMakeSureRobesHasCorrectDescription() {
    	assertTrue(ROBES_OF_IMMENSE_MAGIC.getDescription().equals("A beautiful piece of silken robes that imbue the wearer with immense magical power!"));
    }
    @Test
    void testToMakeSureRobesHasCorrectArmorValue() {
    	assertEquals(30, ((Equipment) ROBES_OF_IMMENSE_MAGIC).getArmor());
    }
    @Test
    void testForCorrectMaxDurabilityOfRobes() {
    	assertEquals(85, ROBES_OF_IMMENSE_MAGIC.getMaxDurability());
    }
    @ParameterizedTest
	@ValueSource(ints = {85, 35, 0, 21, 1, 78})
    void testForCorrectDurabilityValueOfDamagedRobes(int value) {
    	ROBES_OF_IMMENSE_MAGIC.damageDurability(value);
    	assertEquals(85-value, ROBES_OF_IMMENSE_MAGIC.getDurability()); 
    } 
    @ParameterizedTest
   	@ValueSource(ints = {86, 100, Integer.MAX_VALUE, 999, 2578, 213})
       void testForCorrectDurabilityZeroSettingOfDamagedRobes(int value) {
       	ROBES_OF_IMMENSE_MAGIC.damageDurability(value);
       	assertEquals(0, ROBES_OF_IMMENSE_MAGIC.getDurability()); 
       }
    @Test
    void testRepair() {
    	ROBES_OF_IMMENSE_MAGIC.damageDurability(40);
    	ROBES_OF_IMMENSE_MAGIC.repair();
    	assertEquals(85, ROBES_OF_IMMENSE_MAGIC.getDurability());
    }
    @Test
    void testArmorTypeOfRobes() {
    	assertEquals(ArmorType.CLOTH, ((RobesOfImmenseMagic) ROBES_OF_IMMENSE_MAGIC).getArmorType());
    }
    @Test
    void testForCorrectSlotValueFromRobes() {
    	assertTrue(((Chest) ROBES_OF_IMMENSE_MAGIC).getSlot().equals("chest"));
    }
    @Test
    void testForCorrectValueFromToStringMethodInRobes() {
    	assertTrue(ROBES_OF_IMMENSE_MAGIC.toString().equals("Robes of Immense Magic"+"\n"+"A beautiful piece of silken robes that imbue the wearer with immense magical power!"+"\n"+"CLOTH"+" "+"chest"+"\n"+"Armor: "+30+"\n"+"Strength: "+0+"\n"+"Agility: "+0+"\n"+"Intelligence: "+100+"\n"+"Stamina: "+20+"\n"+"Required Level: "+37+"\n"+"Durability: "+85+"/"+85));
    }
    @Test
    void testForCorrectValueOfRobesRequiredLevel() {
    	assertEquals(37, ROBES_OF_IMMENSE_MAGIC.getRequiredUnitLevel());
    }
    @Test
    void testIfTwoRobesAreConsideredTheSame() { 
    	RobesOfImmenseMagic rim = new RobesOfImmenseMagic();
    	assertEquals(rim, ROBES_OF_IMMENSE_MAGIC);
    	assertTrue(rim.equals(ROBES_OF_IMMENSE_MAGIC));
    }
}
