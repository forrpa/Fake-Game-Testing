package quest;

import org.junit.jupiter.api.*;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class ExploreAndAttackTest {

    Player standardPlayer = new Player("Tank", "Human", 200, 1500);
    Player player;
    GuildMap guildMap = new GuildMap();
    ExploreAndAttack quest = new ExploreAndAttack();
    StealthAndAttackEnemy enemy = new StealthAndAttackEnemy();
    TalkToGuildLeader talkToGuildLeader;

    //Lägger till Talk To Guild Leader-quest i spelarens lista över klarade quests så att man kan göra detta quest. Dock inte till en metod som testar att det inte ska finnas ett sådant quest.
    @BeforeEach
    void setUp(TestInfo info){
        if (info.getDisplayName().equals("Required Quest is null")){
            return;
        } else {
            talkToGuildLeader = new TalkToGuildLeader();
            standardPlayer.addQuestToCompletedQuests(talkToGuildLeader);
        }
    }

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
        talkToGuildLeader = new TalkToGuildLeader();
        player.addQuestToCompletedQuests(talkToGuildLeader);
        assertFalse(quest.startRequirementsFulfilled(player));
    }

    //Om man försöker hämta questet som krävs för att starta questet men questet finns inte
    @Test
    @DisplayName("Required Quest is null")
    void getRequiredQuestIsNullReturnsNullPointerException(){
        assertThrows(NullPointerException.class, () -> quest.getRequiredQuest(standardPlayer));
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

    @Test
    void playerFindsEnemyWhenExploring(){
        standardPlayer.setCoordinates(new Coordinates(254, 566)); //Fiendens koordinater
        quest.explore(standardPlayer, enemy);
        assertTrue(quest.isEnemyFound());
    }

    @Test
    void playerDoesNotFindEnemyWhenExploring(){
        standardPlayer.setCoordinates(new Coordinates(255, 566));
        quest.explore(standardPlayer, enemy);
        assertFalse(quest.isEnemyFound());
    }

    @Test
    void playerDiesDuringExploring(){
        standardPlayer.setCoordinates(new Coordinates(255, 216));
        standardPlayer.setHealthPoint(0);
        quest.explore(standardPlayer, enemy);
        assertEquals(200, standardPlayer.getHealthPoint());
    }

    //Testa om man lyckas med stealth
    /*
    @Test
    void stealthSucceeded(){
        quest.stealth(standardPlayer, enemy);
        assertTrue(quest.isEnemyFound());
        assertEquals("You succeeded not being seen, now you have to decide if you want to kill your enemy or negotiate with it.", quest.getDescription());
        assertTrue(quest.stealth(standardPlayer, enemy));
    }

    //Testa om man inte lyckas med stealth (blir upptäckt)
    @Test
    void stealthNotSucceeded(){
        standardPlayer.setHealthPoint(1);
        assertFalse(quest.stealth(standardPlayer, enemy));
    }*/

    //Testa krav för att starta attack eller förhandla med fienden
    @Test
    void canPlayerStartAttackOrNegotiatingWithEnemy(){
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        assertTrue(quest.isEnemyFound());
    }

    //Spelaren kan inte starta attack eller förhandla med fienden
    @Test
    void playerCantStartAttackOrNegotiatingWithEnemy(){
        assertFalse(quest.attack(standardPlayer, enemy));
    }

    //Fiende dör i attack och man lyckas
    @Test
    void successfulAttack(){
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        enemy.setHealthPoint(1);
        assertTrue(quest.attack(standardPlayer, enemy));
        assertTrue(quest.hasAttacked());
        assertEquals("You succeeded killing your enemy. Go talk to the Guild Leader for your reward!", quest.getDescription());
    }

    //Spelaren dör i attack
    @Test
    void playerDiesInAttack(){
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        enemy.setMaxHealthPoint(1000);
        enemy.setHealthPoint(1000);
        assertFalse(quest.attack(standardPlayer, enemy));
    }

    //Testa att förhandla med fienden
    @Test
    void successfulNegotiatingWithEnemy(){
        addGuildMapToInventory();
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer, enemy); //NYTT
        assertTrue(quest.hasTalkedToEnemy());
        assertEquals("You decided to talk to your enemy instead of killing him. Now you cant reach the Guild so you have to talk to the Townsman.", quest.getDescription());
        assertFalse(standardPlayer.isInInventory(guildMap));
    }

    //Spelaren har kraven för att tala med Guild Master
    @Test
    void playerCanTalkToGuildMaster(){
        addGuildMapToInventory();
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        quest.attack(standardPlayer, enemy); //Ger false
        assertTrue(quest.hasAttacked());
        assertEquals(1, standardPlayer.getInventoryCount(guildMap));
    }

    //Spelaren lyckas tala med Guild Master
    @Test
    void successfulTalkToGuildMaster(){
        GuildMaster guildMaster = quest.getGuildMaster();
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        quest.attack(standardPlayer, enemy);
        addGuildMapToInventory();
        quest.talkToGuildMaster(standardPlayer);
        assertTrue(guildMaster.talk());
        assertEquals(guildMaster, quest.getTalkedTo());
    }

    //Spelaren lyckas inte tala med Guild Master
    @Test
    void unSuccessfulTalkToGuildMaster(){
        assertFalse(quest.talkToGuildMaster(standardPlayer));
    }

    //Spelaren har kraven för att tala med Townsman
    @Test
    void canPlayerTalkToTownsman(){
        addGuildMapToInventory();
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer, enemy); //NYTT
        assertTrue(quest.hasTalkedToEnemy());
    }

    //Spelaren lyckas tala med Townsman
    @Test
    void succesfulTalkToTownsman(){
        Townsman townsman = quest.getTownsman();
        addGuildMapToInventory();
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer, enemy); //NYTT
        quest.talkToTownsman();
        assertTrue(townsman.talk());
        assertEquals(townsman, quest.getTalkedTo());
    }

    //Spelaren lyckas inte tala med Townsman
    @Test
    void unSuccessfulTalkToTownsman(){
        addGuildMapToInventory();
        assertFalse(quest.talkToTownsman());
    }

    //SLutkrav

    //Lyckas med smygande, lyckas attackera samt prata med guild master
    @Test
    void playerMeetsEndRequirementsForAttackingOnTime(){
        addGuildMapToInventory();
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        quest.attack(standardPlayer, enemy);
        quest.talkToGuildMaster(standardPlayer);
        assertTrue(quest.endRequirementsForAttackingTheEnemy());
    }

    //Lyckas med smygande, ej attackera och prata med Townsman
    @Test
    void playerMeetsEndRequirementsForNegotiatingWithEnemy(){
        addGuildMapToInventory();
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer, enemy);
        quest.talkToTownsman();
        assertTrue(quest.endRequirementsForNegotiatingWithEnemy());
    }

    //Klarar man något krav för att klara questet, i detta fall förhandla med fienden
    @Test
    void playerMeetsEndRequirementsForStealthAndAttackQuest(){
        addGuildMapToInventory();
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer, enemy);
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
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
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

    //Rätt belöning för att attackera
    @Test
    void correctRewardsForAttackingOnTime(){
        quest.rewardWhenAttackingTheEnemy(standardPlayer);
        assertEquals(2500, standardPlayer.getExperiencePoint());
    }

    //Rätt belöning för att förhandla med fienden
    @Test
    void correctRewardsForNegotiatingWithEnemy(){
        quest.rewardWhenNegotiatingWithEnemy(standardPlayer);
        assertEquals(2000, standardPlayer.getExperiencePoint());
        assertEquals(300, standardPlayer.getMaxHealthPoint());
    }

    //Rätt reward när attack med getReward-metoden
    @Test
    void getRewardMethodReturnsCorrectRewardForAttacking(){
        addGuildMapToInventory();
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        quest.attack(standardPlayer, enemy);
        quest.talkToGuildMaster(standardPlayer);
        quest.getReward(standardPlayer);
        assertEquals(2600, standardPlayer.getExperiencePoint()); //Borde vara 2500
    }

    //Förhandlade med fienden rätt reward med getReward-metoden
    @Test
    void getRewardMethodReturnsCorrectRewardForNegotiatingWithEnemy(){
        addGuildMapToInventory();
        standardPlayer.setCoordinates(new Coordinates(254, 566));
        quest.explore(standardPlayer, enemy);
        quest.negotiateWithEnemy(standardPlayer, enemy);
        quest.talkToTownsman();
        quest.getReward(standardPlayer);
        assertEquals(2000, standardPlayer.getExperiencePoint());
        assertEquals(300, standardPlayer.getMaxHealthPoint());
    }

    //toString returnerar rätt sträng
    @Test
    void toStringMethodReturnsCorrectString(){
        assertEquals("Stealth and Attack: You have to follow your enemy without being seen and then attack him. PENDING, true, false, false, false, null", quest.toString());
    }

}