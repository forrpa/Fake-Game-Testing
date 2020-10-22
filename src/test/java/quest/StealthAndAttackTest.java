package quest;

import org.junit.jupiter.api.Test;
import player.Player;
import unit.AttackType;
import unit.Bat;
import unit.Questgiver;
import unit.Wolf;

import static org.junit.jupiter.api.Assertions.*;

class StealthAndAttackTest {

    Player standardPlayer = new Player("Tank", "Human", 200, 1500);
    Player player;
    GuildMap guildMap = new GuildMap();
    StealthAndAttack quest = new StealthAndAttack();
    Questgiver questGiver = new Questgiver("Boris Johnson", quest);
    Wolf enemy = new Wolf();

    public void addGuildMapToInventory(){
        standardPlayer.addToInventory(guildMap);
    }

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
    }

    //Har inte tillräckligt med experience för att starta questet
    @Test
    void playerDoesNotMeetStartRequirementsForStealthAndAttackQuest(){
        player = new Player("Human", "Damage", 200, 1499);
        assertFalse(quest.startRequirementsFulfilled(player));
    }

    //Testa om det går att starta questet
    @Test
    void playerStartsQuestSuccessfully() {
        addGuildMapToInventory();
        quest.startQuest(standardPlayer);
        assertEquals(QuestState.IN_PROGRESS, quest.getState());
        assertEquals("Your first job is to follow the enemy without being seen.", quest.getDescription());
    }

    //Metoden resetQuest lyckas
    @Test
    void questResetsSuccessfully(){
        standardPlayer.setHealthPoint(0);
        quest.resetQuest(standardPlayer);
        assertEquals(200, standardPlayer.getHealthPoint());
        assertFalse(quest.hasAttacked());
        assertFalse(quest.isDiscovered());
    }

    //Testa om man lyckas med stealth
    @Test
    void stealthSucceeded(){
        quest.stealth(standardPlayer, enemy);
        assertFalse(quest.isDiscovered());
        assertEquals("You succeeded not being seen, now you have to decide if you want to kill your enemy or negotiate with it.", quest.getDescription());
        assertTrue(quest.stealth(standardPlayer, enemy));
    }

    //Testa om man inte lyckas med stealth (blir upptäckt)
    @Test
    void stealthNotSucceeded(){
        standardPlayer.setHealthPoint(1);
        Bat bat = new Bat("Bat", 10, 5, 10, AttackType.ICE, AttackType.FIRE);
        bat.fillHealthBar();
        assertFalse(quest.stealth(standardPlayer, bat));
        assertTrue(quest.resetQuest(standardPlayer));
    }

    //Testa krav för att starta attack eller förhandla med fienden
    @Test
    void canPlayerStartAttackOrNegotiatingWithEnemy(){
        assertTrue(quest.stealth(standardPlayer, enemy));
    }

    /*//Tiden tar slut i attack
    @Test
    void playerRunsOutOfTimeInBattle(){
        //attack = new Attack(0, standardPlayer, enemy);
        assertEquals(0, attack.getTimer());
    }*/

    //Fiende dör i attack och man lyckas
    @Test
    void successfulAttack(){
        quest.attack(standardPlayer, enemy);
        assertTrue(quest.attack(standardPlayer, enemy));
        assertEquals("You succeeded killing your enemy on time. Go talk to the Guild Leader for your reward!", quest.getDescription());
    }

    //Testa att dö i attack
    @Test
    void unSuccessfulAttack(){
        Bat bat = new Bat();
        bat.getGrounded(); //Funkar ej
        quest.attack(standardPlayer, bat);
        assertFalse(quest.attack(standardPlayer, bat));
        assertTrue(quest.resetQuest(standardPlayer));
    }

    //Testa att förhandla med fienden
    @Test
    void successfulNegotiatingWithEnemy(){
        addGuildMapToInventory();
        quest.negotiateWithEnemy(standardPlayer,enemy);
        assertTrue(quest.hasTalkedToEnemy());
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
        quest.attack(standardPlayer, enemy);
        addGuildMapToInventory();
        quest.talkToQuestGiver(standardPlayer, enemy, questGiver);
        //assertTrue(questGiver.talkToPlayer()); Fixa metod
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
        addGuildMapToInventory();
        quest.talkToQuestGiver(standardPlayer, enemy, questGiver);
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
        quest.talkToTownsman(standardPlayer, enemy);
        assertTrue(quest.endRequirementsForNegotiatingWithEnemy());
    }

    //Klarar man något krav för att klara questet, i detta fall förhandla med fienden
    @Test
    void playerMeetsEndRequirementsForStealthAndAttackQuest(){
        addGuildMapToInventory();
        quest.talkToTownsman(standardPlayer, enemy);
        quest.endRequirementsFulfilled(standardPlayer);
        assertEquals(QuestState.COMPLETED, quest.getState());
        assertTrue(quest.endRequirementsFulfilled(standardPlayer));
    }

    //Klara testet, i detta fall attack
    @Test
    void playerCompletesQuestSuccessfully(){
        addGuildMapToInventory();
        quest.talkToQuestGiver(standardPlayer, enemy, questGiver);
        quest.questCompleted(standardPlayer);
        assertEquals(QuestState.DONE, quest.getState());
        assertEquals("You completed the quest!", quest.getDescription());
        assertEquals(200, standardPlayer.getHealthPoint());
    }

    //Belöning
    //Ökad relation med guild, XP
    @Test
    void correctRewardsForAttackingOnTime(){
        quest.rewardWhenAttackingOnTime(standardPlayer);
        assertEquals(2500, standardPlayer.getExperiencePoint());
        //Relation med guild
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
        //assertTrue(player.isInInventory("money"));//Ökning av pengar
        //Minus på guildrelation
    }

    //Förhandlade med fienden rätt reward
    @Test
    void getRewardReturnsCorrectRewardForAttacking(){
        addGuildMapToInventory();
        quest.talkToQuestGiver(standardPlayer, enemy, questGiver);
        quest.getReward(standardPlayer);
        assertEquals(2500, standardPlayer.getExperiencePoint());
        //assertEquals(300, standardPlayer.getMaxHealthPoint());
    }
}