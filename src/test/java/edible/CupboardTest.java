package edible;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import magic.MagicPlayer;
import player.Player;
import quest.TalkToGuildLeader;

class CupboardTest {

	final static MagicPlayer MAGIC_PLAYER = new MagicPlayer("Vamp Witch", "Very badass witch from Rumania", 500, 100);
	final static Cupboard CUPBOARD = new Cupboard(MAGIC_PLAYER); 
	final static Edible FLY_AGARIC = new Edible("Fly Agaric", "Poisonous mushroom with magic powers", 4, -3, 0);
	final static ForbiddenFruit LUCKY_CHERRY = new ForbiddenFruit("Lucky Cherry", "Eating cherry starts quest Talk to Guild leader", new TalkToGuildLeader("Talk to Guild leader", "Talk", "in progress", true, true));
	
	final static Ingredient FIRE_ROOT = new Ingredient("Fire Root", "Increases power of potions");
	final static Ingredient CLAW_OF_HIPOGRIFF = new Ingredient("Claw of Hipogriff", "Rare poisonous ingredient");
	final static Ingredient MORNING_STAR = new Ingredient("Morning Star", "Flower used in potions effecting health");
		
	final static Potion GREEN_VENOM = new Potion("Green Venom", "Sabbotages health on entering bloodstream", 0, -10, 0);
	final static Potion LOVE_BREW = new Potion("Love Potion", "Boosts magic power and boosts health", 5, 2, 0);
	final static Potion HEARTBREAK_POTION = new Potion("Heartbreak Potion", "Weakens magic powers, sabbotages health and gives life experience", -3, -10, 6);
	final static Potion POWER_POTION = new Potion("Power Potion", "Boosts mana, health and experience", 5, 5, 5);
	
	final static Ingredient[] GREEN_VENOM_INGREDIENTS = {FIRE_ROOT, CLAW_OF_HIPOGRIFF, MORNING_STAR};
	
	final static Recipie GREEN_VENOM_RECIPIE = new Recipie("Green Venom Recipie", 
			"Recipie for potion sabbotaging health on entering bloodstream", GREEN_VENOM, GREEN_VENOM_INGREDIENTS, 50, 20);
	
	//final static Edible ONE_UP_CEREAL = new Edible("1-Up Cereal", "1-Up", 0, 10, 0);
	final static Edible FORTUNE_COOKIE = new Edible("Fortune Cookie", "Hides a wisdom", 0, 0, 1);
	
	@BeforeAll
	static void setCupboardAttributeOfMagicPlayer() throws Exception {
		MAGIC_PLAYER.setCupboard(CUPBOARD);
	}
	
	@BeforeAll
	static void addItemToAllInventoryObjectsInCupboard() {
		CUPBOARD.store(FORTUNE_COOKIE);
	}
	
	@Test
	void constructorSetsAttributePlayer() {
//		Player player = new MagicPlayer(MAGIC_PLAYER.getPlayerClass(), MAGIC_PLAYER.getRace(), 
//				MAGIC_PLAYER.getHealthPoint(), MAGIC_PLAYER.getExperiencePoint());
		assertEquals(MAGIC_PLAYER, CUPBOARD.getPlayer());
	}
	
	@Test
	void storeAllocatesItemInCorrectInventory() {
		fail("Not yet implemented"); 
	}
	
	@Test
	void storeSameTypeRecipieTwiceThrowsISE() {
		fail("Not yet implemented");
	}
	
	@Test
	void storeSameTypeForbiddenFruitTwiceThrowsISE() {
		fail("Not yet implemented");
	}
	
	@Test
	void consumeAddsEdiblePointsToPlayerPoints() {
		fail("Not yet implemented");
	}
	
	@Test
	void consumePoisonedForbiddenFruitAddsPotionPointsToPlayerPoints() {
		fail("Not yet implemented");
	}
	
	@Test
	void consumeForbiddenFruitChangesQuestStatusToInProgress() {
		fail("Not yet implemented");
	}
	
	@Test
	void poisonStoresPoisonedFruitandUsesUpPotion() {
		fail("Not yet implemented");
	}
	
	@Test
	void cookRestoresRecipieAndStoresNewPotion() {
		fail("Not yet implemented");
	}
	
	@Test
	void cookIngredientsFromRecipieNotInCupboardThrowsNPE() {
		fail("Not yet implemented");
	}
	
	@Test
	void cookNotMagicPlayerThrowsISE() {
		Player player = new Player("Gnome", "Good companion appreciated by other players", 20, 0);
		player.setCupboard(new Cupboard(player));
		assertThrows(IllegalCallerException.class, () -> player.getCupboard().cook(GREEN_VENOM_RECIPIE));
	}
	
	@Test
	void toStringFormatsAttributesCorrect() {
		fail("Not yet implemented");
	}

}
