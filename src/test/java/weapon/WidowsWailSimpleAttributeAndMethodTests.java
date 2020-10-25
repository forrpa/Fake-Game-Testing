package weapon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import equipment.Gear;
import item.Item;

class WidowsWailSimpleAttributeAndMethodTests {

	final static Gear WIDOWS_WAIL = new WidowsWail();
	@BeforeEach
	void repairWidowsWail() {
		WIDOWS_WAIL.repair();
	}
    @Test
    void testToMakeSureWidowsWailHasCorrectName(){
        assertTrue(WIDOWS_WAIL.getName().equals("Widow's Wail"));
    }
    @Test
    void testToMakeSureWidowsWailHasCorrectAttributValues(){
        assertArrayEquals(new int[]{17, 14, 0, 10},( (Weapon) WIDOWS_WAIL).getAttributes());
    }
    @Test
    void testToMakeSureWidowsWailHasCorrectDamageRange() {
    	assertEquals(87,((Weapon) WIDOWS_WAIL).getDamage());
    }
    @Test
    void testToMakeSureWidowsWailIsSubclassOfOneHandedSword() {
    	assertTrue(WIDOWS_WAIL instanceof OneHandedSword);
    	assertFalse(WIDOWS_WAIL instanceof TwoHandedSword);
    }
    @Test
    void testToMakeSureWidowsWailIsSubclassOfWeapon() {
    	assertTrue(WIDOWS_WAIL instanceof Weapon);
    }
    @Test
    void testToMakeSureWidowsWailIsSubclassOfGear() {
    	assertTrue(WIDOWS_WAIL instanceof Gear);
    }
    @Test
    void testToMakeSureWidowsWailIsSubclassOfItem() {
    	assertTrue(WIDOWS_WAIL instanceof Item);
    }
    @Test
    void testToMakeSureWidowsWailHasCorrectDescription() {
    	assertTrue(WIDOWS_WAIL.getDescription().equals("A valyrian steel blade that once belonged to a little shit called Joffrey Baratheon."));
    }
    @Test
    void testForCorrectMaxDurabilityOfWidowsWail() {
    	assertEquals(102, WIDOWS_WAIL.getMaxDurability());
    }
    @ParameterizedTest
	@ValueSource(ints = {102, 100, 0, 55, 1, 78})
    void testForCorrectDurabilityValueOfDamagedWidowsWail(int value) {
    	WIDOWS_WAIL.damageDurability(value);
    	assertEquals(102-value, WIDOWS_WAIL.getDurability()); 
    } 
	@ParameterizedTest
	@ValueSource(ints = {103, 114, Integer.MAX_VALUE, 214, 1000, 666})
    void testForCorrectDurabilityZeroSettingOfDamagedWidowsWail(int value) {
		WIDOWS_WAIL.damageDurability(value);
    	assertEquals(0, WIDOWS_WAIL.getDurability());
    }
    @Test
    void testRepair() {
    	WIDOWS_WAIL.damageDurability(40);
    	WIDOWS_WAIL.repair();
    	assertEquals(102, WIDOWS_WAIL.getDurability());
    }
    @Test
    void testWeaponTypeOfWidowsWail() {
    	assertTrue(((OneHandedSword) WIDOWS_WAIL).getType().equals("OneHandedSword"));
    }
    @Test
    void testForCorrectSizeValueFromWidowsWail() {
    	assertEquals(WeaponSize.OneHanded, ((OneHandedSword) WIDOWS_WAIL).getSize());
    }
    @Test
    void testForCorrectValueFromToStringMethodInWidowsWail() {
    	assertTrue(WIDOWS_WAIL.toString().equals("Widow's Wail"+"\n"+"A valyrian steel blade that once belonged to a little shit called Joffrey Baratheon."+"\n"+"OneHandedSword"+"\n"+"Damage: 87"+"\n"+"Strength: "+17+"\n"+"Agility: "+14+"\n"+"Intelligence: "+0+"\n"+"Stamina: "+10+"\n"+"Required Level: "+20+"\n"+"Durability: "+102+"/"+102));
    }
    @Test
    void testForCorrectValueOfWidowsWailsRequiredLevel() {
    	assertEquals(20, WIDOWS_WAIL.getRequiredLevel());
    }
    @Test
    void testIfTwoWidowsWailsAreConsideredTheSame() {
    	WidowsWail ww = new WidowsWail();
    	assertEquals(ww, WIDOWS_WAIL);
    	assertTrue(ww.equals(WIDOWS_WAIL));
    }
}
