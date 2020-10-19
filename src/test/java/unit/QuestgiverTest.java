package unit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestgiverTest {

    @Test
    void TwoAvailableQuestTakenFromQuestgiver() {
        Player player = new Player();
        ArrayList <Quest> questsForQuestgiver = new ArrayList<>();
        questsForQuestgiver.add(new Quest("Quest 1"));
        questsForQuestgiver.add(new Quest("Quest 2"));
        Questgiver questgiver = new Questgiver("Guild Leader", questsForQuestgiver);
        ArrayList <Quest> questsTaken = new ArrayList<>();
        questsTaken.addAll(questgiver.getAvailableQuests(player));
        assertEquals(2, questsTaken.size());
        assertEquals("Quest 1", questsTaken.get(0).getName());
        assertEquals("Quest 2", questsTaken.get(1).getName());
        assertTrue(questgiver.getAllQuests().isEmpty());
    }
}