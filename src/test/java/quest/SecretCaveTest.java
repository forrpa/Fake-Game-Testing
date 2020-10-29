package quest;

import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import player.Player;
import unit.Bat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SecretCaveTest {

    Player player = new Player("Tank", "Human", 200, 1580);
    SecretCave secretCave = new SecretCave();
    BatFang batFang = new BatFang();

    ArrayList<Item> batFangs = new ArrayList<>(){{
        add(batFang);
    }};

    Bat bat = new Bat(batFangs);
    Bat bat2 = new Bat(batFangs);
    Bat bat3 = new Bat(batFangs);
    Bat bat4 = new Bat(batFangs);
    Bat bat5 = new Bat(batFangs);

    //Alla test som kräver att spelaren har hittat detta quest i Explore and Attack questet
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

        //Spelaren möter startkraven för att starta questet
        @Test
        void playerMeetsStartRequirementsForSecretCaveQuest(){
            secretCave.startRequirementsFulfilled(player);
            Quest quest = secretCave.getSecretCaveQuestIfInAvailableQuests(player);
            assertEquals(QuestState.UNLOCKED, secretCave.getState());
            assertTrue(player.getQuestLog().isInAvailableQuests(quest));
        }

        //Spelaren kan starta questet
        @Test
        void playerCanStartSecretCaveQuest(){
            assertTrue(secretCave.startRequirementsFulfilled(player));
        }

        //Spelaren startar questet lyckat
        @Test
        void playerStartsSecretCaveQuestSuccessfully(){
            secretCave.startQuest(player);
            assertEquals(QuestState.IN_PROGRESS, secretCave.getState());
            assertTrue(player.getQuestLog().isInCurrentQuests(secretCave));
            assertEquals("First you have to kill and loot 5 bats. They hide around the cave. Be careful.", secretCave.getDescription());
        }
    }

    //Spelaren har inte startkraven för att kunna starta questet
    @Test
    void playerDoesNotMeetStartRequirementsForSecretCaveQuest(){
        Quest quest = secretCave.getSecretCaveQuestIfInAvailableQuests(player);
        assertFalse(player.getQuestLog().isInAvailableQuests(quest));
    }

    //Spelaren kan inte starta questet
    @Test
    void playerCantStartSecretCaveQuest(){
        assertFalse(secretCave.startRequirementsFulfilled(player));
    }

    //Spelaren lyckas inte starta questet
    @Test
    void playerStartsSecretCaveQuestUnsuccessfully(){
        assertFalse(secretCave.startQuest(player));
    }

    //Spelaren lyckas döda en Bat, genom att attackera den 5 gånger
    @Test
    void playerKillOneBatSuccessfully(){
        attack1Bat5TimesUntilItDies();
        assertEquals(0, bat.getHealthPoint());
        assertEquals(1, secretCave.getBatsLooted());
        assertEquals(1, player.getInventoryCount(batFang));
    }

    //Spelaren dödar tillräckligt med bats (5) och beskrivningen ändras då till att man ska hitta häxan
    @Test
    void playerKillsFiveBatsSuccessfully(){
        attack5Bats5TimesEachUntilTheyDie();
        assertEquals(0, bat.getHealthPoint());
        assertEquals(0, bat2.getHealthPoint());
        assertEquals(0, bat3.getHealthPoint());
        assertEquals(0, bat4.getHealthPoint());
        assertEquals(0, bat5.getHealthPoint());
        assertEquals(5, secretCave.getBatsLooted());
        assertEquals(5, player.getInventoryCount(batFang));
        assertEquals("You have looted five bats successfully. Now it's time to find the witch. She lurks somewhere in this cave!", secretCave.getDescription());
    }

    //Spelaren dör när den attackerar en Bat, då fylls spelarens Health på till max och questet startas om
    @Test
    void playerDiesWhileTryingToKillABat(){
        bat.getGrounded();
        player.setHealthPoint(1);
        secretCave.attackBat(player, bat);
        assertEquals(200, player.getHealthPoint());
    }

    //Spelaren lyckas hitta häxan och får en Super Potion. Inventory ska inte innehålla några BatFangs (häxan tog dom), men Cupboard ska innehålla SuperPotion
    @Test
    void playerFindsWitchAndGetPotion(){
        attack5Bats5TimesEachUntilTheyDie();
        player.setCoordinates(new Coordinates(514, 112));
        secretCave.visitWitch(player);
        assertThrows(NullPointerException.class, () -> {
            player.getInventoryCount(new BatFang());
        });
        assertTrue(player.getCupboard().isInInventory(new SuperPotion()));
        assertTrue(secretCave.isWitchVisited());
        assertEquals("You found the wicked witch and succeeded trading the bat loot for a powerful potion.", secretCave.getDescription());
    }

    //Spelaren har bara en bat fang, men behöver 5. Får ingen Super Potion av häxan.
    @Test
    void playerFindsWitchButDoesNotGetPotion(){
        attack1Bat5TimesUntilItDies();
        player.setCoordinates(new Coordinates(514, 112));
        secretCave.visitWitch(player);
        assertFalse(player.getCupboard().isInInventory(new SuperPotion()));
    }

    //Spelaren hittar inte häxan eftersom den har fel koordinater
    @Test
    void playerDoesNotFindWitch(){
        player.setCoordinates(new Coordinates(512, 210));
        assertFalse(secretCave.visitWitch(player));
    }

    //Spelaren möter slutkraven för questet, genom att lyckas med att besöka häxan
    @Test
    void playerMeetsEndRequirementsForSecretCaveQuest(){
        attack5Bats5TimesEachUntilTheyDie();
        player.setCoordinates(new Coordinates(514, 112));
        secretCave.visitWitch(player);
        assertTrue(secretCave.endRequirementsFulfilled(player));
    }

    //Spelaren möter inte slutkraven för questet
    @Test
    void playerDoesNotMeetEndRequirementsForSecretCaveQuest(){
        assertFalse(secretCave.endRequirementsFulfilled(player));
    }

    //Spelaren lyckas klara questet genom att göra det som uppfyller slutkraven
    @Test
    void playerCompletesSecretCaveQuestSuccessfully(){
        attack5Bats5TimesEachUntilTheyDie();
        player.setCoordinates(new Coordinates(514, 112));
        secretCave.visitWitch(player);
        assertTrue(secretCave.completeQuest(player));
        assertEquals(QuestState.DONE, secretCave.getState());
        assertTrue(player.getQuestLog().isInCompletedQuests(secretCave));
        assertEquals("You completed the quest!", secretCave.getDescription());
        assertEquals(6830, player.getExperiencePoint());
        assertEquals(200, player.getHealthPoint());
    }

    //Spelaren lyckas inte klara questet
    @Test
    void playerCompletesSecretCaveQuestUnsuccessfully(){
        assertFalse(secretCave.completeQuest(player));
    }

    //toString returnerar rätt sträng
    @Test
    void toStringMethodReturnsCorrectString(){
        assertEquals("Secret Cave: You have found the secret cave. What secrets lies within it? State: PENDING, Mandatory: false, Has visited witch: false, Bats looted: 0", secretCave.toString());
    }

    void attack1Bat5TimesUntilItDies(){
        bat.getGrounded();
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
        secretCave.attackBat(player, bat);
    }

    //Spelaren attackerar fem olika Bats fem gånger var, så att de dör
    void attack5Bats5TimesEachUntilTheyDie(){
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

}