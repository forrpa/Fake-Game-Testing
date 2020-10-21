package quest;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class StealthAndAttackTest {

    //parameterized tests

    private final String questName = "Stealth and Attack";
    private final String questDescription = "You have to follow your enemy without being seen and then attack him";

    Player player = new Player("Tank", "Human", 200, 1500);
    GuildMap guildMap = new GuildMap();
    StealthAndAttack quest;
    Enemy enemy = new Enemy(50);
    Attack attack;

    @Test
    void testConstructor(){
        quest = new StealthAndAttack(questName, questDescription, "pending", true, false, false, true, "townsman");
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
        player.addToInventory(guildMap);
        assertEquals(1500, player.getExperiencePoint());
        assertEquals(1, player.getInventoryCount(guildMap));
        //assertTrue(player.getQuestLog().contains(TalkToGuildLeader);
    }

    //Testa om det går att starta questet
    @Test
    void canPlayerStartStealthAndAttackQuest() {
        quest = new StealthAndAttack(questName, questDescription, "unlocked", true, false, false, false, "");
        player.addToInventory(guildMap);
        assertEquals("unlocked", quest.getState());
        assertTrue(quest.startRequirementsFulfilled(player));
    }

    //Testa om man lyckas med stealth
    @Test
    void stealthSucceeded(){
        quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, false, false, "");
        assertFalse(quest.isDiscovered());
    }

    //Testa om man inte lyckas med stealth (blir upptäckt)
    @Test
    void stealthNotSucceeded(){
        quest = new StealthAndAttack(questName, questDescription, "in progress", true, true, false, false, "");
        assertTrue(quest.isDiscovered());
    }

    //Testa krav för att starta attack eller förhandla med fienden
    @Test
    void canPlayerStartAttackOrNegotiatingWithEnemy(){
        quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, false, false, "");
        assertTrue(quest.stealth(player, enemy));
    }

    //Testa att dö i attack
    @Test
    void playerDiesInBattle() {
        player = new Player("Tank", "Human", 0, 1500);
        quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, true, false, "");
        assertEquals(0, player.getHealthPoint());
        //assertFalse(quest.attack(player, enemy));
    }

    //Tiden tar slut i attack
    @Test
    void playerRunsOutOfTimeInBattle(){
        attack = new Attack(0, player, enemy);
        assertEquals(0, attack.getTimer());
    }

    //Fiende dör i attack
    @Test
    void enemyDiesInBattle(){
        enemy = new Enemy(0);
        assertEquals(0, enemy.getHealth());
    }

    //Testa att förhandla med fienden
    @Test
    void playerHasNegotiatedWithEnemy(){
        quest = new StealthAndAttack(questName, "You decided to talk to your enemy instead of killing him. Now you cant reach the Guild so you have to talk to Townsman.", "in progress", true,false,false,true, "");
        assertTrue(quest.hasTalkedToEnemy());
        assertTrue(enemy.negotiate());
        assertEquals("You decided to talk to your enemy instead of killing him. Now you cant reach the Guild so you have to talk to Townsman.", quest.getDescription());
        assertFalse(player.isInInventory(guildMap));
    }


    //Testa slutkrav
    //Lyckas med smygande, lyckas attackera på tid samt prata med guild master
    @Test
    void playerMeetsEndRequirementsForAttackingOnTime(){
        quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, true, false, "questgiver");
        attack = new Attack(1, player, enemy);

        assertEquals("questgiver", quest.getTalkedTo());
        assertTrue(quest.hasAttacked());
        assertEquals(1, attack.getTimer());
        assertFalse(quest.hasTalkedToEnemy());

    }
    //Lyckas med smygande, attackera men ej lyckas på tid, prata med guildmaster
    @Test
    void playerMeetsEndRequirementsForNotAttackingOnTime(){
        quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, true, false, "questgiver");
        attack = new Attack(0, player, enemy);
        assertEquals("questgiver", quest.getTalkedTo());
        assertTrue(quest.hasAttacked());
        assertEquals(0, attack.getTimer());
        assertFalse(quest.hasTalkedToEnemy());
    }
    //Lyckas med smygande, ej attackera och prata med Townsman
    @Test
    void playerMeetsEndRequireMentsForNegotiatingWithEnemy(){
        quest = new StealthAndAttack(questName, questDescription, "in progress", true, false, false, true, "townsman");
        assertEquals("townsman", quest.getTalkedTo());
        assertFalse(quest.hasAttacked());
        assertTrue(quest.hasTalkedToEnemy());
    }

    //Kan man avsluta questet
    @Test
    void canPlayerCompleteStealthAndAttackQuest() {
        quest = new StealthAndAttack(questName, questDescription, "completed", true, false, true, false, "questgiver");
        assertEquals("completed", quest.getState());
        assertTrue(quest.endRequirementsFulfilled(player));
    }

    //Belöning
    //Ökad relation med guild, XP
    @Test
    void correctRewardsForAttackingOnTime(){
        player = new Player("Tank", "Human", 100, 2000);
        assertEquals(2000, player.getExperiencePoint()); //Kolla ökningen, istället för att lägga in 2000 öka med 2000
        //Relation med guild
    }
    //Eller liten ökad relation med guild, mindre XP
    @Test
    void correctRewardsForNotAttackingOnTime(){
        player = new Player("Tank", "Human", 200, 1000);
        assertEquals(1000, player.getExperiencePoint());
        //Guildrelation
    }
    //Eller minskad relation med guild, pengar
    @Test
    void correctRewardsForNegotiatingWithEnemy(){
        player = new Player("Tank", "Human", 300, 300, 1000);
        assertEquals(1000, player.getExperiencePoint());
        assertEquals(300, player.getMaxHealthPoint());
        //assertTrue(player.isInInventory("money"));//Ökning av pengar
        //Minus på guildrelation
    }


}