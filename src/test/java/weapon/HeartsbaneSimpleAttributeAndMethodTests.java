package weapon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import equipment.Gear;
import item.Item;

class HeartsbaneSimpleAttributeAndMethodTests {
	
	final static Gear HEARTSBANE = new Heartsbane();
	@BeforeEach
	void repairHeartsbane() {
		HEARTSBANE.repair();
	}
    @Test
    void testToMakeSureHeartsbaneHasCorrectName(){
        assertTrue(HEARTSBANE.getName().equals("Heartsbane"));
    }
    @Test
    void testToMakeSureHeartsbaneHasCorrectAttributValues(){
        assertArrayEquals(new int[]{46, 28, 0, 18},( (Weapon) HEARTSBANE).getAttributes());
    }
    @Test
    void testToMakeSureHeartsbaneHasCorrectDamageRange() {
    	assertEquals(189,((Weapon) HEARTSBANE).getDamage());
    }
    @Test
    void testToMakeSureHeartsbaneIsSubclassOfTwoHandedSword() {
    	assertFalse(HEARTSBANE instanceof OneHandedSword);
    	assertTrue(HEARTSBANE instanceof TwoHandedSword);
    }
    @Test
    void testToMakeSureHeartsbaneIsSubclassOfWeapon() {
    	assertTrue(HEARTSBANE instanceof Weapon);
    }
    @Test
    void testToMakeSureHeartsbaneIsSubclassOfGear() {
    	assertTrue(HEARTSBANE instanceof Gear);
    }
    @Test
    void testToMakeSureHeartsbaneIsSubclassOfItem() {
    	assertTrue(HEARTSBANE instanceof Item);
    }
    @Test
    void testToMakeSureHeartsbaneHasCorrectDescription() {
    	assertTrue(HEARTSBANE.getDescription().equals("A valyrian steel two-handed greatsword that belongs to a giant twat called Randyll Tarly."));
    }
    @Test
    void testForCorrectMaxDurabilityOfHeartsbane() {
    	assertEquals(117, HEARTSBANE.getMaxDurability());
    }
    @Test
    void testForCorrectDurabilityValueOfDamagedHeartsbane() {
    	HEARTSBANE.damageDurability(18);
    	assertEquals(99, HEARTSBANE.getDurability());
    }
    @Test
    void testRepair() {
    	HEARTSBANE.damageDurability(60);
    	HEARTSBANE.repair();
    	assertEquals(117, HEARTSBANE.getDurability());
    }
    @Test
    void testWeaponTypeOfHeartsbane() {
    	assertTrue(((TwoHandedSword) HEARTSBANE).getType().equals("TwoHandedSword"));
    }
    @Test
    void testForCorrectSizeValueFromHeartsbane() {
    	assertEquals(WeaponSize.TwoHanded, ((TwoHandedSword) HEARTSBANE).getSize());
    }
    @Test
    void testForCorrectValueFromToStringMethodInHeartsbane() {
    	assertTrue(HEARTSBANE.toString().equals("Heartsbane"+"\n"+"A valyrian steel two-handed greatsword that belongs to a giant twat called Randyll Tarly."+"\n"+"TwoHandedSword"+"\n"+"Damage: 189"+"\n"+"Strength: "+46+"\n"+"Agility: "+28+"\n"+"Intelligence: "+0+"\n"+"Stamina: "+18+"\n"+"Required Level: "+35+"\n"+"Durability: "+117+"/"+117));
    }
    @Test
    void testForCorrectValueOfHeartsbanesRequiredLevel() {
    	assertEquals(35, HEARTSBANE.getRequiredLevel());
    }
    @Test
    void testIfTwoHeartsbanesAreConsideredTheSame() {
    	Heartsbane hb = new Heartsbane();
    	assertEquals(hb, HEARTSBANE);
    	assertTrue(hb.equals(HEARTSBANE));
    }
}
