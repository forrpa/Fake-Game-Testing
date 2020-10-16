package quest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import player.Player;

class QuestTest {

    //Test:
    //Startkrav
    //Slutkrav
    //Belöningssystem
    //Frivillig vs obligatorisk
    //Tillstånd

    @Test
    void playerMeetsStartRequirementsForTalkToGuildLeaderQuest(){

        Player player = new Player("Tank", "Human", 100,100,1000);
        //TalkToGuildLeader quest = new TalkToGuildLeader("TTGL", "Talk to GL", true);
        //assertTrue(player.getExperiencePoint() >= quest.getRequiredXP);
        assertEquals(1000, player.getExperiencePoint());
        //assertTrue(player.getInventory().contains(quest.getQuestItem));
        assertTrue(player.getInventory().contains("Guild Map"));
    }

    @Test
    void playerMeetsEndRequirementsForTalkToGuildLeaderQuest(){
        //Slutkrav: Ha pratat med guildledaren + ha Guild Map i inventory
        Player player = new Player("Healer", "Orc", 200, 200, 1500);
        TalkToGuildLeader quest = new TalkToGuildLeader("TTGL", "Talk", true, true);
        assertTrue(quest.talkedToGuildLeader);
        assertTrue(player.getInventory().contains("Guild Map"));
    }
}