package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import equipment.BucklerOfUselessness;
import magic.MagicPlayer;
import player.Player;
import quest.TalkToGuildLeader;

class CupboardTest {

	final static Player GNOME = new Player("Gnome", "Good companion appreciated by other players", 70, 25);
	final static MagicPlayer VAMP_WITCH = new MagicPlayer("Vamp Witch", "Very badass witch from Rumania", 500, 100);
	final static Cupboard CUPBOARD = new Cupboard(VAMP_WITCH); 
	final static Edible FORTUNE_COOKIE = new Edible("Fortune Cookie", "Hides a wisdom", 0, 0, 1);
	final static Edible FLY_AGARIC = new Edible("Fly Agaric", "Poisonous mushroom with magic powers", 4, -3, 0);	
	final static Potion LOVE_BREW = new Potion("Love Potion", "Boosts magic power and boosts health", 5, 2, 0);
	final static Potion POWER_POTION = new Potion("Power Potion", "Boosts mana, health and experience", 5, 5, 5);
	final static Potion GREEN_VENOM = new Potion("Green Venom", "Sabbotages health on entering bloodstream", 0, -10, 0);
	final static Ingredient FIRE_ROOT = new Ingredient("Fire Root", "Increases power of potions");
	final static Ingredient CLAW_OF_HIPOGRIFF = new Ingredient("Claw of Hipogriff", "Rare poisonous ingredient");
	final static Ingredient MORNING_STAR = new Ingredient("Morning Star", "Flower used in potions effecting health");
	final static Ingredient[] GREEN_VENOM_INGREDIENTS = {FIRE_ROOT, CLAW_OF_HIPOGRIFF, MORNING_STAR};
	final static Recipie GREEN_VENOM_RECIPIE = new Recipie("Green Venom Recipie", 
			"Recipie for potion sabbotaging health on entering bloodstream", GREEN_VENOM, GREEN_VENOM_INGREDIENTS, 50, 20);
	final static TalkToGuildLeader QUEST = new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true);
	final static ForbiddenFruit LUCKY_CHERRY = new ForbiddenFruit("Lucky Cherry", "Eating cherry starts quest Talk to Guild leader", QUEST);
	final static int FIVE_BEFOREALL_COUNT_FORTUNE_COOKIE = 5;
	static int COUNT_FORTUNE_COOKIE = 0;
	
	final Potion HEARTBREAK_POTION = new Potion("Heartbreak Potion", "Weakens magic powers, sabbotages health and gives life experience", -3, -10, 6);
	
	@BeforeAll
	static void setPlayerAttributeOfCupboard() throws Exception {
		CUPBOARD.setPlayer(VAMP_WITCH);
	}
	
	@BeforeAll
	static void setManaPointMagicPlayerEoughToCookGreenVenomRecipie() throws Exception {
		VAMP_WITCH.setManaPoint(50);
	}
	
	@BeforeAll
	static void addItemToAllInventoryObjectsInCupboard() {
		for(int i = 0; i < FIVE_BEFOREALL_COUNT_FORTUNE_COOKIE; i++) {
			CUPBOARD.store(FORTUNE_COOKIE);
			COUNT_FORTUNE_COOKIE++;
		}
		CUPBOARD.store(GREEN_VENOM_RECIPIE);
		CUPBOARD.store(LUCKY_CHERRY);
		CUPBOARD.store(FLY_AGARIC);
		CUPBOARD.store(LOVE_BREW);
	}
	
	@BeforeEach
	void restoreIngredientsForCookingGreenVenomRecipie() {
		for(Ingredient i : GREEN_VENOM_INGREDIENTS) {
			if(!CUPBOARD.isInInventory(i)) CUPBOARD.store(i);
		}
	}
	
	@Test
	void constructorSetsAttributePlayer() {
		assertEquals(VAMP_WITCH, CUPBOARD.getPlayer());
	}
	
	// getCount is already tested in InventoryTest to throw NullPointerException
	
	@Test
	void storeNotCupboardItemThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> CUPBOARD.store(new BucklerOfUselessness()));
	}
	
	@Test
	void storeSameTypeRecipieTwiceThrowsISE() {
		assertEquals(1, CUPBOARD.getCount(GREEN_VENOM_RECIPIE));
		assertThrows(IllegalStateException.class, () -> CUPBOARD.store(GREEN_VENOM_RECIPIE));
	}
	
	@Test
	void storeSameTypeForbiddenFruitTwiceThrowsISE() {
		assertEquals(1, CUPBOARD.getCount(LUCKY_CHERRY));
		assertThrows(IllegalStateException.class, () -> CUPBOARD.store(LUCKY_CHERRY));
	}
	
	@RepeatedTest(FIVE_BEFOREALL_COUNT_FORTUNE_COOKIE)
	void consumeRemovesOneOfItemTypeFromCupboard() {
		assertEquals(COUNT_FORTUNE_COOKIE, CUPBOARD.getCount(FORTUNE_COOKIE));
		CUPBOARD.consume(FORTUNE_COOKIE);
		COUNT_FORTUNE_COOKIE--;
	}

	@Test
	void consumeIfItemNotStoredThrowsNPE() {
		assertFalse(CUPBOARD.isInInventory(POWER_POTION));
		assertThrows(NullPointerException.class, () -> CUPBOARD.consume(POWER_POTION));
	}
	
	@Test
	void consumeAddsEdibleHealthAndExperiencePointToPlayerHealthAndExperiencePoint() {
		int healthPointPlayer = GNOME.getHealthPoint();
		int experiencePointPlayer = GNOME.getExperiencePoint();
		Cupboard cupboard = GNOME.getCupboard();
		Edible[] ediblesOneFromEachClass = {FLY_AGARIC, LOVE_BREW, LUCKY_CHERRY};
		for(Edible e : ediblesOneFromEachClass) {
			cupboard.store(e);
			healthPointPlayer = GNOME.getHealthPoint() + e.getHealthPoint();
			experiencePointPlayer = GNOME.getExperiencePoint() + e.getExperiencePoint();
			cupboard.consume(e);
			assertEquals(healthPointPlayer, GNOME.getHealthPoint());
			assertEquals(experiencePointPlayer, GNOME.getExperiencePoint());
		}
	}
	
	@Test
	void consumeAddsEdibleManaPointToPlayerManaPoint() {
		int manaPointMagicPlayer = VAMP_WITCH.getManaPoint();
		Edible[] ediblesOneFromEachClass = {FLY_AGARIC, LOVE_BREW, LUCKY_CHERRY};
		for(Edible e : ediblesOneFromEachClass) {
			manaPointMagicPlayer = VAMP_WITCH.getManaPoint() + e.getManaPoint();
			CUPBOARD.consume(e);
			assertEquals(manaPointMagicPlayer, VAMP_WITCH.getManaPoint());
		}
	}
