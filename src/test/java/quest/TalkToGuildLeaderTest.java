package quest;

import static org.junit.jupiter.api.Assertions.*;
import equipment.BreastplateOfTesting;
import equipment.BucklerOfUselessness;
import org.junit.jupiter.api.Test;

import player.Player;
import unit.Questgiver;
import weapon.Heartsbane;
import weapon.WidowsWail;


class TalkToGuildLeaderTest {

    Player player;
    Player standardPlayer = new Player("Tank", "Human", 200, 1000);
    TalkToGuildLeader quest = new TalkToGuildLeader();

    //Konstruktor
    @Test
    void constructorTest() {
        assertEquals("Talk to Guild Leader", quest.getName());
        assertEquals("You have to talk to the guild leader west of town.", quest.getDescription());
        assertEquals(QuestState.PENDING, quest.getState());
        assertTrue(quest.isMandatory());
    }

    //Spelaren har startkrav för att starta quest
    @Test
    void playerMeetsStartRequirementsForTalkToGuildLeaderQuest() {
        quest.startRequirementsFulfilled(standardPlayer);
        assertEquals(QuestState.UNLOCKED, quest.getState());
    }

    //Spelaren har inte startkrav för att starta quest
    @Test
    void playerDoesNotMeetStartRequirementsForTalkToGuildLeaderQuest() {
        player = new Player("Tank", "Human", 200, 999);
        assertFalse(quest.startRequirementsFulfilled(player));
    }

    //Lyckas starta quest
    @Test
    void playerStartsTalkToGuildLeaderQuestSuccessfully(){
        Questgiver questgiver = new Questgiver("Robert", new TalkToGuildLeader());
        quest.startQuest(standardPlayer);
        assertTrue(questgiver.talk());
        assertEquals(QuestState.IN_PROGRESS, quest.getState());
    }

    //Kan inte starta quest
    @Test
    void playerCantStartTalkToGuildLeader(){
        player = new Player("Tank", "Human", 200, 999);
        assertFalse(quest.startQuest(player));
    }

    //Lyckas tala med Guild Leader
    @Test
    void playerTalkToGuildLeaderSuccessfully(){
        GuildLeader guildLeader = new GuildLeader("Guild Leader Martin");
        assertTrue(guildLeader.talk());
        quest.talkToGuildLeader();
        assertTrue(quest.hasTalkedToGuildLeader());
    }

    //Spelaren har slutkrav för quest
    @Test
    void playerMeetsEndRequirementsForTalkToGuildLeaderQuest() {
        quest.talkToGuildLeader();
        quest.endRequirementsFulfilled(standardPlayer);
        assertEquals(QuestState.COMPLETED, quest.getState());
    }

    //Lyckas klara quest
    @Test
    void playerCompletesTalkToGuildLeaderQuestSuccessfully() {
        GuildMap guildMap = new GuildMap();
        quest.talkToGuildLeader();
        quest.completeQuest(standardPlayer);
        assertEquals(QuestState.DONE, quest.getState());
        assertEquals(1500, standardPlayer.getExperiencePoint());
        assertEquals(1, standardPlayer.getInventoryCount(guildMap));
    }

    //Kan inte klara quest
    @Test
    void playerCantCompleteTalkToGuildLeaderQuest(){
        assertFalse(quest.completeQuest(standardPlayer));
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

    //Non human tank får rätt reward
    @Test
    void nonTankHumanGetsCorrectReward() {
        player = new Player("Healer", "Human", 200, 1500);
        BucklerOfUselessness bOU = new BucklerOfUselessness();
        quest.rewardBasedOnRace(player);
        assertEquals(1, player.getInventoryCount(bOU));
    }

    //Tank human får rätt reward
    @Test
    void tankHumanGetsCorrectReward() {
        BreastplateOfTesting bOT = new BreastplateOfTesting();
        quest.rewardBasedOnRace(standardPlayer);
        assertEquals(1, standardPlayer.getInventoryCount(bOT));
    }

    //Icke damage orc får rätt reward
    @Test
    void nonDamageOrcGetsCorrectReward() {
        player = new Player("Tank", "Orc", 200, 1500);
        Heartsbane heartsbane = new Heartsbane();
        quest.rewardBasedOnRace(player);
        assertEquals(1, player.getInventoryCount(heartsbane));
    }

    //Damage orc får rätt reward
    @Test
    void damageOrcGetsCorrectReward() {
        player = new Player("Damage", "Orc", 200, 1500);
        WidowsWail widowsWail = new WidowsWail();
        quest.rewardBasedOnRace(player);
        assertEquals(1, player.getInventoryCount(widowsWail));
    }

    @Test
    void toStringMethodReturnsCorrectString(){
        assertEquals("Talk to Guild Leader: You have to talk to the guild leader west of town.. PENDING, true, false", quest.toString());
    }
}