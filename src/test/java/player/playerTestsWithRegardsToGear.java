package player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edible.Cupboard;
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
	@ValueSource(ints = {36, 37, 39, 40, 67, 87})
	void testIfMageCanEquipRobesAndIfAttributesAreUpdated(int value){
		Gear roim = new RobesOfImmenseMagic();
		magePlayer.addToInventory(roim);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		int intBeforeRobe = magePlayer.getIntelligence();
		int strBeforeRobe = magePlayer.getStrength();
		int agiBeforeRobe = magePlayer.getAgility();
		int staBeforeRobe = magePlayer.getStamina();
		int armorBeforeRobe = magePlayer.getArmor();
		int maxHpBeforeRobe = magePlayer.getMaxHealthPoint();
		int hpBeforeRobe = magePlayer.getHealthPoint();
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
		assertTrue(magePlayer.getMaxHealthPoint()==(maxHpBeforeRobe+200));
		assertTrue(magePlayer.getHealthPoint()==(hpBeforeRobe+200));

	}
	@ParameterizedTest
	@ValueSource(ints = {36, 37, 39, 40, 67, 87})
	void testIfWarriorCanEquipRobesAndIfAttributesAreUpdated(int value){
		Gear roim = new RobesOfImmenseMagic();
		warriorPlayer.addToInventory(roim);
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		int intBeforeRobe = warriorPlayer.getIntelligence();
		int strBeforeRobe = warriorPlayer.getStrength();
		int agiBeforeRobe = warriorPlayer.getAgility();
		int staBeforeRobe = warriorPlayer.getStamina();
		int armorBeforeRobe = warriorPlayer.getArmor();
		int maxHpBeforeRobe = warriorPlayer.getMaxHealthPoint();
		int hpBeforeRobe = warriorPlayer.getHealthPoint();
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
		assertTrue(warriorPlayer.getMaxHealthPoint()==(maxHpBeforeRobe+200));
		assertTrue(warriorPlayer.getHealthPoint()==(hpBeforeRobe+200));
	}
	@ParameterizedTest
	@ValueSource(ints = {0, 5, 24, 35, 13, 31})
	void testIfLevelCriteriaForRobesWorkInEquipArmorMethod(int value) {
		Gear roim = new RobesOfImmenseMagic();
		magePlayer.addToInventory(roim);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		assertThrows(ArithmeticException.class, () -> {
			magePlayer.equipArmor((Equipment) roim);;
		});
		assertTrue(magePlayer.getGearFromGear("chest")==null);
		assertTrue(magePlayer.isInInventory(roim));
	}
	@ParameterizedTest
	@ValueSource(ints = {6, 14, 39, 42, 26, 34})
	void testToMakeSureMageCannotWearPlate(int value) {
		Gear bpt = new BreastplateOfTesting();
		magePlayer.addToInventory(bpt);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		assertThrows(IllegalArgumentException.class, () -> {
			magePlayer.equipArmor((Equipment) bpt);;
		});
		assertTrue(magePlayer.getGearFromGear("chest")==null);
		assertTrue(magePlayer.isInInventory(bpt));
	}
	@ParameterizedTest
	@ValueSource(ints = {6, 14, 39, 42, 26, 34})
	void testIfWarriorCanEquipPlateAndIfAttributesAreUpdated(int value){
		Gear bpt = new BreastplateOfTesting();
		warriorPlayer.addToInventory(bpt);
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		int intBeforePlate = warriorPlayer.getIntelligence();
		int strBeforePlate = warriorPlayer.getStrength();
		int agiBeforePlate = warriorPlayer.getAgility();
		int staBeforePlate = warriorPlayer.getStamina();
		int armorBeforePlate = warriorPlayer.getArmor();
		int maxHpBeforePlate = warriorPlayer.getMaxHealthPoint();
		int hpBeforePlate = warriorPlayer.getHealthPoint();
		try {
			warriorPlayer.equipArmor((Equipment) bpt);
		} catch (Exception e) {
			fail();
		}
		assertTrue(warriorPlayer.getGearFromGear("chest").equals(bpt));
		assertTrue(warriorPlayer.getIntelligence()==(intBeforePlate+5));
		assertTrue(warriorPlayer.getStrength()==(strBeforePlate));
		assertTrue(warriorPlayer.getAgility()==(agiBeforePlate+2));
		assertTrue(warriorPlayer.getStamina()==(staBeforePlate+3));
		assertTrue(warriorPlayer.getArmor()==(armorBeforePlate+150));
		assertTrue(warriorPlayer.getMaxHealthPoint()==(maxHpBeforePlate+30));
		assertTrue(warriorPlayer.getHealthPoint()==(hpBeforePlate+30));
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
		assertTrue(magePlayer.isInInventory(bou));
	}
	@ParameterizedTest
	@ValueSource(ints = {6, 14, 39, 42, 26, 0})
	void testIfWarriorCanEquipShieldAndIfAttributesAreUpdated(int value){
		Gear bou = new BucklerOfUselessness();
		warriorPlayer.addToInventory(bou);
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		int intBeforeShield = warriorPlayer.getIntelligence();
		int strBeforeShield = warriorPlayer.getStrength();
		int agiBeforeShield = warriorPlayer.getAgility();
		int staBeforeShield = warriorPlayer.getStamina();
		int armorBeforeShield = warriorPlayer.getArmor();
		int maxHpBeforeShield = warriorPlayer.getMaxHealthPoint();
		int hpBeforeShield = warriorPlayer.getHealthPoint();
		try {
			warriorPlayer.equipArmor((Equipment) bou);
		} catch (Exception e) {
			fail();
		}
		assertTrue(warriorPlayer.getGearFromGear("shield").equals(bou));
		assertTrue(warriorPlayer.getIntelligence()==(intBeforeShield));
		assertTrue(warriorPlayer.getStrength()==(strBeforeShield));
		assertTrue(warriorPlayer.getAgility()==(agiBeforeShield));
		assertTrue(warriorPlayer.getStamina()==(staBeforeShield));
		assertTrue(warriorPlayer.getArmor()==(armorBeforeShield+10));
		assertTrue(warriorPlayer.getMaxHealthPoint()==(maxHpBeforeShield));
		assertTrue(warriorPlayer.getHealthPoint()==(hpBeforeShield));
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
	@ValueSource(ints = {20, 19, 39, 37, 25, 34})
	void testIfMageCanEquipOneHandedSwordsAndAttributesAreUpdated(int value) {
		Gear ww = new WidowsWail();
		magePlayer.addToInventory(ww);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		int intBeforeWW = magePlayer.getIntelligence();
		int strBeforeWW = magePlayer.getStrength();
		int agiBeforeWW = magePlayer.getAgility();
		int staBeforeWW = magePlayer.getStamina();
		int dmgBeforeWW = magePlayer.getAttackPower();
		int maxHpBeforeWW = magePlayer.getMaxHealthPoint();
		int hpBeforeWW = magePlayer.getHealthPoint();
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
		assertTrue(magePlayer.getMaxHealthPoint()==(maxHpBeforeWW+100));
		assertTrue(magePlayer.getHealthPoint()==(hpBeforeWW+100));
		assertFalse(magePlayer.isInInventory(ww));
	}
	@ParameterizedTest
	@ValueSource(ints = {20, 19, 39, 37, 25, 34})
	void testIfWarriorCanEquipOneHandedSwordsAndAttributesAreUpdated(int value) {
		Gear ww = new WidowsWail();
		warriorPlayer.addToInventory(ww);
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		int intBeforeWW = warriorPlayer.getIntelligence();
		int strBeforeWW = warriorPlayer.getStrength();
		int agiBeforeWW = warriorPlayer.getAgility();
		int staBeforeWW = warriorPlayer.getStamina();
		int dmgBeforeWW = warriorPlayer.getAttackPower();
		int maxHpBeforeWW = warriorPlayer.getMaxHealthPoint();
		int hpBeforeWW = warriorPlayer.getHealthPoint();
		try {
			warriorPlayer.equipWeapon((Weapon) ww);
		} catch (Exception e) {
			fail();
		}
		assertTrue(warriorPlayer.getGearFromGear("weapon").equals(ww));
		assertTrue(warriorPlayer.getIntelligence()==(intBeforeWW));
		assertTrue(warriorPlayer.getStrength()==(strBeforeWW+17));
		assertTrue(warriorPlayer.getAgility()==(agiBeforeWW+14));
		assertTrue(warriorPlayer.getStamina()==(staBeforeWW+10));
		assertTrue(warriorPlayer.getAttackPower()==(dmgBeforeWW+87+(5*17)));
		assertTrue(warriorPlayer.getMaxHealthPoint()==(maxHpBeforeWW+100));
		assertTrue(warriorPlayer.getHealthPoint()==(hpBeforeWW+100));
		assertFalse(warriorPlayer.isInInventory(ww));
	}
	@ParameterizedTest
	@ValueSource(ints = {0, 3, 6, 17, 11, 18})
	void testIfLevelCriteriaForWidowsWailWorkInEquipWeaponMethod(int value) {
		Gear ww = new WidowsWail();
		magePlayer.addToInventory(ww);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		assertThrows(ArithmeticException.class, () -> {
			magePlayer.equipWeapon((Weapon) ww);
		});
		assertTrue(magePlayer.getGearFromGear("weapon")==null);
		assertTrue(magePlayer.isInInventory(ww));
	}
	@ParameterizedTest
	@ValueSource(ints = {20, 19, 28, 37, 33, 36})
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
		int maxHpAfterWW = magePlayer.getMaxHealthPoint();
		int hpAfterWW = magePlayer.getHealthPoint();
		magePlayer.unEquipGear(ww);
		assertTrue(magePlayer.getGearFromGear("weapon")==null);
		assertTrue(magePlayer.getIntelligence()==(intAfterWW));
		assertTrue(magePlayer.getStrength()==(strAfterWW-17));
		assertTrue(magePlayer.getAgility()==(agiAfterWW-14));
		assertTrue(magePlayer.getStamina()==(staAfterWW-10));
		assertTrue(magePlayer.getAttackPower()==(dmgAfterWW-87-(5*17)));
		assertTrue(magePlayer.getMaxHealthPoint()==(maxHpAfterWW-100));
		assertTrue(magePlayer.getHealthPoint()==(hpAfterWW-100));
		assertTrue(magePlayer.getInventoryCount(ww)==1);
	}
	@ParameterizedTest
	@ValueSource(ints = {20, 19, 28, 37, 33, 36})
	void testIfWarriorCanUnEquipOneHandedSwordsAndAttributesAreUpdated(int value) {
		Gear ww = new WidowsWail();
		warriorPlayer.addToInventory(ww);
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		try {
			warriorPlayer.equipWeapon((Weapon) ww);
		} catch (Exception e) {
			fail();
		}
		int intAfterWW = warriorPlayer.getIntelligence();
		int strAfterWW = warriorPlayer.getStrength();
		int agiAfterWW = warriorPlayer.getAgility();
		int staAfterWW = warriorPlayer.getStamina();
		int dmgAfterWW = warriorPlayer.getAttackPower();
		int maxHpAfterWW = warriorPlayer.getMaxHealthPoint();
		int hpAfterWW = warriorPlayer.getHealthPoint();
		warriorPlayer.unEquipGear(ww);
		assertTrue(warriorPlayer.getGearFromGear("weapon")==null);
		assertTrue(warriorPlayer.getIntelligence()==(intAfterWW));
		assertTrue(warriorPlayer.getStrength()==(strAfterWW-17));
		assertTrue(warriorPlayer.getAgility()==(agiAfterWW-14));
		assertTrue(warriorPlayer.getStamina()==(staAfterWW-10));
		assertTrue(warriorPlayer.getAttackPower()==(dmgAfterWW-87-(5*17)));
		assertTrue(warriorPlayer.getMaxHealthPoint()==(maxHpAfterWW-100));
		assertTrue(warriorPlayer.getHealthPoint()==(hpAfterWW-100));
		assertTrue(warriorPlayer.getInventoryCount(ww)==1);
	}
	@ParameterizedTest
	@ValueSource(ints = {9, 6, 13, 28, 39, 21})
	void testIfWarriorCanUnEquipBreastplateAndAttributesAreUpdated(int value) {
		Gear bpt = new BreastplateOfTesting();
		warriorPlayer.addToInventory(bpt);
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		try {
			warriorPlayer.equipArmor((Equipment) bpt);
		} catch (Exception e) {
			fail();
		}
		int intAfterPlate = warriorPlayer.getIntelligence();
		int strAfterPlate = warriorPlayer.getStrength();
		int agiAfterPlate = warriorPlayer.getAgility();
		int staAfterPlate = warriorPlayer.getStamina();
		int dmgAfterPlate = warriorPlayer.getAttackPower();
		int maxHpAfterPlate = warriorPlayer.getMaxHealthPoint();
		int hpAfterPlate = warriorPlayer.getHealthPoint();
		warriorPlayer.unEquipGear(bpt);
		assertTrue(warriorPlayer.getGearFromGear("chest")==null);
		assertTrue(warriorPlayer.getIntelligence()==(intAfterPlate-5));
		assertTrue(warriorPlayer.getStrength()==(strAfterPlate));
		assertTrue(warriorPlayer.getAgility()==(agiAfterPlate-2));
		assertTrue(warriorPlayer.getStamina()==(staAfterPlate-3));
		assertTrue(warriorPlayer.getAttackPower()==(dmgAfterPlate));
		assertTrue(warriorPlayer.getMaxHealthPoint()==(maxHpAfterPlate-30));
		assertTrue(warriorPlayer.getHealthPoint()==(hpAfterPlate-30));
		assertTrue(warriorPlayer.getInventoryCount(bpt)==1); 
	}
	@ParameterizedTest
	@ValueSource(ints = {9, 6, 13, 28, 39, 21})
	void testIfTryingToUnEquipNonWornPieceChangesNothing(int value) {
		Gear bpt = new BreastplateOfTesting();
		Gear bou = new BucklerOfUselessness();
		warriorPlayer.addToInventory(bpt);
		warriorPlayer.addToInventory(bou);
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		try {
			warriorPlayer.equipArmor((Equipment) bpt);
		} catch (Exception e) {
			fail();
		}
		int intAfterPlate = warriorPlayer.getIntelligence();
		int strAfterPlate = warriorPlayer.getStrength();
		int agiAfterPlate = warriorPlayer.getAgility();
		int staAfterPlate = warriorPlayer.getStamina();
		int dmgAfterPlate = warriorPlayer.getAttackPower();
		int maxHpAfterPlate = warriorPlayer.getMaxHealthPoint();
		int hpAfterPlate = warriorPlayer.getHealthPoint();
		warriorPlayer.unEquipGear(bou);
		assertTrue(warriorPlayer.getGearFromGear("chest").equals(bpt));
		assertTrue(warriorPlayer.getIntelligence()==(intAfterPlate));
		assertTrue(warriorPlayer.getStrength()==(strAfterPlate));
		assertTrue(warriorPlayer.getAgility()==(agiAfterPlate));
		assertTrue(warriorPlayer.getStamina()==(staAfterPlate));
		assertTrue(warriorPlayer.getAttackPower()==(dmgAfterPlate));
		assertTrue(warriorPlayer.getMaxHealthPoint()==(maxHpAfterPlate));
		assertTrue(warriorPlayer.getHealthPoint()==(hpAfterPlate));
		assertTrue(warriorPlayer.getInventoryCount(bou)==1); 
		assertFalse(warriorPlayer.isInInventory(bpt));
	}
	@ParameterizedTest
	@ValueSource(ints = {34, 39, 36, 37, 38, 60}) 
	void testToMakeSureMageCannotEquipTwoHandedSwords(int value) {
		Gear hb = new Heartsbane();
		magePlayer.addToInventory(hb);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		assertThrows(IllegalArgumentException.class, () -> {
			magePlayer.equipWeapon((Weapon) hb);;
		});
		assertTrue(magePlayer.getGearFromGear("weapon")==null);
	}
	@ParameterizedTest
	@ValueSource(ints = {34, 39, 36, 37, 38, 60})
	void testIfWarriorCanEquipTwoHandedSwordsAndAttributesAreUpdated(int value) {
		Gear hb = new Heartsbane();
		warriorPlayer.addToInventory(hb);
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		int intBeforeHB = warriorPlayer.getIntelligence();
		int strBeforeHB = warriorPlayer.getStrength();
		int agiBeforeHB = warriorPlayer.getAgility();
		int staBeforeHB = warriorPlayer.getStamina();
		int dmgBeforeHB = warriorPlayer.getAttackPower();
		int maxHpBeforeHB = warriorPlayer.getMaxHealthPoint();
		int hpBeforeHB = warriorPlayer.getHealthPoint();
		try {
			warriorPlayer.equipWeapon((Weapon) hb);
		} catch (Exception e) {
			fail();
		}
		assertTrue(warriorPlayer.getGearFromGear("weapon").equals(hb));
		assertTrue(warriorPlayer.getIntelligence()==(intBeforeHB));
		assertTrue(warriorPlayer.getStrength()==(strBeforeHB+46));
		assertTrue(warriorPlayer.getAgility()==(agiBeforeHB+28));
		assertTrue(warriorPlayer.getStamina()==(staBeforeHB+18));
		assertTrue(warriorPlayer.getAttackPower()==(dmgBeforeHB+189+(5*46)));
		assertTrue(warriorPlayer.getMaxHealthPoint()==(maxHpBeforeHB+180));
		assertTrue(warriorPlayer.getHealthPoint()==(hpBeforeHB+180));
		assertFalse(warriorPlayer.isInInventory(hb));
	}
	@Test
	void testToMakeSureEquippingANewWeaponRemovesOldWeaponAndUpdatesAttributes() {
		Gear ww = new WidowsWail();
		Gear hb = new Heartsbane();
		warriorPlayer.addToInventory(ww);
		warriorPlayer.addToInventory(hb);
		for(int i=0;i<54;i++) {warriorPlayer.increaseLevel();}
		int intBeforeWW = warriorPlayer.getIntelligence();
		int strBeforeWW = warriorPlayer.getStrength();
		int agiBeforeWW = warriorPlayer.getAgility();
		int staBeforeWW = warriorPlayer.getStamina();
		int dmgBeforeWW = warriorPlayer.getAttackPower();
		int maxHpBeforeWW = warriorPlayer.getMaxHealthPoint();
		int hpBeforeWW = warriorPlayer.getHealthPoint();
		try {
			warriorPlayer.equipWeapon((Weapon) ww);
		} catch (Exception e) {
			fail();
		}
		int intAfterWW = warriorPlayer.getIntelligence();
		int strAfterWW = warriorPlayer.getStrength();
		int agiAfterWW = warriorPlayer.getAgility();
		int staAfterWW = warriorPlayer.getStamina();
		int dmgAfterWW = warriorPlayer.getAttackPower();
		int maxHpAfterWW = warriorPlayer.getMaxHealthPoint();
		int hpAfterWW = warriorPlayer.getHealthPoint();
		try {
			warriorPlayer.equipWeapon((Weapon) hb);
		} catch (Exception e) {
			fail();
		}
		int intAfterHB = warriorPlayer.getIntelligence();
		int strAfterHB = warriorPlayer.getStrength();
		int agiAfterHB = warriorPlayer.getAgility();
		int staAfterHB = warriorPlayer.getStamina();
		int dmgAfterHB = warriorPlayer.getAttackPower();
		int maxHpAfterHB = warriorPlayer.getMaxHealthPoint();
		int hpAfterHB = warriorPlayer.getHealthPoint();
		assertTrue(warriorPlayer.getGearFromGear("weapon").equals(hb));
		assertFalse(warriorPlayer.isInInventory(hb));
		assertTrue(warriorPlayer.isInInventory(ww));
		assertEquals(intBeforeWW,intAfterWW);
		assertEquals(strBeforeWW+17,strAfterWW);
		assertEquals(agiBeforeWW+14,agiAfterWW);
		assertEquals(staBeforeWW+10,staAfterWW);
		assertEquals(dmgBeforeWW+(87+5*17),dmgAfterWW);
		assertEquals(maxHpBeforeWW+100,maxHpAfterWW);
		assertEquals(hpBeforeWW+100,hpAfterWW);
		assertEquals(intAfterWW,intAfterHB);
		assertEquals(strAfterWW+(46-17),strAfterHB);
		assertEquals(agiAfterWW+(28-14),agiAfterHB);
		assertEquals(staAfterWW+(18-10),staAfterHB);
		assertEquals(dmgAfterWW+247,dmgAfterHB);
		assertEquals(maxHpAfterWW+80,maxHpAfterHB);
		assertEquals(hpAfterWW+80,hpAfterHB);
	}
	@Test
	void testToMakeSureWarriorCanEquipMultipleThings() {
		Gear ww = new WidowsWail();
		Gear bpt = new BreastplateOfTesting();
		Gear bou = new BucklerOfUselessness();
		warriorPlayer.addToInventory(ww);
		warriorPlayer.addToInventory(bpt);
		warriorPlayer.addToInventory(bou);
		for(int i=0;i<30;i++) {warriorPlayer.increaseLevel();}
		int intBeforeEquip = warriorPlayer.getIntelligence();
		int strBeforeEquip = warriorPlayer.getStrength();
		int agiBeforeEquip = warriorPlayer.getAgility();
		int staBeforeEquip = warriorPlayer.getStamina();
		int dmgBeforeEquip = warriorPlayer.getAttackPower();
		int armorBeforeEquip = warriorPlayer.getArmor();
		int maxHpBeforeEquip = warriorPlayer.getMaxHealthPoint();
		int hpBeforeEquip = warriorPlayer.getHealthPoint();
		try {
			warriorPlayer.equipWeapon((Weapon) ww);
			warriorPlayer.equipArmor((Equipment) bpt);
			warriorPlayer.equipArmor((Equipment) bou);
		} catch (Exception e) {
			fail();
		}
		int intAfterEquip = warriorPlayer.getIntelligence();
		int strAfterEquip = warriorPlayer.getStrength();
		int agiAfterEquip = warriorPlayer.getAgility();
		int staAfterEquip = warriorPlayer.getStamina();
		int dmgAfterEquip = warriorPlayer.getAttackPower();
		int armorAfterEquip = warriorPlayer.getArmor();
		int maxHpAfterEquip = warriorPlayer.getMaxHealthPoint();
		int hpAfterEquip = warriorPlayer.getHealthPoint();
		assertTrue(warriorPlayer.getGearFromGear("weapon").equals(ww));
		assertTrue(warriorPlayer.getGearFromGear("chest").equals(bpt));
		assertTrue(warriorPlayer.getGearFromGear("shield").equals(bou));
		assertFalse(warriorPlayer.isInInventory(ww));
		assertFalse(warriorPlayer.isInInventory(bpt));
		assertFalse(warriorPlayer.isInInventory(bou));
		assertEquals(intBeforeEquip+5,intAfterEquip);
		assertEquals(strBeforeEquip+17,strAfterEquip);
		assertEquals(agiBeforeEquip+16,agiAfterEquip);
		assertEquals(staBeforeEquip+13,staAfterEquip);
		assertEquals(dmgBeforeEquip+(87+5*17),dmgAfterEquip);
		assertEquals(armorBeforeEquip+160,armorAfterEquip);
		assertEquals(maxHpBeforeEquip+130,maxHpAfterEquip);
		assertEquals(hpBeforeEquip+130,hpAfterEquip);
	}
	@Test
	void testToMakeSureEquippingTwoHandedRemovesShield() {
		Gear ww = new WidowsWail();
		Gear hb = new Heartsbane();
		Gear bou = new BucklerOfUselessness();
		warriorPlayer.addToInventory(ww);
		warriorPlayer.addToInventory(hb);
		warriorPlayer.addToInventory(bou);
		for(int i=0;i<57;i++) {warriorPlayer.increaseLevel();}
		try {
			warriorPlayer.equipWeapon((Weapon) ww);
			warriorPlayer.equipArmor((Equipment) bou);
		} catch (Exception e) {
			fail();
		}
		int intBeforeHB = warriorPlayer.getIntelligence();
		int strBeforeHB = warriorPlayer.getStrength();
		int agiBeforeHB = warriorPlayer.getAgility();
		int staBeforeHB = warriorPlayer.getStamina();
		int dmgBeforeHB = warriorPlayer.getAttackPower();
		int armorBeforeHB = warriorPlayer.getArmor();
		int maxHpBeforeHB = warriorPlayer.getMaxHealthPoint();
		int hpBeforeHB = warriorPlayer.getHealthPoint();
		try {
			warriorPlayer.equipWeapon((Weapon) hb);
		} catch (Exception e) {
			fail();
		}
		int intAfterHB = warriorPlayer.getIntelligence();
		int strAfterHB = warriorPlayer.getStrength();
		int agiAfterHB = warriorPlayer.getAgility();
		int staAfterHB = warriorPlayer.getStamina();
		int dmgAfterHB = warriorPlayer.getAttackPower();
		int armorAfterHB = warriorPlayer.getArmor();
		int maxHpAfterHB = warriorPlayer.getMaxHealthPoint();
		int hpAfterHB = warriorPlayer.getHealthPoint();
		assertTrue(warriorPlayer.getGearFromGear("weapon").equals(hb));
		assertFalse(warriorPlayer.getGearFromGear("weapon").equals(ww));
		assertTrue(warriorPlayer.getGearFromGear("shield")==null);
		assertFalse(warriorPlayer.isInInventory(hb));
		assertTrue(warriorPlayer.isInInventory(ww));
		assertTrue(warriorPlayer.isInInventory(bou));
		assertEquals(intBeforeHB,intAfterHB);
		assertEquals(strBeforeHB+(46-17),strAfterHB);
		assertEquals(agiBeforeHB+(28-14),agiAfterHB);
		assertEquals(staBeforeHB+(18-10),staAfterHB);
		assertEquals(dmgBeforeHB+(247),dmgAfterHB);
		assertEquals(armorBeforeHB-10,armorAfterHB);
		assertEquals(maxHpBeforeHB+80,maxHpAfterHB);
		assertEquals(hpBeforeHB+80,hpAfterHB);
	}
	@Test
	void testGetGearFromGearMethodWithoutGear() {
		assertTrue(warriorPlayer.getGearFromGear("chest")==null);
		assertTrue(warriorPlayer.getGearFromGear("shield")==null);
		assertTrue(warriorPlayer.getGearFromGear("weapon")==null);
	}
	@Test
	void testAFewSimpleGetters() {
		assertEquals(0,magePlayer.getExperiencePoint());
		assertEquals("Human",magePlayer.getRace());
		assertEquals("Mage",magePlayer.getPlayerClass());
	}
	@ParameterizedTest
	@ValueSource(ints = {1, 5, 14, 32, 27, 0})
	void testToMakeSureIncreaseLevelMethodUpdatesAttributesCorrectlyForBothWarriorAndMage(int value) {
		int intBeforeLevelUpWarr = warriorPlayer.getIntelligence();
		int strBeforeLevelUpWarr = warriorPlayer.getStrength();
		int agiBeforeLevelUpWarr = warriorPlayer.getAgility();
		int staBeforeLevelUpWarr = warriorPlayer.getStamina();
		int dmgBeforeLevelUpWarr = warriorPlayer.getAttackPower();
		int maxHpBeforeLevelUpWarr = warriorPlayer.getMaxHealthPoint();
		int hpBeforeLevelUpWarr = warriorPlayer.getHealthPoint();
		int intBeforeLevelUpMage = magePlayer.getIntelligence();
		int strBeforeLevelUpMage = magePlayer.getStrength();
		int agiBeforeLevelUpMage = magePlayer.getAgility();
		int staBeforeLevelUpMage = magePlayer.getStamina();
		int dmgBeforeLevelUpMage = magePlayer.getAttackPower();
		int maxHpBeforeLevelUpMage = magePlayer.getMaxHealthPoint();
		int hpBeforeLevelUpMage = magePlayer.getHealthPoint();
		for(int i=0;i<value;i++) {warriorPlayer.increaseLevel();}
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		int intAfterLevelUpWarr = warriorPlayer.getIntelligence();
		int strAfterLevelUpWarr = warriorPlayer.getStrength();
		int agiAfterLevelUpWarr = warriorPlayer.getAgility();
		int staAfterLevelUpWarr = warriorPlayer.getStamina();
		int dmgAfterLevelUpWarr = warriorPlayer.getAttackPower();
		int maxHpAfterLevelUpWarr = warriorPlayer.getMaxHealthPoint();
		int hpAfterLevelUpWarr = warriorPlayer.getHealthPoint();
		int intAfterLevelUpMage = magePlayer.getIntelligence();
		int strAfterLevelUpMage = magePlayer.getStrength();
		int agiAfterLevelUpMage = magePlayer.getAgility();
		int staAfterLevelUpMage = magePlayer.getStamina();
		int dmgAfterLevelUpMage = magePlayer.getAttackPower();
		int maxHpAfterLevelUpMage = magePlayer.getMaxHealthPoint();
		int hpAfterLevelUpMage = magePlayer.getHealthPoint();
		assertEquals(intBeforeLevelUpWarr+value,intAfterLevelUpWarr);
		assertEquals(strBeforeLevelUpWarr+(value*2),strAfterLevelUpWarr);
		assertEquals(agiBeforeLevelUpWarr+value,agiAfterLevelUpWarr);
		assertEquals(staBeforeLevelUpWarr+(value*2),staAfterLevelUpWarr);
		assertEquals(dmgBeforeLevelUpWarr+(value*10),dmgAfterLevelUpWarr);
		assertEquals(maxHpBeforeLevelUpWarr+(value*20),maxHpAfterLevelUpWarr);
		assertEquals(hpBeforeLevelUpWarr+(value*20),hpAfterLevelUpWarr);
		assertEquals(intBeforeLevelUpMage+(value*2),intAfterLevelUpMage);
		assertEquals(strBeforeLevelUpMage+(value),strAfterLevelUpMage);
		assertEquals(agiBeforeLevelUpMage+value,agiAfterLevelUpMage);
		assertEquals(staBeforeLevelUpMage+(value),staAfterLevelUpMage);
		assertEquals(dmgBeforeLevelUpMage+(value*5),dmgAfterLevelUpMage);
		assertEquals(maxHpBeforeLevelUpMage+(value*10),maxHpAfterLevelUpMage);
		assertEquals(hpBeforeLevelUpMage+(value*10),hpAfterLevelUpMage);
	}
	@ParameterizedTest
	@ValueSource(ints = {1000, 100, 500, 250, 3678, 798, 500070023, 500070022, 0})
	void testToMakeCertainincreaseExperienceWorksAsIntended(int value) {
		magePlayer.increaseExperiencePoint(value);
		boolean loop = true;
		int i = 0;
		int j = 100;
		if(value>=j) {
			do {
				i+=1;
				j=j+(j/2);
				if(j>value) {loop = false;}
			}while(loop);
		}
		assertEquals(i+1,magePlayer.getLevel());
		assertEquals(value,magePlayer.getExperiencePoint());
		assertTrue(magePlayer.getNextLevelCap() > magePlayer.getExperiencePoint());
	}
	@ParameterizedTest
	@ValueSource(ints = {970070024, 556070024, 500074024, 900070024, Integer.MAX_VALUE, 501070024, 500070024, 500076022, 600070024})
	void testToMakeSureExperienceMaxAndLevelMaxWork(int value) {
		magePlayer.increaseExperiencePoint(value);
		warriorPlayer.setExperiencePoint(value);
		assertEquals(40,magePlayer.getLevel());
		assertEquals(40,warriorPlayer.getLevel());
		assertTrue(value > magePlayer.getExperiencePoint());
		assertTrue(value > warriorPlayer.getExperiencePoint());
	}
	@ParameterizedTest
	@ValueSource(ints = {50001, 50004, 64147, Integer.MAX_VALUE, 984324, 346635, 79321, 55555})
	void testToMakeSureArmorMaxWorksAsIntended(int value) {
		Gear testGear = new BreastplateOfTesting(value,0,0,0,0,0);
		Gear bou = new BucklerOfUselessness();
		warriorPlayer.addToInventory(testGear);
		warriorPlayer.addToInventory(bou);
		int armorBefore = warriorPlayer.getArmor();
		for(int i=0;i<10;i++) {warriorPlayer.increaseLevel();}
		try {
			warriorPlayer.equipArmor((Equipment) bou);
			warriorPlayer.equipArmor((Equipment) testGear);
		} catch (Exception e) {
			fail();
		}
		int armorAfter = warriorPlayer.getArmor();
		assertTrue(value > armorAfter);
		assertEquals(50000, armorAfter);
		assertTrue(armorAfter > armorBefore);
	}
	@ParameterizedTest
	@ValueSource(ints = {50000, 0, 8539, 286, 98, 999, 39485, 5555})
	void testToMakeSureArmorAddsUpCorrectlyWithoutGoingOverMax(int value) {
		Gear testGear = new BreastplateOfTesting(value,0,0,0,0,0);
		warriorPlayer.addToInventory(testGear);
		int armorBefore = warriorPlayer.getArmor();
		for(int i=0;i<10;i++) {warriorPlayer.increaseLevel();}
		try {
			warriorPlayer.equipArmor((Equipment) testGear);
		} catch (Exception e) {
			fail();
		}
		int armorAfter = warriorPlayer.getArmor();
		assertTrue(value == armorAfter);
		assertTrue(armorAfter >= armorBefore);
		assertTrue(50000 >= armorAfter);
	}
	@ParameterizedTest
	@ValueSource(ints = {5001, 5004, 6147, Integer.MAX_VALUE, 98424, 34635, 7931, 5555})
	void testToMakeSureAttributesMaxWorksAsIntended(int value) {
		Gear strGear = new BreastplateOfTesting(0,value,0,0,0,0);
		Gear agiGear = new BreastplateOfTesting(0,0,value,0,0,0);
		Gear intellGear = new BreastplateOfTesting(0,0,0,value,0,0);
		Gear staGear = new BreastplateOfTesting(0,0,0,0,value,0);
		warriorPlayer.addToInventory(strGear);
		warriorPlayer.addToInventory(agiGear);
		warriorPlayer.addToInventory(intellGear);
		warriorPlayer.addToInventory(staGear);
		int strBefore = warriorPlayer.getStrength();
		int agiBefore = warriorPlayer.getAgility();
		int intellBefore = warriorPlayer.getIntelligence();
		int staBefore = warriorPlayer.getStamina();
		for(int i=0;i<10;i++) {warriorPlayer.increaseLevel();}
		try {
			warriorPlayer.equipArmor((Equipment) strGear);
		} catch (Exception e) {
			fail();
		}
		int strAfter = warriorPlayer.getStrength();
		try {
			warriorPlayer.equipArmor((Equipment) agiGear);
		} catch (Exception e) {
			fail();
		}
		int agiAfter = warriorPlayer.getAgility();
		try {
			warriorPlayer.equipArmor((Equipment) intellGear);
		} catch (Exception e) {
			fail();
		}
		int intellAfter = warriorPlayer.getIntelligence();
		try {
			warriorPlayer.equipArmor((Equipment) staGear);
		} catch (Exception e) {
			fail();
		}
		int staAfter = warriorPlayer.getStamina();
		assertTrue(value > strAfter);
		assertTrue(value > agiAfter);
		assertTrue(value > intellAfter);
		assertTrue(value > staAfter);
		assertEquals(5000, strAfter);
		assertEquals(5000, agiAfter);
		assertEquals(5000, intellAfter);
		assertEquals(5000, staAfter);
		assertTrue(strAfter > strBefore);
		assertTrue(agiAfter > agiBefore);
		assertTrue(intellAfter > intellBefore);
		assertTrue(staAfter > staBefore);
	} 
	@Test
	void testToMakeSureCupboardExists() {
		assertTrue(magePlayer.getCupboard() instanceof Cupboard);
	}
	@Test
	void testToMakeSureRemovingAnItemWorks() {
		Gear ww = new WidowsWail();
		magePlayer.addToInventory(ww);
		int countBeforeRemoval = magePlayer.getInventoryCount(ww);
		magePlayer.removeFromInventory(ww);
		assertFalse(magePlayer.isInInventory(ww));
		assertEquals(1,countBeforeRemoval);
	}
	@Test
	void testToMakeSureEqualsWorksAsIntendedWithTwoPlayersUnequalInRegardsToClass() {
		assertFalse(magePlayer.equals(warriorPlayer));
	}
	@Test
	void testToMakeSureEqualsWorksAsIntendedWithTwoEqualPlayers() {
		Player magePlayer2 = new Player("Mage","Human",100,0);
		assertTrue(magePlayer.equals(magePlayer2));
	}
	@Test
	void testToMakeSureEqualsWorksAsIntendedWithTwoPlayersUnequalInRegardsToRace() {
		Player magePlayer2 = new Player("Mage","Elf",100,0);
		assertFalse(magePlayer.equals(magePlayer2));
	}
	@Test
	void testToMakeSurePlayerEqualsItself() {
		assertTrue(magePlayer.equals(magePlayer));
	}
	@Test
	void testToMakeSurePlayerIsUnequalToNonPlayerObjects() {
		Gear bpt = new BreastplateOfTesting();
		assertFalse(magePlayer.equals(bpt));
	}
}
