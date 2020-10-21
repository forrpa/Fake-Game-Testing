package quest;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class StealthAndAttackTest {

    //parameterized tests

    Player standardPlayer = new Player("Tank", "Human", 200, 200, 1500);
    Player player;
    GuildMap guildMap = new GuildMap();
    StealthAndAttack quest;
    StealthAndAttack standardQuest = new StealthAndAttack(false,false,false,"");
    Enemy enemy = new Enemy(50);
    Attack attack;

    @Test
    void testConstructor(){
        quest = new StealthAndAttack(false, false, true, "townsman");
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
        standardPlayer.addToInventory(guildMap); //?
        standardQuest.startRequirementsFulfilled(standardPlayer);
        assertEquals("unlocked", standardQuest.getState());
    }

    //Har inte tillräckligt med experience för att starta questet
    @Test
    void playerDoesNotMeetStartRequirementsForStealthAndAttackQuest(){
        player = new Player("Human", "Damage", 200, 200, 1499);
        assertFalse(standardQuest.startRequirementsFulfilled(player));
    }

    //Testa om det går att starta questet
    @Test
    void playerStartsQuestSuccessfully() {
        standardPlayer.addToInventory(guildMap);
        standardQuest.startQuest(standardPlayer);
        assertEquals("in progress", standardQuest.getState());
        assertEquals("Your first job is to follow the enemy without being seen.", standardQuest.getDescription());
    }

    //Metoden resetQuest lyckas
    @Test
    void questResetsSuccessfully(){
        player = new Player("Orc", "Damage", 0,200, 1500);
        standardQuest.resetQuest(player);
        assertEquals(200, player.getHealthPoint());
        assertFalse(standardQuest.hasAttacked());
        assertFalse(standardQuest.isDiscovered());
    }

    //Testa om man lyckas med stealth
    @Test
    void stealthSucceeded(){
        standardQuest.stealth(player, enemy); //FUNKAR EJ!!!!!
        assertFalse(standardQuest.isDiscovered());
        assertEquals("You succeeded not being seen, now you have to decide if you want to kill your enemy or negotiate with it.", standardQuest.getDescription());
    }

    //Testa om man inte lyckas med stealth (blir upptäckt)
    @Test
    void stealthNotSucceeded(){
        quest = new StealthAndAttack(true, false, false, "");
        quest.stealth(player, enemy); //FUNKAR EJ!!!!!!!
        assertTrue(quest.isDiscovered());
    }

    //Testa krav för att starta attack eller förhandla med fienden
    @Test
    void canPlayerStartAttackOrNegotiatingWithEnemy(){
        assertTrue(standardQuest.stealth(standardPlayer, enemy)); //FUNKAR EJ
    }

    //Testa att dö i attack
    @Test
    void playerDiesInBattle() {
        standardPlayer = new Player("Tank", "Human", 0, 1500);
        quest = new StealthAndAttack(false, true, false, "");
        assertEquals(0, standardPlayer.getHealthPoint());
        //assertFalse(quest.attack(player, enemy));
    }

    //Tiden tar slut i attack
    @Test
    void playerRunsOutOfTimeInBattle(){
        attack = new Attack(0, standardPlayer, enemy);
        assertEquals(0, attack.getTimer());
    }

    //Fiende dör i attack
    @Test
    void enemyDiesInBattle(){
        enemy = new Enemy(0);
        assertEquals(0, enemy.getHealth());
    }

    @Test
    void successfulAttack(){
        quest = new StealthAndAttack(false, true, false, "");
        assertTrue(quest.attack(standardPlayer, enemy)); //Hur påverka att det blir true/false
    }

    @Test
    void unSuccessfulAttack(){

    }

    //Testa att förhandla med fienden
    @Test
    void successfulNegotiatingWithEnemy(){
        quest = new StealthAndAttack(false,false,true, "");
        standardPlayer.addToInventory(guildMap);
        quest.negotiateWithEnemy(standardPlayer,enemy);
        assertTrue(quest.hasTalkedToEnemy());
        assertTrue(enemy.negotiate());
        assertEquals("You decided to talk to your enemy instead of killing him. Now you cant reach the Guild so you have to talk to Townsman.", quest.getDescription());
        assertFalse(standardPlayer.isInInventory(guildMap));
    }

    @Test
    void canPlayerTalkToQuestGiver(){

    }

    @Test
    void succesfulTalkToQuestGiver(){

    }

    @Test
    void canPlayerTalkToTownsman(){

    }

    @Test
    void succesfulTalkToTownsman(){

    }

    //Testa slutkrav
    //Lyckas med smygande, lyckas attackera på tid samt prata med guild master
    @Test
    void playerMeetsEndRequirementsForAttackingOnTime(){
        quest = new StealthAndAttack(false, true, false, "questgiver");
        assertTrue(quest.endRequirementsForAttackingOnTime()); //FUNKAR EJ
    }

    //Lyckas med smygande, attackera men ej lyckas på tid, prata med guildmaster
    @Test
    void playerMeetsEndRequirementsForNotAttackingOnTime(){
        quest = new StealthAndAttack(false, true, false, "questgiver");
        assertTrue(quest.endRequirementsForNotAttackingOnTime()); //FUNKAR EJ
    }

    //Lyckas med smygande, ej attackera och prata med Townsman
    @Test
    void playerMeetsEndRequireMentsForNegotiatingWithEnemy(){
        quest = new StealthAndAttack(false, false, true, "townsman");
        assertTrue(quest.endRequirementsForNegotiatingWithEnemy());
    }

    //Klarar man något krav för att klara questet
    @Test
    void playerMeetsEndRequirementsForStealthAndAttackQuest(){
        quest = new StealthAndAttack(false, true, false, "questgiver");
        quest.endRequirementsFulfilled(player);
        assertEquals("completed", quest.getState());
    }

    @Test
    void playerCompletesQuestSuccessfullyWhenNotAttacking(){
        quest = new StealthAndAttack(false, false, true, "townsman");
        quest.questCompleted(standardPlayer);
        assertEquals("done", quest.getState());
        assertEquals("You completed the quest!", quest.getDescription());
        assertEquals(300, standardPlayer.getHealthPoint());
    }

    //Belöning
    //Ökad relation med guild, XP
    @Test
    void correctRewardsForAttackingOnTime(){
        standardQuest.rewardWhenAttackingOnTime(standardPlayer);
        assertEquals(2500, standardPlayer.getExperiencePoint());
        //Relation med guild
    }
    //Eller liten ökad relation med guild, mindre XP
    @Test
    void correctRewardsForNotAttackingOnTime(){
        standardQuest.rewardWhenNotAttackingOnTime(standardPlayer);
        assertEquals(1800, standardPlayer.getExperiencePoint());
        //Guildrelation
    }
    //Eller minskad relation med guild, pengar
    @Test
    void correctRewardsForNegotiatingWithEnemy(){
        standardQuest.rewardWhenNegotiatingWithEnemy(standardPlayer);
        assertEquals(2000, standardPlayer.getExperiencePoint());
        assertEquals(300, standardPlayer.getMaxHealthPoint());
        //assertTrue(player.isInInventory("money"));//Ökning av pengar
        //Minus på guildrelation
    }

    @Test
    void getRewardReturnsCorrectReward(){
        //Förhandlade med fienden
        quest = new StealthAndAttack(false, false, true, "townsman");
        quest.getReward(standardPlayer);
        assertEquals(2000, standardPlayer.getExperiencePoint());
        assertEquals(300, standardPlayer.getMaxHealthPoint());
    }


}