//	
//	@Test
//	void consumeForbiddenFruitChangesQuestStatusToInProgress() {
//		fail("Not yet implemented");
//	}

	@Test
	void poisonForbiddenFruitNotStoredThrowsNPE() {
		
	}
//	@Test
//	void poisonStoresPoisonedFruit() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void haveAllIngredientsChecksInventory() {
		CUPBOARD.cook(GREEN_VENOM_RECIPIE);
		CUPBOARD.store(FIRE_ROOT);
		CUPBOARD.store(CLAW_OF_HIPOGRIFF);
		assertFalse(CUPBOARD.haveAllIngredients(GREEN_VENOM_RECIPIE));
		CUPBOARD.store(MORNING_STAR);
		assertTrue(CUPBOARD.haveAllIngredients(GREEN_VENOM_RECIPIE));
	}
	
	@Test
	void cookRemovesOneOfEachTypeOfIngredientFromCupboard() {
//		for(Ingredient i : GREEN_VENOM_INGREDIENTS) {
//			assertEquals(1, CUPBOARD.getCount(i));
//		}
//		CUPBOARD.cook(GREEN_VENOM_RECIPIE);
//		assertTrue(CUPBOARD.haveAllIngredients(GREEN_VENOM_RECIPIE));
//		CUPBOARD.cook(GREEN_VENOM_RECIPIE);
//		assertFalse(CUPBOARD.haveAllIngredients(GREEN_VENOM_RECIPIE));
	}
	
	@Test
	void cookRestoresRecipieForMagicPlayer() {
		assertEquals(1, CUPBOARD.getCount(GREEN_VENOM_RECIPIE));
		CUPBOARD.cook(GREEN_VENOM_RECIPIE);
		assertEquals(1, CUPBOARD.getCount(GREEN_VENOM_RECIPIE));
	}
	
	@Test
	void cookStoresNewPotionForMagicPlayer() {
		assertFalse(CUPBOARD.isInInventory(GREEN_VENOM));
		CUPBOARD.cook(GREEN_VENOM_RECIPIE);
		assertEquals(1, CUPBOARD.getCount(GREEN_VENOM));
	}
	
	@Test
	void cookNotMagicPlayerThrowsISE() {
		assertThrows(IllegalCallerException.class, () -> GNOME.getCupboard().cook(GREEN_VENOM_RECIPIE));
	}
	
//	@Test
//	void toStringFormatsAttributesCorrect() {
//		fail("Not yet implemented");
//	}

}
