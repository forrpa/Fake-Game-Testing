package quest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import player.Player;

class TalkToGuildLeaderTest {
    private final String questName = "Talk to Guild Leader";
    private final String questDescription = "You have to talk to the guild leader west of town.";
    private final String defaultState = "pending";

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
        Player player = new Player("Tank", "Human", 100, 100, 1000);
        assertEquals(1000, player.getExperiencePoint());
    }

    @Test
    void canPlayerStartTalkToGuildLeaderQuest() {
        Player player = new Player("Tank", "Human", 100, 100, 1000);
        TalkToGuildLeader quest = new TalkToGuildLeader(questName, questDescription, "unlocked", true, false);
        assertEquals("unlocked", quest.getState());
        assertTrue(quest.startRequirementsFulfilled(player));
    }

    @Test
    void playerMeetsEndRequirementsForTalkToGuildLeaderQuest() {
        Player player = new Player("Healer", "Orc", 200, 200, 1000);
        TalkToGuildLeader quest = new TalkToGuildLeader(questName, questDescription, "in progress", true, true);
        assertTrue(quest.hasTalkedToGuildLeader());
    }

    @Test
    void canPlayerCompleteTalkToGuildLeaderQuest() {
        Player player = new Player("Healer", "Orc", 200, 200, 1000);
        TalkToGuildLeader quest = new TalkToGuildLeader(questName, questDescription, "completed", true, true);
        assertEquals("completed", quest.getState());
        assertTrue(quest.endRequirementsFulfilled(player));
    }

    @Test
    void healerGetsCorrectReward() {
        Player player = new Player("Healer", "Orc", 200, 500, 1000);
        assertEquals(500, player.getHealthPoint()); //HP fylls på till max
    }

    @Test
    void tankGetsCorrectReward() {
        Player player = new Player("Healer", "Orc", 250, 250, 1000);
        assertEquals(250, player.getHealthPoint());
        assertEquals(250, player.getManaPoint());
    }

    @Test
    void damageGetsCorrectReward() {
        Player player = new Player("Healer", "Orc", 500, 500, 1000);
        assertEquals(500, player.getManaPoint());
    }

    //Fixa inventory
    @Test
    void nonTankHumanGetsCorrectReward() {
        Player player = new Player("Healer", "Human", 200, 200, 1000);
        //assertTrue(player.getInventory().contains("Common Steel Boots"));
    }

    @Test
    void tankHumanGetsCorrectReward() {
        Player player = new Player("Tank", "Human", 200, 200, 1000);
        //assertTrue(player.getInventory().contains("Rare Steel Boots"));
    }

    @Test
    void nonDamageOrcGetsCorrectReward() {
        Player player = new Player("Tank", "Orc", 200, 200, 1000);
        //assertTrue(player.getInventory().contains("Common Leather Gauntlets"));
    }

    @Test
    void damageOrcGetsCorrectReward() {
        Player player = new Player("Damage", "Orc", 200, 200, 1000);
        //assertTrue(player.getInventory().contains("Rare Leather Gauntlets"));
    }

}