package quest;

import static org.junit.jupiter.api.Assertions.*;
import equipment.BreastplateOfTesting;
import equipment.BucklerOfUselessness;
import org.junit.jupiter.api.Test;

import player.Player;
import weapon.Heartsbane;
import weapon.WidowsWail;

class TalkToGuildLeaderTest {

    Player player;
    Player standardPlayer = new Player("Tank", "Human", 200, 1000);
    TalkToGuildLeader quest = new TalkToGuildLeader();


    @Test
    void constructorTest() {
        assertEquals("Talk to Guild Leader", quest.getName());
        assertEquals("You have to talk to the guild leader west of town.", quest.getDescription());
        assertEquals(QuestState.PENDING, quest.getState());
        assertTrue(quest.isMandatory());
    }

    @Test
    void playerMeetsStartRequirementsForTalkToGuildLeaderQuest() {
        quest.startRequirementsFulfilled(standardPlayer);
        assertEquals(QuestState.UNLOCKED, quest.getState());
    }

    @Test
    void playerDoesNotMeetStartRequirementsForTalkToGuildLeaderQuest() {
        player = new Player("Tank", "Human", 200, 999);
        assertFalse(quest.startRequirementsFulfilled(player));
    }

    @Test
    void playerStartsTalkToGuildLeaderQuestSuccessfully(){
        quest.startQuest(standardPlayer);
        assertEquals(QuestState.IN_PROGRESS, quest.getState());
    }

    @Test
    void playerTalkToGuildLeaderSuccessfully(){
        quest.talkToGuildLeader();
        assertTrue(quest.hasTalkedToGuildLeader());
    }

    @Test
    void playerMeetsEndRequirementsForTalkToGuildLeaderQuest() {
        quest.talkToGuildLeader();
        quest.endRequirementsFulfilled(standardPlayer);
        assertEquals(QuestState.COMPLETED, quest.getState());
    }

    @Test
    void canPlayerCompleteTalkToGuildLeaderQuest() {
        GuildMap guildMap = new GuildMap();
        quest.talkToGuildLeader();
        quest.questCompleted(standardPlayer);
        assertEquals(QuestState.DONE, quest.getState());
        assertEquals(1500, standardPlayer.getExperiencePoint());
        assertEquals(1, standardPlayer.getInventoryCount(guildMap));
    }

    /*
    @Test
    void healerGetsCorrectReward() {
        player = new Player("Healer", "Orc", 200, 1500);
        HealingPotion healingPotion = new HealingPotion();
        quest.rewardBasedOnClass(player);
        assertEquals(1, player.getInventoryCount(healingPotion));
    }

    @Test
    void damageGetsCorrectReward() {
        player = new Player("Damage", "Human", 200, 1500);
        CrystalChard crystalChard = new CrystalChard();
        quest.rewardBasedOnClass(player);
        assertEquals(1, player.getInventoryCount(crystalChard));
    }

    @Test
    void tankGetsCorrectReward() {
        HealingPotionRecipe healingPotionRecipe = new HealingPotionRecipe();
        quest.rewardBasedOnClass(standardPlayer);
        assertEquals(1, standardPlayer.getInventoryCount(healingPotionRecipe));
    }*/

    @Test
    void nonTankHumanGetsCorrectReward() {
        player = new Player("Healer", "Human", 200, 1500);
        BucklerOfUselessness bOU = new BucklerOfUselessness();
        quest.rewardBasedOnRace(player);
        assertEquals(1, player.getInventoryCount(bOU));
    }

    @Test
    void tankHumanGetsCorrectReward() {
        BreastplateOfTesting bOT = new BreastplateOfTesting();
        quest.rewardBasedOnRace(standardPlayer);
        assertEquals(1, standardPlayer.getInventoryCount(bOT));
    }

    @Test
    void nonDamageOrcGetsCorrectReward() {
        player = new Player("Tank", "Orc", 200, 1500);
        Heartsbane heartsbane = new Heartsbane();
        quest.rewardBasedOnRace(player);
        assertEquals(1, player.getInventoryCount(heartsbane));
    }

    @Test
    void damageOrcGetsCorrectReward() {
        player = new Player("Damage", "Orc", 200, 1500);
        WidowsWail widowsWail = new WidowsWail();
        quest.rewardBasedOnRace(player);
        assertEquals(1, player.getInventoryCount(widowsWail));
    }

}