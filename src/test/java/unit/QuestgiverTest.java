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
    void cantCreateNewQuestgiversWithANullQuest(){
        Quest quest = null;
        assertThrows(IllegalArgumentException.class, () -> new Questgiver("Guild Leader", quest));
    }

    @Test
    void cantCreateNewQuestgiversWithANullList(){
        ArrayList<Quest> quests = null;
        assertThrows(IllegalArgumentException.class, () -> new Questgiver("Guild Leader", quests));
    }

    @Test
    void cantCreateNewQuestgiversWithNoQuestsInList(){
        ArrayList<Quest> quests = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Questgiver("Guild Leader", quests));
    }

    @Test
    void twoAvailableQuestsTalkToGuildLeaderAndStealthAndAttackTalkToGuildLeaderTakenAndStealthAndAttackLeftAtQuestgiver() {
        //Set-up
        Player player = new Player("NPC", "Orc", 5, 1500);
        ArrayList <Quest> questsForQuestgiver = new ArrayList<>();
        Quest quest1 = new TalkToGuildLeader();
        Quest quest2 = new ExploreAndAttack();
        questsForQuestgiver.add(quest1);
        questsForQuestgiver.add(quest2);
        Questgiver questgiver = new Questgiver("Guild Leader", questsForQuestgiver);
        ArrayList <Quest> questsTaken = new ArrayList<>();
        //Take all available quests
        questgiver.takeAvailableQuests(player);
        //Check that 1 quest named Talk to Guild Leader is taken
        assertTrue(player.isInCurrentQuests(quest1));
        //Check that questgiver has Stealth and Attack left
        assertEquals("Stealth and Attack",questgiver.getAllQuestNames());
    }
}

