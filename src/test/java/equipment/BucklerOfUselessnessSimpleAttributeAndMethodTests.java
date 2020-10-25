package equipment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BucklerOfUselessnessSimpleAttributeAndMethodTests { 
	final static Gear BUCKLER_OF_USELESSNESS = new BucklerOfUselessness();
	@BeforeEach
	void repairRobes() { 
		BUCKLER_OF_USELESSNESS.repair();
	}
	@Test
	void testIfArmorSlotIsCorrect() {
		assertTrue(((Shield) BUCKLER_OF_USELESSNESS).getSlot().equals("shield"));
	}
	@Test
	void testIfArmorTypeIsCorrect() {
		assertEquals(ArmorType.SHIELD, ((Shield) BUCKLER_OF_USELESSNESS).getArmorType());
	}
	@Test
    void testForCorrectValueFromToStringMethodInShield() {
    	assertTrue(BUCKLER_OF_USELESSNESS.toString().equals("Buckler of Uselessness"+"\n"+"A buckler so small and weak it is practically useless."+"\n"+"SHIELD"+" "+"shield"+"\n"+"Armor: "+10+"\n"+"Strength: "+0+"\n"+"Agility: "+0+"\n"+"Intelligence: "+0+"\n"+"Stamina: "+0+"\n"+"Required Level: "+0+"\n"+"Durability: "+40+"/"+40));
    } 
	@ParameterizedTest
	@ValueSource(ints = {40, 35, 0, 21, 1, 19})
    void testForCorrectDurabilityValueOfDamagedBuckler(int value) {
    	BUCKLER_OF_USELESSNESS.damageDurability(value);
    	assertEquals(40-value, BUCKLER_OF_USELESSNESS.getDurability()); 
    } 
	@ParameterizedTest
	@ValueSource(ints = {41, 55, Integer.MAX_VALUE, 78, 1000, 69})
    void testForCorrectDurabilityZeroSettingOfDamagedBuckler(int value) {
    	BUCKLER_OF_USELESSNESS.damageDurability(value);
    	assertEquals(0, BUCKLER_OF_USELESSNESS.getDurability());
    } 
}
