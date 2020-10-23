package player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import equipment.*;
import weapon.*;

class playerTestsWithRegardsToGear {
	Player magePlayer = new Player("Mage","Human",100,0);
	Player warriorPlayer = new Player("Warrior","Human",100,0);
	@BeforeEach
	void resetPlayer() {
		warriorPlayer = new Player("Warrior","Human",100,0);
		magePlayer = new Player("Mage","Human",100,0);
	}
	@ParameterizedTest
	@ValueSource(ints = {49, 50, 100, 200, 140, 87})
	void testIfMageCanEquipRobesAndIfAttributesAreUpdated(int value){
		Gear roim = new RobesOfImmenseMagic();
		magePlayer.addToInventory(roim);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		int intBeforeRobe = magePlayer.getIntelligence();
		int strBeforeRobe = magePlayer.getStrength();
		int agiBeforeRobe = magePlayer.getAgility();
		int staBeforeRobe = magePlayer.getStamina();
		int armorBeforeRobe = magePlayer.getArmor();
		try {
			magePlayer.equipArmor((Equipment) roim);
		} catch (Exception e) {
			fail();
		}
		assertTrue(magePlayer.getGearFromGear("chest").equals(roim));
		assertTrue(magePlayer.getIntelligence()==(intBeforeRobe+100));
		assertTrue(magePlayer.getStrength()==(strBeforeRobe));
		assertTrue(magePlayer.getAgility()==(agiBeforeRobe));
		assertTrue(magePlayer.getStamina()==(staBeforeRobe+20));
		assertTrue(magePlayer.getArmor()==(armorBeforeRobe+30));
	}
	@ParameterizedTest
	@ValueSource(ints = {49, 50, 100, 200, 140, 87})
	void testIfWarriorCanEquipRobesAndIfAttributesAreUpdated(int value){
		Gear roim = new RobesOfImmenseMagic();
		warriorPlayer.addToInventory(roim);
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		int intBeforeRobe = warriorPlayer.getIntelligence();
		int strBeforeRobe = warriorPlayer.getStrength();
		int agiBeforeRobe = warriorPlayer.getAgility();
		int staBeforeRobe = warriorPlayer.getStamina();
		int armorBeforeRobe = warriorPlayer.getArmor();
		try {
			warriorPlayer.equipArmor((Equipment) roim);
		} catch (Exception e) {
			fail();
		}
		assertTrue(warriorPlayer.getGearFromGear("chest").equals(roim));
		assertTrue(warriorPlayer.getIntelligence()==(intBeforeRobe+100));
		assertTrue(warriorPlayer.getStrength()==(strBeforeRobe));
		assertTrue(warriorPlayer.getAgility()==(agiBeforeRobe));
		assertTrue(warriorPlayer.getStamina()==(staBeforeRobe+20));
		assertTrue(warriorPlayer.getArmor()==(armorBeforeRobe+30));
	}
	@ParameterizedTest
	@ValueSource(ints = {0, 5, 24, 37, 40, 48})
	void testIfLevelCriteriaForRobesWorkInEquipArmorMethod(int value) {
		Gear roim = new RobesOfImmenseMagic();
		magePlayer.addToInventory(roim);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		assertThrows(ArithmeticException.class, () -> {
			magePlayer.equipArmor((Equipment) roim);;
		});
		assertTrue(magePlayer.getGearFromGear("chest")==null);
	}
	@ParameterizedTest
	@ValueSource(ints = {6, 15, 20, 37, 40, 48})
	void testToMakeSureMageCannotWearPlate(int value) {
		Gear bpt = new BreastplateOfTesting();
		magePlayer.addToInventory(bpt);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		assertThrows(IllegalArgumentException.class, () -> {
			magePlayer.equipArmor((Equipment) bpt);;
		});
		assertTrue(magePlayer.getGearFromGear("chest")==null);
	}
	@ParameterizedTest
	@ValueSource(ints = {6, 14, 47, 103, 68, 87})
	void testIfWarriorCanEquipPlateAndIfAttributesAreUpdated(int value){
		Gear bpt = new BreastplateOfTesting();
		warriorPlayer.addToInventory(bpt);
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		int intBeforeRobe = warriorPlayer.getIntelligence();
		int strBeforeRobe = warriorPlayer.getStrength();
		int agiBeforeRobe = warriorPlayer.getAgility();
		int staBeforeRobe = warriorPlayer.getStamina();
		int armorBeforeRobe = warriorPlayer.getArmor();
		try {
			warriorPlayer.equipArmor((Equipment) bpt);
		} catch (Exception e) {
			fail();
		}
		assertTrue(warriorPlayer.getGearFromGear("chest").equals(bpt));
		assertTrue(warriorPlayer.getIntelligence()==(intBeforeRobe+5));
		assertTrue(warriorPlayer.getStrength()==(strBeforeRobe));
		assertTrue(warriorPlayer.getAgility()==(agiBeforeRobe+2));
		assertTrue(warriorPlayer.getStamina()==(staBeforeRobe+3));
		assertTrue(warriorPlayer.getArmor()==(armorBeforeRobe+150));
	}
	@Test
	void testToMakeSureMageCannotWearShield() {
		Gear bou = new BucklerOfUselessness();
		magePlayer.addToInventory(bou);
		for(int i=0;i<17;i++) {magePlayer.increaseLevel();}
		assertThrows(IllegalArgumentException.class, () -> {
			magePlayer.equipArmor((Equipment) bou);;
		});
		assertTrue(magePlayer.getGearFromGear("shield")==null);
	}
	@Test
	void testIfInventoryControlForEquippingGearWorks() {
		Gear roim = new RobesOfImmenseMagic();
		for(int i=0;i<66;i++) {magePlayer.increaseLevel();}
		int intell1 = magePlayer.getIntelligence();
		assertThrows(NullPointerException.class, () -> {
			magePlayer.equipArmor((Equipment) roim);
		});
		assertTrue(magePlayer.getIntelligence()==intell1);
		assertTrue(magePlayer.getGearFromGear("chest")==null);
	}
	@ParameterizedTest
	@ValueSource(ints = {20, 19, 68, 37, 40, 48})
	void testIfMageCanEquipOneHandedSwordsAndAttributesAreUpdated(int value) {
		Gear ww = new WidowsWail();
		magePlayer.addToInventory(ww);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		int intBeforeWW = magePlayer.getIntelligence();
		int strBeforeWW = magePlayer.getStrength();
		int agiBeforeWW = magePlayer.getAgility();
		int staBeforeWW = magePlayer.getStamina();
		int dmgBeforeWW = magePlayer.getAttackPower();
		try {
			magePlayer.equipWeapon((Weapon) ww);
		} catch (Exception e) {
			fail();
		}
		assertTrue(magePlayer.getGearFromGear("weapon").equals(ww));
		assertTrue(magePlayer.getIntelligence()==(intBeforeWW));
		assertTrue(magePlayer.getStrength()==(strBeforeWW+17));
		assertTrue(magePlayer.getAgility()==(agiBeforeWW+14));
		assertTrue(magePlayer.getStamina()==(staBeforeWW+10));
		assertTrue(magePlayer.getAttackPower()==(dmgBeforeWW+87+(5*17)));
		assertFalse(magePlayer.isInInventory(ww));
	}
	@ParameterizedTest
	@ValueSource(ints = {0, 3, 6, 17, 18, 18})
	void testIfLevelCriteriaForWidowsWailWorkInEquipWeaponMethod(int value) {
		Gear ww = new WidowsWail();
		magePlayer.addToInventory(ww);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		assertThrows(ArithmeticException.class, () -> {
			magePlayer.equipWeapon((Weapon) ww);
		});
		assertTrue(magePlayer.getGearFromGear("weapon")==null);
	}
	@ParameterizedTest
	@ValueSource(ints = {20, 19, 68, 37, 40, 48})
	void testIfMageCanUnEquipOneHandedSwordsAndAttributesAreUpdated(int value) {
		Gear ww = new WidowsWail();
		magePlayer.addToInventory(ww);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		try {
			magePlayer.equipWeapon((Weapon) ww);
		} catch (Exception e) {
			fail();
		}
		int intAfterWW = magePlayer.getIntelligence();
		int strAfterWW = magePlayer.getStrength();
		int agiAfterWW = magePlayer.getAgility();
		int staAfterWW = magePlayer.getStamina();
		int dmgAfterWW = magePlayer.getAttackPower();
		magePlayer.unEquipGear(ww);
		assertTrue(magePlayer.getGearFromGear("weapon")==null);
		assertTrue(magePlayer.getIntelligence()==(intAfterWW));
		assertTrue(magePlayer.getStrength()==(strAfterWW-17));
		assertTrue(magePlayer.getAgility()==(agiAfterWW-14));
		assertTrue(magePlayer.getStamina()==(staAfterWW-10));
		assertTrue(magePlayer.getAttackPower()==(dmgAfterWW-87-(5*17)));
		assertTrue(magePlayer.getInventoryCount(ww)==1);
	}
	@ParameterizedTest
	@ValueSource(ints = {34, 39, 53, 67, 78, 100})
	void testToMakeSureMageCannotEquipTwoHandedSwords(int value) {
		Gear hb = new Heartsbane();
		magePlayer.addToInventory(hb);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		assertThrows(IllegalArgumentException.class, () -> {
			magePlayer.equipWeapon((Weapon) hb);;
		});
		assertTrue(magePlayer.getGearFromGear("weapon")==null);
	}
}
