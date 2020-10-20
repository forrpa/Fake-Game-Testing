package quest;

import static org.junit.jupiter.api.Assertions.*;

import edible.Potion;
import org.junit.jupiter.api.Test;

import player.Player;
import weapon.WidowsWail;

class TalkToGuildLeaderTest {
    private final String questName = "Talk to Guild Leader";
    private final String questDescription = "You have to talk to the guild leader west of town.";
    private final String defaultState = "pending";

    Player player;
    Player standardPlayer = new Player("Tank", "Human", 100, 1000);

    @Test
    void constructorTest() {
        TalkToGuildLeader quest = new TalkToGuildLeader(questName, questDescription, defaultState, true, false);
        assertEquals(questName, quest.getName());
        assertEquals(questDescription, quest.getDescription());
        assertEquals(defaultState, quest.getState());
        assertTrue(quest.isMandatory());
        assertFalse(quest.hasTalkedToGuildLeader());
    }

    @Test
    void playerMeetsStartRequirementsForTalkToGuildLeaderQuest() {
        assertEquals(1000, standardPlayer.getExperiencePoint());
    }

    @Test
    void playerDoesNotMeetStartRequirementsForTalkToGuildLeaderQuest(){
        player = new Player("Tank", "Human", 100, 999);
        assertEquals(999, player.getExperiencePoint());
    }

    @Test
    void canPlayerStartTalkToGuildLeaderQuest() {
        TalkToGuildLeader quest = new TalkToGuildLeader(questName, questDescription, "unlocked", true, false);
        assertEquals("unlocked", quest.getState());
        assertTrue(quest.startRequirementsFulfilled(standardPlayer));
    }

    @Test
    void playerMeetsEndRequirementsForTalkToGuildLeaderQuest() {
        TalkToGuildLeader quest = new TalkToGuildLeader(questName, questDescription, "in progress", true, true);
        assertTrue(quest.hasTalkedToGuildLeader());
    }

    @Test
    void canPlayerCompleteTalkToGuildLeaderQuest() {
        TalkToGuildLeader quest = new TalkToGuildLeader(questName, questDescription, "completed", true, true);
        assertEquals("completed", quest.getState());
        assertTrue(quest.endRequirementsFulfilled(standardPlayer));
    }

    @Test
    void healerGetsCorrectReward() {
        player = new Player("Healer", "Orc", 200, 1500);
        HealingPotion healingPotion = new HealingPotion();
        player.addToInventory(healingPotion);
        GuildMap guildMap = new GuildMap();
        player.addToInventory(guildMap);
        assertEquals(1, player.getInventoryCount(healingPotion));
        assertEquals(1, player.getInventoryCount(guildMap));
        assertEquals(1500, player.getExperiencePoint());
    }

    @Test
    void damageGetsCorrectReward() {
        player = new Player("Damage", "Human", 200, 1500);
        CrystalChard crystalChard = new CrystalChard();
        player.addToInventory(crystalChard);
        GuildMap guildMap = new GuildMap();
        player.addToInventory(guildMap);
        assertEquals(1, player.getInventoryCount(guildMap));
        assertEquals(1, player.getInventoryCount(crystalChard));
        assertEquals(1500, player.getExperiencePoint());
    }

    @Test
    void tankGetsCorrectReward() {
        player = new Player("Tank", "Orc", 200, 1500);
        HealingPotionRecipe healingPotionRecipe = new HealingPotionRecipe();
        player.addToInventory(healingPotionRecipe);
        GuildMap guildMap = new GuildMap();
        player.addToInventory(guildMap);
        assertEquals(1, player.getInventoryCount(guildMap));
        assertEquals(1, player.getInventoryCount(healingPotionRecipe));
        assertEquals(1500, player.getExperiencePoint());
    }

    @Test
    void nonTankHumanGetsCorrectReward() {
        player = new Player("Healer", "Human", 200, 1500);
        CommonBreastPlate commonBreastPlate = new CommonBreastPlate();
        player.addToInventory(commonBreastPlate);
        GuildMap guildMap = new GuildMap();
        player.addToInventory(guildMap);
        assertEquals(1, player.getInventoryCount(guildMap));
        assertEquals(1, player.getInventoryCount(commonBreastPlate));
        assertEquals(1500, player.getExperiencePoint());
    }

    @Test
    void tankHumanGetsCorrectReward() {
        player = new Player("Tank", "Human", 200, 1500);
        RareBreastplate rareBreastplate = new RareBreastplate();
        player.addToInventory(rareBreastplate);
        GuildMap guildMap = new GuildMap();
        player.addToInventory(guildMap);
        assertEquals(1, player.getInventoryCount(guildMap));
        assertEquals(1, player.getInventoryCount(rareBreastplate));
        assertEquals(1500, player.getExperiencePoint());
    }

    @Test
    void nonDamageOrcGetsCorrectReward() {
        player = new Player("Tank", "Orc", 200, 1500);
        CommonSword commonSword = new CommonSword();
        player.addToInventory(commonSword);
        GuildMap guildMap = new GuildMap();
        player.addToInventory(guildMap);
        assertEquals(1, player.getInventoryCount(guildMap));
        assertEquals(1, player.getInventoryCount(commonSword));
        assertEquals(1500, player.getExperiencePoint());
    }

    @Test
    void damageOrcGetsCorrectReward() {
        player = new Player("Damage", "Orc", 200, 1500);
        WidowsWail widowsWail = new WidowsWail();
        player.addToInventory(widowsWail);
        GuildMap guildMap = new GuildMap();
        player.addToInventory(guildMap);
        assertEquals(1, player.getInventoryCount(guildMap));
        assertEquals(1, player.getInventoryCount(widowsWail));
        assertEquals(1500, player.getExperiencePoint());
    }

}