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
        Questgiver questgiver = new Questgiver("Guild Leader", new TalkToGuildLeader(false));
        //Check that attributes are set correctly
        assertEquals("Guild Leader", questgiver.getName());
        assertEquals("Talk to Guild Leader", questgiver.getAllQuestNames());
    }

    @Test
    void toStringReturnsCorrectString(){
        Questgiver questgiver = new Questgiver("Guild Leader", new TalkToGuildLeader(false));
        assertEquals("Name: Guild Leader, Alive: true, Quests available: Talk to Guild Leader.", questgiver.toString());
    }

    @Test
    void twoAvailableQuestsTalkToGuildLeaderAndStealthAndAttackTalkToGuildLeaderTakenAndStealthAndAttackLeftAtQuestgiver() {
        //Set-up
        Player player = new Player("NPC", "Orc", 5, 5,1600);
        ArrayList <Quest> questsForQuestgiver = new ArrayList<>();
        questsForQuestgiver.add(new TalkToGuildLeader(false));
        questsForQuestgiver.add(new StealthAndAttack(false, false, false, "false"));
        Questgiver questgiver = new Questgiver("Guild Leader", questsForQuestgiver);
        ArrayList <Quest> questsTaken = new ArrayList<>();
        //Take all available quests
        questsTaken.addAll(questgiver.getAvailableQuests(player));
        //Check that 1 quest named Talk to Guild Leader is taken
        assertEquals(1, questsTaken.size());
        assertEquals("Talk to Guild Leader", questsTaken.get(0).getName());
        //Check that questgiver has Stealth and Attack left
        assertEquals("Stealth and Attack",questgiver.getAllQuestNames());
    }
}

