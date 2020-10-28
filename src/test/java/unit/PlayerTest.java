package unit;

import org.junit.jupiter.api.Test;
import player.Player;
import quest.Quest;
import quest.TalkToGuildLeader;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void playerWithTenHealthGetsHealthDecreasedByFive(){
        Player player = new Player("Warrior", "Orc", 10, 0);
        player.decreaseHealth(5);
        assertEquals(5, player.getHealthPoint());
    }
    @Test
    void playerWith55000HealthThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Player("Warrior", "Orc", 55000, 0));
    }
    @Test
    void playerWith100HealthGetsMaxHealthIncreasedWith50(){
        Player player = new Player("Warrior", "Orc", 100, 0);
        player.increaseMaxHealthPoint(50);
        assertEquals(150, player.getMaxHealthPoint());
        assertEquals(100, player.getHealthPoint());
    }
    @Test
    void playerWith100HealthGetsDamagedThreeHealthAndGetsHealthIncreasedByEightBackTo100(){
        Player player = new Player("Warrior", "Orc", 100, 0);
        Monster wolf = new Wolf();
        assertTrue(wolf.attack(player));
        assertEquals(97, player.getHealthPoint());
        player.increaseHealth(8);
        assertEquals(player.getMaxHealthPoint(),player.getHealthPoint());
    }
    @Test
    void playerWith100HealthGetsDamagedThreeHealthThreeTimesAndGetsHealthRestoredToMax(){
        Player player = new Player("Warrior", "Orc", 100, 0);
        Monster wolf = new Wolf();
        assertTrue(wolf.attack(player));
        assertEquals(97, player.getHealthPoint());
        player.increaseHealth(8);
        assertEquals(player.getMaxHealthPoint(),player.getHealthPoint());
    }
    @Test
    void playerAttacksBatAndMisses(){
        Player player = new Player("Warrior", "Orc", 100, 0);
        Monster bat = new Bat();
        assertFalse(player.attack(bat));
    }
    @Test
    void playerAttacksDeadBatThrowsIllegalStateException(){
        Player player = new Player("Warrior", "Orc", 100, 0);
        Monster bat = new Bat();
        bat.setHealthPoint(0);
        assertFalse(bat.isAlive());
        assertThrows(IllegalStateException.class, () -> player.attack(bat));
    }
    @Test
    void deadPlayerAttacksBatThrowsIllegalStateException(){
        Player player = new Player("Warrior", "Orc", 100, 0);
        Monster bat = new Bat();
        player.setHealthPoint(0);
        assertTrue(bat.isAlive());
        assertFalse(player.isAlive());
        assertThrows(IllegalStateException.class, () -> player.attack(bat));
    }
    @Test
    void playerTakesNullQuestFromQuestgiverThrowsIllegalArgumentException(){
        Player player = new Player("Warrior", "Orc", 100, 0);
        Quest quest = null;
        Questgiver questgiver = new Questgiver("Quest Giver", new TalkToGuildLeader());
        assertThrows(IllegalArgumentException.class, () -> player.takeQuest(questgiver, quest));
    }
    @Test
    void playerTakesQuestFromNullQuestgiverThrowsIllegalArgumentException(){
        Player player = new Player("Warrior", "Orc", 100, 0);
        Quest quest = new TalkToGuildLeader();
        Questgiver questgiver = null;
        assertThrows(IllegalArgumentException.class, () -> player.takeQuest(questgiver, quest));
    }
    @Test
    void playerTakesAllQuestsFromNullQuestgiverThrowsIllegalArgumentException(){
        Player player = new Player("Warrior", "Orc", 100, 0);
        Questgiver questgiver = null;
        assertThrows(IllegalArgumentException.class, () -> player.takeAllQuests(questgiver));
    }
}
