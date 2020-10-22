package player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import equipment.*;

class playerTestsWithRegardsToGear {
	Player magePlayer = new Player("Mage","Human",100,0);
	
	@BeforeEach
	void resetPlayer() {
		magePlayer = new Player("Mage","Human",100,0);
	}
	@Test
	void testIfMageCanEquipRobes() throws Exception {
		Gear roim = new RobesOfImmenseMagic();
		magePlayer.addToInventory(roim);
		for(int i=0;i<50;i++) {magePlayer.increaseLevel();}
		magePlayer.equipArmor((Equipment) roim);
		assertTrue(magePlayer.getGearFromGear("chest").equals(roim));
	}
	@Test
	void testIfLevelCriteriaForRobesWork() throws Exception {
		Gear roim = new RobesOfImmenseMagic();
		magePlayer.addToInventory(roim);
		for(int i=0;i<45;i++) {magePlayer.increaseLevel();}
		assertThrows(Exception.class, () -> {
			magePlayer.equipArmor((Equipment) roim);;
		});
	}
}
