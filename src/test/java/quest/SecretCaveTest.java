package quest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import player.Player;
import unit.Bat;

import static org.junit.jupiter.api.Assertions.*;

class SecretCaveTest {

    Player player = new Player("Tank", "Human", 200, 1580);
    SecretCave secretCave = new SecretCave();
    Bat bat = new Bat();
    Bat bat2 = new Bat();
    Bat bat3 = new Bat();
    Bat bat4 = new Bat();
    Bat bat5 = new Bat();

    @Nested
    @DisplayName("Tests where the player explores and finds Secret Cave Quest in Explore and Attack quest")
    class TestsWherePlayerHasFoundMagicPeachInExploreAndAttackQuest{

        //Spelaren har kört Explore and Attack Quest, explorat och hittat rätt koordinater för MagicPeach som lägger detta quest i Available Quests
        @BeforeEach
        void setUp(){
            ExploreAndAttack exploreAndAttack = new ExploreAndAttack();
            player.setCoordinates(new Coordinates(123, 2899));
            exploreAndAttack.explore(player);
        }


        @Test
        void playerMeetsStartRequirementsForSecretCaveQuest(){
            Quest quest = secretCave.getSecretCaveQuestIfInAvailableQuests(player);
            assertTrue(player.isInAvailableQuests(quest));
        }

        @Test
        void playerCanStartSecretCaveQuest(){
            assertTrue(secretCave.startRequirementsFulfilled(player));
        }

        @Test
        void playerStartsSecretCaveQuestSuccessfully(){
            assertTrue(secretCave.startQuest(player));
        }
    }


    @Test
    void playerDoesNotMeetStartRequirementsForSecretCaveQuest(){
        Quest quest = secretCave.getSecretCaveQuestIfInAvailableQuests(player);
        assertFalse(player.isInAvailableQuests(quest));
    }

    @Test
    void playerCantStartSecretCaveQuest(){
        assertFalse(secretCave.startRequirementsFulfilled(player));
    }

    @Test
    void playerStartsSecretCaveQuestUnsuccessfully(){
        assertFalse(secretCave.startQuest(player));
    }

    @Test
    void playerKillOneBatSuccessfullyAndBatsKilledIsIncreasedByOne(){
        bat.getGrounded();
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
        assertEquals(0, bat.getHealthPoint());
        assertEquals(1, secretCave.getBatsKilled());
    }

    @Test
    void playerDiesWhileTryingToKillABat(){
        bat.getGrounded();
        player.setHealthPoint(1);
        secretCave.attackBat(player, bat);
        assertEquals(200, player.getHealthPoint());
    }

    void attack5Bats5TimesEach(){
        bat.getGrounded();
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);

        bat2.getGrounded();
        secretCave.attackBat(player, bat2);
        secretCave.attackBat(player, bat2);
        secretCave.attackBat(player, bat2);
        secretCave.attackBat(player, bat2);
        secretCave.attackBat(player, bat2);

        bat3.getGrounded();
        secretCave.attackBat(player, bat3);
        secretCave.attackBat(player, bat3);
        secretCave.attackBat(player, bat3);
        secretCave.attackBat(player, bat3);
        secretCave.attackBat(player, bat3);

        bat4.getGrounded();
        secretCave.attackBat(player, bat4);
        secretCave.attackBat(player, bat4);
        secretCave.attackBat(player, bat4);
        secretCave.attackBat(player, bat4);
        secretCave.attackBat(player, bat4);

        bat5.getGrounded();
        secretCave.attackBat(player, bat5);
        secretCave.attackBat(player, bat5);
        secretCave.attackBat(player, bat5);
        secretCave.attackBat(player, bat5);
        secretCave.attackBat(player, bat5);
    }

    @Test
    void playerMeetsEndRequirementsForSecretCaveQuest(){
        attack5Bats5TimesEach();
        assertTrue(secretCave.endRequirementsFulfilled(player));
    }

    @Test
    void playerDoesNotMeetEndRequirementsForSecretCaveQuest(){
        assertFalse(secretCave.endRequirementsFulfilled(player));
    }

    @Test
    void playerCompletesSecretCaveQuestSuccessfully(){
        attack5Bats5TimesEach();
        assertTrue(secretCave.completeQuest(player));
    }

    @Test
    void playerCompletesSecretCaveQuestUnsuccessfully(){
        assertFalse(secretCave.completeQuest(player));
    }

    @Test
    void toStringMethodReturnsCorrectString(){
        assertEquals("Secret Cave: You have found the secret cave. What secrets lies within it?. PENDING, false", secretCave.toString());
    }

}