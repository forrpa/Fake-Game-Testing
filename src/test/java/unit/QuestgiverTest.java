package unit;

import org.junit.jupiter.api.Test;
import player.Player;
import quest.Quest;
import quest.StealthAndAttack;
import quest.TalkToGuildLeader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestgiverTest {

    @Test
    void constructorSetsAttributes(){
        //Set-up
        Questgiver questgiver = new Questgiver("Guild Leader", new TalkToGuildLeader("test", "test", "test", true, false));
        //Check that attributes are set correctly
        assertEquals("Guild Leader", questgiver.getName());
        assertEquals("Talk to Guild Leader", questgiver.getAllQuests().get(0));
    }

    @Test
    void twoAvailableQuestsQuest1AndQuest2TakenFromQuestgiverAndNoQuestsLeft() {
        //Set-up
        Player player = new Player("NPC", "Orc", 5, 5,1600);
        ArrayList <Quest> questsForQuestgiver = new ArrayList<>();
        questsForQuestgiver.add(new TalkToGuildLeader("test", "test", "test", true, false));
        questsForQuestgiver.add(new StealthAndAttack("test", "test", "test", false, false, false, false, "test"));
        Questgiver questgiver = new Questgiver("Guild Leader", questsForQuestgiver);
        ArrayList <Quest> questsTaken = new ArrayList<>();
        //Take all available quests
        questsTaken.addAll(questgiver.getAvailableQuests(player));
        //Check that 2 quests named Quest 1 and Quest 2 is taken
        assertEquals(2, questsTaken.size());
        assertEquals("Talk to Guild Leader", questsTaken.get(0).getName());
        assertEquals("Stealth and Attack", questsTaken.get(1).getName());
        //Check that questgiver has no quests left
        assertTrue(questgiver.getAllQuests().isEmpty());
    }
}