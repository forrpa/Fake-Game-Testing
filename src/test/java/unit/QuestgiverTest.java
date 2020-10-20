package unit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestgiverTest {

    @Test
    void constructorSetsAttributes(){
        //Set-up
        Questgiver questgiver = new Questgiver("Guild Leader", new Quest("Test Quest"));
        //Check that attributes are set correctly
        assertEquals("Guild Leader", questgiver.getName());
        assertEquals("Test Quest", questgiver.getAllQuests().get(0));
    }

    @Test
    void twoAvailableQuestsQuest1AndQuest2TakenFromQuestgiverAndNoQuestsLeft() {
        //Set-up
        Player player = new Player();
        ArrayList <Quest> questsForQuestgiver = new ArrayList<>();
        questsForQuestgiver.add(new Quest("Quest 1"));
        questsForQuestgiver.add(new Quest("Quest 2"));
        Questgiver questgiver = new Questgiver("Guild Leader", questsForQuestgiver);
        ArrayList <Quest> questsTaken = new ArrayList<>();
        //Take all available quests
        questsTaken.addAll(questgiver.getAvailableQuests(player));
        //Check that 2 quests named Quest 1 and Quest 2 is taken
        assertEquals(2, questsTaken.size());
        assertEquals("Quest 1", questsTaken.get(0).getName());
        assertEquals("Quest 2", questsTaken.get(1).getName());
        //Check that questgiver has no quests left
        assertTrue(questgiver.getAllQuests().isEmpty());
    }
}