package unit;

import org.junit.jupiter.api.Test;
import player.Player;
import quest.Quest;
import quest.ExploreAndAttack;
import quest.TalkToGuildLeader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestgiverTest {

    @Test
    void constructorSetsAttributes(){
        //Set-up
        Questgiver questgiver = new Questgiver("Guild Leader", new TalkToGuildLeader());
        //Check that attributes are set correctly
        assertEquals("Guild Leader", questgiver.getName());
        assertEquals("Talk to Guild Leader", questgiver.getAllQuestNames());
    }

    @Test
    void toStringReturnsCorrectString(){
        Questgiver questgiver = new Questgiver("Guild Leader", new TalkToGuildLeader());
        assertEquals("Name: Guild Leader, Alive: true, Quests available: Talk to Guild Leader.", questgiver.toString());
    }

    @Test
    void twoAvailableQuestsTalkToGuildLeaderAndExploreAndAttackTalkToGuildLeaderTakenAndExploreAndAttackLeftAtQuestgiver() {
        //Set-up
        Player player = new Player("NPC", "Orc", 5, 1500);
        ArrayList <Quest> questsForQuestgiver = new ArrayList<>();
        Quest quest1 = new TalkToGuildLeader();
        Quest quest2 = new ExploreAndAttack();
        questsForQuestgiver.add(quest1);
        questsForQuestgiver.add(quest2);
        Questgiver questgiver = new Questgiver("Guild Leader", questsForQuestgiver);
        //Take the TalkToGuildLeaderQuest
        player.takeAllQuests(questgiver);
        //player.takeQuest(questgiver, quest1);
        //Check that 1 quest named Talk to Guild Leader is taken
        assertTrue(player.isInCurrentQuests(quest1));
        //Check that questgiver has Explore and Attack left
        assertEquals("Explore and Attack",questgiver.getAllQuestNames());
    }

    @Test
    void playerTakesQuestTalkToGuildLeaderSuccessfully(){
        Player player = new Player("NPC", "Orc", 5, 1500);
        Quest quest = new TalkToGuildLeader();
        Questgiver questgiver = new Questgiver("Guild Leader", quest);
        player.takeQuest(questgiver, quest);
    }

    @Test
    void playerTakesQuestTalkToGuildLeaderFromQuestgiverWithoutThatQuestThrowsIllegalArgumentException(){
        Player player = new Player("NPC", "Orc", 5, 1500);
        Quest quest = new TalkToGuildLeader();
        Questgiver questgiver = new Questgiver("Guild Leader", new ExploreAndAttack());
        assertThrows(IllegalArgumentException.class, () -> player.takeQuest(questgiver, quest));
    }

    @Test
    void playerTakesQuestTalkToGuildLeaderWithoutHavingEnoughExperienceThrowsIllegalStateException(){
        Player player = new Player("NPC", "Orc", 5, 50);
        Quest quest = new TalkToGuildLeader();
        Questgiver questgiver = new Questgiver("Guild Leader", quest);
        assertThrows(IllegalStateException.class, () -> player.takeQuest(questgiver, quest));
    }

/*    @Test
    void playerTriesToTakeQuestTalkToGuildLeaderThrows(){
        Player player = new Player("NPC", "Orc", 5, 1500);
        Quest quest = new TalkToGuildLeader();
        Questgiver questgiver = new Questgiver("Guild Leader", quest);
        player.takeQuest(questgiver, quest);
    }*/


    @Test
    void constructorGivenNullQuestThrowsIllegalArgumentException(){
        Quest nullQuest = null;
        assertThrows(IllegalArgumentException.class, () -> new Questgiver("Questgiver", nullQuest));
    }

    @Test
    void constructorGivenNullQuestListThrowsIllegalArgumentException(){
        ArrayList<Quest> nullQuestList = null;
        assertThrows(IllegalArgumentException.class, () -> new Questgiver("Questgiver", nullQuestList));
    }
    @Test
    void constructorGivenQuestListWithNullValueThrowsIllegalArgumentException(){
        ArrayList<Quest> nullQuestList = new ArrayList<Quest>();
        Quest nullQuest = null;
        nullQuestList.add(nullQuest);
        assertThrows(IllegalArgumentException.class, () -> new Questgiver("Questgiver", nullQuestList));
    }
    @Test
    void constructorGivenEmptyQuestListThrowsIllegalArgumentException(){
        ArrayList<Quest> quests = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Questgiver("Guild Leader", quests));
    }
}

