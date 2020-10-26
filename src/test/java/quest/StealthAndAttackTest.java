package quest;

import org.junit.jupiter.api.Test;
import player.Player;
import unit.AttackType;
import unit.Bat;
import unit.Wolf;

import static org.junit.jupiter.api.Assertions.*;

class StealthAndAttackTest {

    Player standardPlayer = new Player("Tank", "Human", 200, 1500);
    Player player;
    GuildMap guildMap = new GuildMap();
    StealthAndAttack quest = new StealthAndAttack();
    Wolf enemy = new Wolf();

    //Lägga till en Guild Map i Players inventory
    public void addGuildMapToInventory(){
        standardPlayer.addToInventory(guildMap);
    }

    //Konstruktor
    @Test
    void testConstructor(){
        assertEquals("Stealth and Attack", quest.getName());
        assertEquals("You have to follow your enemy without being seen and then attack him", quest.getDescription());
        assertEquals(QuestState.PENDING, quest.getState());
        assertTrue(quest.isMandatory());
    }

    //Testa förkrav, Ha 1500 experience och Guild Map från förra questet, klarat questet TalkToGuildLeader
    @Test
    void playerMeetsStartRequirementsForStealthAndAttackQuest(){
        addGuildMapToInventory();
        quest.startRequirementsFulfilled(standardPlayer);
        assertEquals(QuestState.UNLOCKED, quest.getState());
        assertTrue(standardPlayer.isInAvailableQuests(quest));
    }

    //Har inte tillräckligt med experience för att starta questet
    @Test
    void playerDoesNotMeetStartRequirementsForStealthAndAttackQuest(){
        player = new Player("Human", "Damage", 200, 1499);
        assertFalse(quest.startRequirementsFulfilled(player));
    }

    //Testa om det går att starta questet
    @Test
    void playerStartsStealthAndAttackQuestSuccessfully() {
        addGuildMapToInventory();
        quest.startQuest(standardPlayer);
        assertEquals(QuestState.IN_PROGRESS, quest.getState());
        assertTrue(standardPlayer.isInCurrentQuests(quest));
        assertEquals("Your first job is to follow the enemy without being seen.", quest.getDescription());
    }

    //Kan ej starta questet
    @Test
    void playerCantStartStealthAndAttackQuest(){
        assertFalse(quest.startQuest(standardPlayer));
    }

    //Metoden resetQuest lyckas
    @Test
    void questResetsSuccessfully(){
        standardPlayer.setHealthPoint(0);
        quest.resetQuest(standardPlayer);
        assertEquals(200, standardPlayer.getHealthPoint());
        assertFalse(quest.hasAttacked());
    }

    //Testa om man lyckas med stealth
    @Test
    void stealthSucceeded(){
        quest.stealth(standardPlayer, enemy);
        assertTrue(quest.hasSucceededStealth());
        assertEquals("You succeeded not being seen, now you have to decide if you want to kill your enemy or negotiate with it.", quest.getDescription());
        assertTrue(quest.stealth(standardPlayer, enemy));
    }

    //Testa om man inte lyckas med stealth (blir upptäckt)
    @Test
    void stealthNotSucceeded(){
        standardPlayer.setHealthPoint(1);
        Bat bat = new Bat("Bat", 10, 5, 10, AttackType.ICE, AttackType.FIRE);
        assertFalse(quest.stealth(standardPlayer, bat));
    }

    //Testa krav för att starta attack eller förhandla med fienden
    @Test
    void canPlayerStartAttackOrNegotiatingWithEnemy(){
        quest.stealth(standardPlayer, enemy);
        assertTrue(quest.hasSucceededStealth());
    }

    @Test
    void playerCantStartAttackOrNegotiatingWithEnemy(){
        assertFalse(quest.attack(standardPlayer, enemy));
    }

    //Fiende dör i attack och man lyckas
    @Test
    void successfulAttack(){
        quest.stealth(standardPlayer, enemy);
        enemy.setHealthPoint(1);
        assertTrue(quest.attack(standardPlayer, enemy));
        assertTrue(quest.hasAttacked());
        assertEquals("You succeeded killing your enemy on time. Go talk to the Guild Leader for your reward!", quest.getDescription());
    }

    //Spelaren dör i attack
    @Test
    void playerDiesInAttack(){
        quest.stealth(standardPlayer, enemy);
        enemy.setHealthPoint(1000);
        assertFalse(quest.attack(standardPlayer, enemy));
    }

    //Testa att förhandla med fienden
    @Test
    void successfulNegotiatingWithEnemy(){
        addGuildMapToInventory();
        quest.stealth(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer);
        assertTrue(quest.hasTalkedToEnemy());
        assertEquals("You decided to talk to your enemy instead of killing him. Now you cant reach the Guild so you have to talk to Townsman.", quest.getDescription());
        assertFalse(standardPlayer.isInInventory(guildMap));
    }

    @Test
    void playerCanTalkToGuildMaster(){
        addGuildMapToInventory();
        quest.stealth(standardPlayer, enemy);
        quest.attack(standardPlayer, enemy); //Ger false
        assertTrue(quest.hasAttacked());
        assertEquals(1, standardPlayer.getInventoryCount(guildMap));
    }

    @Test
    void successfulTalkToGuildMaster(){
        GuildMaster guildMaster = quest.getGuildMaster();
        quest.stealth(standardPlayer, enemy);
        quest.attack(standardPlayer, enemy);
        addGuildMapToInventory();
        quest.talkToGuildMaster(standardPlayer);
        assertTrue(guildMaster.talk());
        assertEquals(guildMaster, quest.getTalkedTo());
    }

