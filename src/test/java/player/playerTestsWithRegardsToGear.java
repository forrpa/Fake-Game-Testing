package player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import equipment.*;

class playerTestsWithRegardsToGear {
	Player magePlayer = new Player("Mage","Human",100,0);
	
	@BeforeEach
	void resetPlayer() {
		magePlayer = new Player("Mage","Human",100,0);
	}
	@ParameterizedTest
	@ValueSource(ints = {49, 50, 100, 200, 140, 87})
	void testIfMageCanEquipRobes(int value){
		Gear roim = new RobesOfImmenseMagic();
		magePlayer.addToInventory(roim);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		int intellBeforeRobe = magePlayer.getIntelligence();
		try {
			magePlayer.equipArmor((Equipment) roim);
		} catch (Exception e) {
			fail();
		}
		assertTrue(magePlayer.getGearFromGear("chest").equals(roim));
		assertTrue(magePlayer.getIntelligence()==(intellBeforeRobe+100));
	}
	@ParameterizedTest
	@ValueSource(ints = {0, 5, 24, 37, 40, 48})
	void testIfLevelCriteriaForRobesWork(int value) {
		Gear roim = new RobesOfImmenseMagic();
		magePlayer.addToInventory(roim);
		for(int i=0;i<value;i++) {magePlayer.increaseLevel();}
		assertThrows(ArithmeticException.class, () -> {
			magePlayer.equipArmor((Equipment) roim);;
		});
		assertTrue(magePlayer.getGearFromGear("chest")==null);
	}
	@Test
	void testToMakeSureMageCannotWearPlate() {
		Gear bpt = new BreastplateOfTesting();
		magePlayer.addToInventory(bpt);
		for(int i=0;i<20;i++) {magePlayer.increaseLevel();}
		assertThrows(IllegalArgumentException.class, () -> {
			magePlayer.equipArmor((Equipment) bpt);;
		});
		assertTrue(magePlayer.getGearFromGear("chest")==null);
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
}
