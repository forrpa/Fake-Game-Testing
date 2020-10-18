package quest;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class StealthAndAttackTest {

    private final String questName = "Stealth and Attack";
    private final String questDescription = "You have to follow your enemy without being seen and then attack him";

    //Behöver veta: Om man blir upptäckt, om HP blir noll, om man pratat med han som gav uppdraget eller den andra personen, om tiden gick ut
    //Attack eller pengar
    //Använda Guild Map till att hitta personen efter attacken

    @Test
    void testConstructor(){
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "pending", true, false, false, false);
        assertEquals("Stealt and Attack", quest.getName());
        assertEquals("You have to follow your enemy without being seen and then attack him", quest.getDescription());
        assertEquals("pending", quest.getState());
        assertTrue(quest.isMandatory());
        assertFalse(quest.isDiscovered());
        assertFalse(quest.isAttacked());
        assertFalse(quest.hasTalkedToQuestMaker());
    }

    //Testa förkrav
    //Ha 1500 experience och Guild Map från förra questet
    @Test
    void playerMeetsStartRequirementsForStealthAndAttackQuest(){
        Player player = new Player("Tank", "Human", 100, 1500);
        assertEquals(1500, player.getExperiencePoint());
        //assertTrue(player.getInventory().contains("Guild Map"));
    }

    //Testa om det går att starta questet
    @Test
    void canPlayerStartTalkToGuildLeaderQuest() {
        Player player = new Player("Tank", "Human", 100, 100);
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "unlocked", true, false, false, false);
        assertEquals("unlocked", quest.getState());
        assertTrue(quest.startRequirementsFulfilled(player));
    }

    //Testa om man lyckas med stealth

    //Testa om man inte lyckas med stealth (blir upptäckt)

    //Testa krav för att starta tjuvlyssning

    //Testa krav för att starta attack eller hot

    //Testa att dö i attack

    //Testa tillräcklig tid i attack

    //Testa ej tillräcklig tid i attack

    //Testa slutkrav
    //Lyckas med smygande, lyckas attackera på tid samt prata med guild master
    //Lyckas med smygande, ej attackera och prata med annan person
    //Lyckas med smygabde, attackera men ej lyckas på tid, prata med guildmaster

    //Kan man avsluta questet
    @Test
    void canPlayerCompleteTalkToGuildLeaderQuest() {
        Player player = new Player("Healer", "Orc", 200, 200);
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "completed", true, false, true, true);
        assertEquals("completed", quest.getState());
        assertTrue(quest.endRequirementsFulfilled(player));
    }

    //Belöning
    //Ökad relation med guild, XP
    //Eller liten ökad relation med guild, mindre XP
    //Eller minskad relation med guild, pengar


}