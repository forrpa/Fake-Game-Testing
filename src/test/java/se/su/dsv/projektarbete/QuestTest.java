package java.se.su.dsv.projektarbete;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.quest.TalkToGuildLeader;
import java.se.su.dsv.projektarbete.Player;

class QuestTest {

    //Test:
    //Startkrav
    //Slutkrav
    //Belöningssystem
    //Frivillig vs obligatorisk
    //Tillstånd

    @Test
    void playerMeetsStartRequirementsForQuest(){

        Player player = new Player("Tank", "Human", 100,100,5000);
        TalkToGuildLeader quest = new TalkToGuildLeader("TTGL", "Talk to GL", true);
        assertTrue(player.getExperiencePoint() >= quest.getRequiredXP);
        assertTrue(player.getInventory().contains(quest.getQuestItem));

        //assertTrue(player.getExperiencePoint() >= 1000);
        //assertTrue(player.getInventory().contains("Guild Map"));
    }

}