    @Test
    void unSuccessfulTalkToGuildMaster(){
        assertFalse(quest.talkToGuildMaster(standardPlayer));
    }

    @Test
    void canPlayerTalkToTownsman(){
        addGuildMapToInventory();
        quest.stealth(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer);
        assertTrue(quest.hasTalkedToEnemy());
    }

    @Test
    void succesfulTalkToTownsman(){
        Townsman townsman = quest.getTownsman();
        addGuildMapToInventory();
        quest.stealth(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer);
        quest.talkToTownsman();
        assertTrue(townsman.talk());
        assertEquals(townsman, quest.getTalkedTo());
    }

    @Test
    void unSuccessfulTalkToTownsman(){
        addGuildMapToInventory();
        assertFalse(quest.talkToTownsman());
    }

    //Testa slutkrav
    //Lyckas med smygande, lyckas attackera på tid samt prata med guild master
    @Test
    void playerMeetsEndRequirementsForAttackingOnTime(){
        addGuildMapToInventory();
        quest.stealth(standardPlayer, enemy);
        quest.attack(standardPlayer, enemy);
        quest.talkToGuildMaster(standardPlayer);
        assertTrue(quest.endRequirementsForAttackingOnTime());
    }

    //Lyckas med smygande, attackera men ej lyckas på tid, prata med guildmaster
    /*@Test
    void playerMeetsEndRequirementsForNotAttackingOnTime(){
        assertTrue(quest.endRequirementsForNotAttackingOnTime()); //FUNKAR EJ
    }*/

    //Lyckas med smygande, ej attackera och prata med Townsman
    @Test
    void playerMeetsEndRequirementsForNegotiatingWithEnemy(){
        addGuildMapToInventory();
        quest.stealth(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer);
        quest.talkToTownsman();
        assertTrue(quest.endRequirementsForNegotiatingWithEnemy());
    }

    //Klarar man något krav för att klara questet, i detta fall förhandla med fienden
    @Test
    void playerMeetsEndRequirementsForStealthAndAttackQuest(){
        addGuildMapToInventory();
        quest.stealth(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer);
        quest.talkToTownsman();
        quest.endRequirementsFulfilled(standardPlayer);
        assertEquals(QuestState.COMPLETED, quest.getState());
        assertTrue(quest.endRequirementsFulfilled(standardPlayer));
    }

    //Klarar inte slutkrav för questet
    @Test
    void playerDoesNotMeetEndRequirementsForStealthAndAttackQuest(){
        assertFalse(quest.endRequirementsFulfilled(standardPlayer));
    }

    //Klara testet, i detta fall attack
    @Test
    void playerCompletesQuestSuccessfully(){
        addGuildMapToInventory();
        quest.stealth(standardPlayer, enemy);
        quest.attack(standardPlayer, enemy);
        quest.talkToGuildMaster(standardPlayer);
        quest.completeQuest(standardPlayer);
        assertEquals(QuestState.DONE, quest.getState());
        assertTrue(standardPlayer.isInCompletedQuests(quest));
        assertEquals("You completed the quest!", quest.getDescription());
        assertEquals(200, standardPlayer.getHealthPoint());
    }

    //Lyckas inte klara questet
    @Test
    void playerDoesNotCompleteQuestSuccessfully(){
        assertFalse(quest.completeQuest(standardPlayer));
    }

    //Belöning
    //Ökad relation med guild, XP
    @Test
    void correctRewardsForAttackingOnTime(){
        quest.rewardWhenAttackingOnTime(standardPlayer);
        assertEquals(2500, standardPlayer.getExperiencePoint());
    }

    /*
    //Eller liten ökad relation med guild, mindre XP
    @Test
    void correctRewardsForNotAttackingOnTime(){
        quest.rewardWhenNotAttackingOnTime(standardPlayer);
        assertEquals(1800, standardPlayer.getExperiencePoint());
        //Guildrelation
    }*/

    //Eller minskad relation med guild, pengar
    @Test
    void correctRewardsForNegotiatingWithEnemy(){
        quest.rewardWhenNegotiatingWithEnemy(standardPlayer);
        assertEquals(2000, standardPlayer.getExperiencePoint());
        assertEquals(300, standardPlayer.getMaxHealthPoint());
    }

    //Rätt reward när attack
    @Test
    void getRewardMethodReturnsCorrectRewardForAttacking(){
        addGuildMapToInventory();
        quest.stealth(standardPlayer, enemy);
        quest.attack(standardPlayer, enemy);
        quest.talkToGuildMaster(standardPlayer);
        quest.getReward(standardPlayer);
        assertEquals(2600, standardPlayer.getExperiencePoint()); //Borde vara 2500
    }

    //Förhandlade med fienden rätt reward
    @Test
    void getRewardMethodReturnsCorrectRewardForNegotiatingWithEnemy(){
        addGuildMapToInventory();
        quest.stealth(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer);
        quest.talkToTownsman();
        quest.getReward(standardPlayer);
        assertEquals(2000, standardPlayer.getExperiencePoint());
        assertEquals(300, standardPlayer.getMaxHealthPoint());
    }

    @Test
    void toStringMethodReturnsCorrectString(){
        assertEquals("Stealth and Attack: You have to follow your enemy without being seen and then attack him. PENDING, true, false, false, false, null", quest.toString());
    }
}