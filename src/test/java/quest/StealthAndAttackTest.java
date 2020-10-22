package quest;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class StealthAndAttackTest {

    Player standardPlayer = new Player("Tank", "Human", 200, 200, 1500);
    Player player;
    GuildMap guildMap = new GuildMap();
    StealthAndAttack quest = new StealthAndAttack();
    Enemy enemy = new Enemy(50);
    Attack attack;

    public void addGuildMapToInventory(){
        standardPlayer.addToInventory(guildMap);
    }

    @Test
    void testConstructor(){
        //quest = new StealthAndAttack(false, false, true, "townsman");
        assertEquals("Stealth and Attack", quest.getName());
        assertEquals("You have to follow your enemy without being seen and then attack him", quest.getDescription());
        assertEquals("pending", quest.getState());
        assertTrue(quest.isMandatory());
        /*assertFalse(standardQuest.isDiscovered());
        assertFalse(standardQuest.hasAttacked());
        assertFalse(standardQuest.hasTalkedToEnemy());
        assertNull(standardQuest.getTalkedTo());*/
    }

    //Testa förkrav, Ha 1500 experience och Guild Map från förra questet, klarat questet TalkToGuildLeader
    @Test
    void playerMeetsStartRequirementsForStealthAndAttackQuest(){
        addGuildMapToInventory();
        quest.startRequirementsFulfilled(standardPlayer);
        assertEquals("unlocked", quest.getState());
    }

    //Har inte tillräckligt med experience för att starta questet
    @Test
    void playerDoesNotMeetStartRequirementsForStealthAndAttackQuest(){
        player = new Player("Human", "Damage", 200, 200, 1499);
        assertFalse(quest.startRequirementsFulfilled(player));
    }

    //Testa om det går att starta questet
    @Test
    void playerStartsQuestSuccessfully() {
        addGuildMapToInventory();
        quest.startQuest(standardPlayer);
        assertEquals("in progress", quest.getState());
        assertEquals("Your first job is to follow the enemy without being seen.", quest.getDescription());
    }

    //Metoden resetQuest lyckas
    @Test
    void questResetsSuccessfully(){
        player = new Player("Orc", "Damage", 0,200, 1500);
        quest.resetQuest(player);
        assertEquals(200, player.getHealthPoint());
        assertFalse(quest.hasAttacked());
        assertFalse(quest.isDiscovered());
    }

    //Testa om man lyckas med stealth
    @Test
    void stealthSucceeded(){
        quest.stealth(player, enemy); //FUNKAR EJ!!!!!
        assertFalse(quest.isDiscovered());
        assertEquals("You succeeded not being seen, now you have to decide if you want to kill your enemy or negotiate with it.", quest.getDescription());
    }

    //Testa om man inte lyckas med stealth (blir upptäckt)
    @Test
    void stealthNotSucceeded(){
        //quest = new StealthAndAttack(true, false, false, "");
        quest.stealth(player, enemy); //FUNKAR EJ!!!!!!!
        assertTrue(quest.isDiscovered()); //Kunna få ut true eller false stealth
    }

    //Testa krav för att starta attack eller förhandla med fienden
    @Test
    void canPlayerStartAttackOrNegotiatingWithEnemy(){
        assertTrue(quest.stealth(standardPlayer, enemy)); //FUNKAR EJ
    }

    //Testa att dö i attack
    @Test
    void playerDiesInBattle() {
        quest.attack(standardPlayer, enemy); //Där spelaren dör
        //quest = new StealthAndAttack(false, true, false, "");
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
        //quest = new StealthAndAttack(false, true, false, "");
        assertTrue(quest.attack(standardPlayer, enemy)); //Hur påverka att det blir true/false
    }

    @Test
    void unSuccessfulAttack(){
        assertFalse(quest.attack(standardPlayer, enemy)); //Funkar ej
    }

    //Testa att förhandla med fienden
    @Test
    void successfulNegotiatingWithEnemy(){
        //quest = new StealthAndAttack(false,false,true, "");
        addGuildMapToInventory();
        quest.negotiateWithEnemy(standardPlayer,enemy);
        assertTrue(quest.hasTalkedToEnemy());
        assertTrue(enemy.negotiate());
        assertEquals("You decided to talk to your enemy instead of killing him. Now you cant reach the Guild so you have to talk to Townsman.", quest.getDescription());
        assertFalse(standardPlayer.isInInventory(guildMap));
    }

    @Test
    void playerCanTalkToQuestGiver(){
        addGuildMapToInventory();
        assertTrue(quest.attack(standardPlayer, enemy)); //True/false
        assertEquals(1, standardPlayer.getInventoryCount(guildMap));
    }

    @Test
    void succesfulTalkToQuestGiver(){
        QuestGiver questGiver = new QuestGiver();
        //quest.attack(standardPlayer, enemy); //Kolla att attack blir true
        addGuildMapToInventory();
        quest.talkToQuestGiver(standardPlayer, enemy, questGiver);
        assertTrue(questGiver.talkToPlayer());
        assertEquals("questgiver", quest.getTalkedTo());
    }

    @Test
    void canPlayerTalkToTownsman(){
        addGuildMapToInventory();
        assertTrue(quest.negotiateWithEnemy(standardPlayer, enemy));
    }

    @Test
    void succesfulTalkToTownsman(){
        addGuildMapToInventory();
        quest.talkToTownsman(standardPlayer, enemy);
        assertEquals("townsman", quest.getTalkedTo());
    }

    //Testa slutkrav
    //Lyckas med smygande, lyckas attackera på tid samt prata med guild master
    @Test
    void playerMeetsEndRequirementsForAttackingOnTime(){
        assertTrue(quest.endRequirementsForAttackingOnTime()); //FUNKAR EJ
    }

    //Lyckas med smygande, attackera men ej lyckas på tid, prata med guildmaster
    @Test
    void playerMeetsEndRequirementsForNotAttackingOnTime(){
        assertTrue(quest.endRequirementsForNotAttackingOnTime()); //FUNKAR EJ
    }

    //Lyckas med smygande, ej attackera och prata med Townsman
    @Test
    void playerMeetsEndRequireMentsForNegotiatingWithEnemy(){
        //FIXA!!!
        addGuildMapToInventory();
        quest.negotiateWithEnemy(standardPlayer, enemy);
        quest.talkToTownsman(standardPlayer, enemy);
        //assertTrue(quest.endRequirementsForNegotiatingWithEnemy());
    }

    //Klarar man något krav för att klara questet
    @Test
    void playerMeetsEndRequirementsForStealthAndAttackQuest(){
        //Någon av de tre
        quest.endRequirementsFulfilled(player);
        assertEquals("completed", quest.getState());
    }

    @Test
    void playerCompletesQuestSuccessfully(){
        //quest = new StealthAndAttack(false, false, true, "townsman");
        //Set up

        quest.questCompleted(standardPlayer);
        assertEquals("done", quest.getState());
        assertEquals("You completed the quest!", quest.getDescription());
        assertEquals(300, standardPlayer.getHealthPoint());
    }

    //Belöning
    //Ökad relation med guild, XP
    @Test
    void correctRewardsForAttackingOnTime(){
        quest.rewardWhenAttackingOnTime(standardPlayer);
        assertEquals(2500, standardPlayer.getExperiencePoint());
        //Relation med guild
    }
    //Eller liten ökad relation med guild, mindre XP
    @Test
    void correctRewardsForNotAttackingOnTime(){
        quest.rewardWhenNotAttackingOnTime(standardPlayer);
        assertEquals(1800, standardPlayer.getExperiencePoint());
        //Guildrelation
    }
    //Eller minskad relation med guild, pengar
    @Test
    void correctRewardsForNegotiatingWithEnemy(){
        quest.rewardWhenNegotiatingWithEnemy(standardPlayer);
        assertEquals(2000, standardPlayer.getExperiencePoint());
        assertEquals(300, standardPlayer.getMaxHealthPoint());
        //assertTrue(player.isInInventory("money"));//Ökning av pengar
        //Minus på guildrelation
    }

    //Förhandlade med fienden rätt reward
    @Test
    void getRewardReturnsCorrectRewardForNegotiatingWithEnemy(){
        //quest = new StealthAndAttack(false, false, true, "townsman");
        //Setup

        quest.getReward(standardPlayer);
        assertEquals(2000, standardPlayer.getExperiencePoint());
        assertEquals(300, standardPlayer.getMaxHealthPoint());
    }
}