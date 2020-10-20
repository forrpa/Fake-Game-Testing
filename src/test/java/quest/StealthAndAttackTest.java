package quest;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class StealthAndAttackTest {

    private final String questName = "Stealth and Attack";
    private final String questDescription = "You have to follow your enemy without being seen and then attack him";

    //Behöver veta: Om man blir upptäckt, om HP blir noll, om man pratat med han som gav uppdraget eller den andra personen, om tiden gick ut
    //Attack eller pengar

    @Test
    void testConstructor(){
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "pending", true, false, false, true, "townsman");
        assertEquals("Stealth and Attack", quest.getName());
        assertEquals("You have to follow your enemy without being seen and then attack him", quest.getDescription());
        assertEquals("pending", quest.getState());
        assertTrue(quest.isMandatory());
        assertFalse(quest.isDiscovered());
        assertFalse(quest.hasAttacked());
        assertTrue(quest.hasTalkedToEnemy());
        assertEquals("townsman", quest.getTalkedTo());
    }

    //Testa förkrav, Ha 1500 experience och Guild Map från förra questet, klarat questet TalkToGuildLeader
    @Test
    void playerMeetsStartRequirementsForStealthAndAttackQuest(){
        Player player = new Player("Tank", "Human", 100, 1500);
        assertEquals(1500, player.getExperiencePoint());
        //assertTrue(player.getInventory().contains("Guild Map"));
        //assertTrue(player.getQuestLog().contains(TalkToGuildLeader);
    }

    //Testa om det går att starta questet
    @Test
    void canPlayerStartStealthAndAttackQuest() {
        Player player = new Player("Tank", "Human", 100, 1500);
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "unlocked", true, false, false, false, "");
        assertEquals("unlocked", quest.getState());
        assertTrue(quest.startRequirementsFulfilled(player));
    }

    //Testa om man lyckas med stealth
    @Test
    void stealthSucceeded(){
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, false, false, "");
        assertFalse(quest.isDiscovered());
    }

    //Testa om man inte lyckas med stealth (blir upptäckt)
    @Test
    void stealthNotSucceeded(){
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "in progress", true, true, false, false, "");
        assertTrue(quest.isDiscovered());
    }

    //Testa krav för att starta attack eller förhandla med fienden
    @Test
    void canPlayerStartAttack(){
        Player player = new Player("Tank", "Human", 100, 1500);
        Enemy enemy = new Enemy();
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, false, false, "");
        assertTrue(quest.stealth(player, enemy));
        assertFalse(quest.isDiscovered());
    }

    //Testa att dö i attack
    @Test
    void playerDiesInBattle() {
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, true, false, "");
        Player player = new Player("Tank", "Human", 100, 1500);
        Enemy enemy = new Enemy();
        assertEquals(0, player.getHealthPoint());
        assertFalse(quest.attack(player, enemy));
    }

    //Fiende dör i attack
    @Test
    void enemyDiesInBattle(){

    }

    //Testa tillräcklig tid i attack

    //Testa ej tillräcklig tid i attack

    //Testa att förhandla med fienden


    //Testa slutkrav
    //Lyckas med smygande, lyckas attackera på tid samt prata med guild master
    @Test
    void playerMeetsEndRequirementsForAttackingOnTime(){
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, true, false, "questgiver");

        //Fixa så man kan se hur många sekunder som är kvar
        assertFalse(quest.isDiscovered());
        assertEquals("questgiver", quest.getTalkedTo());
        assertTrue(quest.hasAttacked());
        assertEquals(1, quest.getSeconds());
        assertFalse(quest.hasTalkedToEnemy());

    }
    //Lyckas med smygande, attackera men ej lyckas på tid, prata med guildmaster
    @Test
    void playerMeetsEndRequirementsForNotAttackingOnTime(){
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, true, false, "questgiver");
        assertFalse(quest.isDiscovered());
        assertEquals("questgiver", quest.getTalkedTo());
        assertTrue(quest.hasAttacked());
        assertEquals(0, quest.getSeconds());
        assertFalse(quest.hasTalkedToEnemy());
    }
    //Lyckas med smygande, ej attackera och prata med Townsman
    @Test
    void playerMeetsEndRequireMentsForNegotiatingWithEnemy(){
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, false, true, "townsman");
        assertFalse(quest.isDiscovered());
        assertEquals("townsman", quest.getTalkedTo());
        assertFalse(quest.hasAttacked());
        assertTrue(quest.hasTalkedToEnemy());
    }

    //Kan man avsluta questet
    @Test
    void canPlayerCompleteStealthAndAttackQuest() {
        Player player = new Player("Healer", "Orc", 200, 1500);
        StealthAndAttack quest = new StealthAndAttack(questName, questDescription, "completed", true, false, true, false, "questgiver");
        assertEquals("completed", quest.getState());
        assertTrue(quest.endRequirementsFulfilled(player));
    }

    //Belöning
    //Ökad relation med guild, XP
    @Test
    void correctRewardsForAttackingOnTime(){
        Player player = new Player("Tank", "Human", 100, 2000);
        assertEquals(2000, player.getExperiencePoint()); //Kolla ökningen
        //Relation med guild
    }
    //Eller liten ökad relation med guild, mindre XP
    @Test
    void correctRewardsForNotAttackingOnTime(){
        Player player = new Player("Tank", "Human", 100, 1000);
        assertEquals(1000, player.getExperiencePoint());
        //Guildrelation
    }
    //Eller minskad relation med guild, pengar
    @Test
    void correctRewardsForNegotiatingWithEnemy(){
        Player player = new Player("Tank", "Human", 100, 1000);
        assertEquals(1000, player.getExperiencePoint());
        assertTrue(player.getInventory().contains("money")); //Ökning av pengar
        //Minus på guildrelation
    }


}