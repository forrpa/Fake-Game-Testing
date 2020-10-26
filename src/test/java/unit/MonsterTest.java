package unit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import item.*;
import edible.*;
import player.Player;
import weapon.*;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    private final static int STANDARD_BAT_HEALTH = 5;
    private final static int STANDARD_BAT_ATTACKPOWER = 2;
    private final static int STANDARD_BAT_EXPERIENCEPOINTS = 50;
    private final static int STANDARD_WOLF_HEALTH = 8;
    private final static int STANDARD_WOLF_ATTACKPOWER = 3;
    private final static int STANDARD_WOLF_EXPERIENCEPOINTS = 100;


    private Player createPlayer(){
        return new Player("Paladin", "Orc", 10, 50);
    }

    @Test
    void batNameAndHealthSetByConstructor(){
        //Set-up
        Monster bat = new Bat("Jabba the Bat", STANDARD_BAT_HEALTH, STANDARD_BAT_ATTACKPOWER, STANDARD_BAT_EXPERIENCEPOINTS, null, null);

        //Assert that name and health is set correct;
        assertEquals("Jabba the Bat", bat.getName());
        assertEquals(STANDARD_BAT_HEALTH, bat.getHealthPoint());
    }
    @Test
    void batNameSetTo556ByConstructorThrowsIllegalArgumentException(){
        //Constructor throws error for numbers in name
        assertThrows(IllegalArgumentException.class, () -> new Bat("556", STANDARD_BAT_HEALTH, STANDARD_BAT_ATTACKPOWER, STANDARD_BAT_EXPERIENCEPOINTS, null, null));
    }
    @Test
    void toStringReturnsCorrectString(){
        Monster bat = new Bat();
        assertEquals("Name: Bat, Max Health: 5, Current Health: 5, Attack Power: 2, Resistance: null, Weakness null.", bat.toString());
    }
    @Test
    void toStringReturnsCorrectStringWithItems(){
        Item item = new Ingredient("Bat wing", "A wing from a bat.");
        ArrayList<Item> batItems = new ArrayList<>();
        batItems.add(item);
        Monster bat = new Bat(batItems);
        assertEquals("Name: Bat, Max Health: 5, Current Health: 5, Attack Power: 2, Resistance: null, Weakness null, Loot: Bat wing.", bat.toString());
    }
    @Test
    void setBatHealthToOverMaxAttributeThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Bat("556", 50001, STANDARD_BAT_ATTACKPOWER, STANDARD_BAT_EXPERIENCEPOINTS, null, null));
    }
    @Test
    void batMonsterAttackWolfSuccessful(){
        //Set-up
        Monster bat = new Bat();
        Monster wolf = new Wolf();

        //Assert that wolf has full health
        assertEquals(STANDARD_WOLF_HEALTH, wolf.getHealthPoint());

        //Assert that attack is successful and health is deducted
        assertTrue(bat.attack(wolf));
        assertEquals(STANDARD_WOLF_HEALTH-STANDARD_BAT_ATTACKPOWER, wolf.getHealthPoint());
    }
    @Test
    void wolfMonsterAttackBatUnsuccessful(){
        //Set-up
        Monster bat = new Bat();
        Monster wolf = new Wolf();

        //Assert that attack is unsuccessful
        assertFalse(wolf.attack(bat));
    }
    @Test
    void wolfMonsterAttackOtherWolfForStandardWolfDamageOtherWolfHas5HealthRemaining(){
        //Set-up
        Monster wolf = new Wolf();
        Monster otherWolf = new Wolf();

        //Assert that wolf can attack otherWolf, has right health left and is not dead
        assertTrue(wolf.attack(otherWolf));
        assertEquals(STANDARD_WOLF_HEALTH-STANDARD_WOLF_ATTACKPOWER, otherWolf.getHealthPoint());
        assertTrue(otherWolf.isAlive());
    }
    @Test
    void wolfMonsterAttackOtherWolfForStandardWolfDamageThriceOtherWolfHas0HealthRemaining(){
        //Set-up
        Monster wolf = new Wolf();
        Monster otherWolf = new Wolf();

        //Assert that wolf can attack right amount to make otherWolf health 0 and that health is stuck at 0
        for (int i = 0;i < 3; i++){
            assertTrue(wolf.attack(otherWolf));
        }
        assertEquals(0, otherWolf.getHealthPoint());
        assertFalse(otherWolf.isAlive());
    }
    @Test
    void wolfMonsterAttackOtherWolfWith0HealthThrowsIllegalStateException(){
        //Set-up
        Monster wolf = new Wolf();
        Monster otherWolf = new Wolf("Wolf", 0, 0, 0,null, null);

        //Asserts that wolf cant attack another monster that has 0 health
        assertThrows(IllegalStateException.class, () -> wolf.attack((otherWolf)));
    }
    @Test
    void wolfWith0HealthAttacksOtherWolfThrowsIllegalStateException(){
        //Set-up
        Monster wolf = new Wolf("Wolf", 0, 4, 0,null, null);
        Monster otherWolf = new Wolf();

        //Asserts that a dead monster cant attack
        assertThrows(IllegalStateException.class, () -> wolf.attack((otherWolf)));
    }
    @Test
    void batHasItemPotionNotLootableBecauseBatIsAliveIllegalStateException(){
        //Set-up
        Item item = new Ingredient("Bat wing", "A wing from a bat.");
        ArrayList<Item> batItems = new ArrayList<>();
        batItems.add(item);
        Monster bat = new Bat(batItems);

        //Asserts that a living monster can't get looted
        assertTrue(bat.isAlive());
        assertThrows(IllegalStateException.class, () -> bat.getLooted());
    }
    @Test
    void batHasItemsPotionAndSwordLootedSuccessfully(){
        //Set-up
        Item potion = new Potion("Potion", "A test potion", 0, 5, 0);
        Item batWing = new Ingredient("Bat wing", "A wing from a bat.");
        ArrayList<Item> batItems = new ArrayList<>();
        batItems.add(potion);
        batItems.add(batWing);
        ArrayList<Item> lootedItems = new ArrayList<>();
        Monster bat = new Bat("Bat", 0, 0,0, batItems, null, null);

        //Asserts that inventory is empty and bat is dead
        assertTrue(lootedItems.isEmpty());
        assertFalse(bat.isAlive());

        //Asserts that the items gets put in inventory
        lootedItems.addAll(bat.getLooted());
        assertTrue(lootedItems.contains(potion)&& lootedItems.contains(batWing));
        assertEquals(2, lootedItems.size());
    }
    @Test
    void batGetsKilledByOtherBatAndHasItemsPotionAndSwordLootedSuccessfullyByPlayerAndLootedAgainUnsuccessfullyIllegalStateException(){
        //Set-up
        Player player = createPlayer();
        Item batEye = new Ingredient("Bat eye", "An eye from a bat.");
        Item batWing = new Ingredient("Bat wing", "A wing from a bat.");
        ArrayList<Item> batItems = new ArrayList<>();
        batItems.add(batWing);
        batItems.add(batEye);

        Monster bat = new Bat(batItems);
        Monster otherBat = new Bat();

        //Check that *inventory* is empty and bat is alive at start
        assertTrue(bat.isAlive());

        //Kill the bat with the items
        for(int i = 0; i < 3; i++){
            otherBat.attack(bat);
        }

        //Ensure bat is dead
        assertFalse(bat.isAlive());

        //Loot the items
        player.loot(bat);

        //Check that *inventory* contains the looted items
        assertEquals(1,player.getCupboard().getCount(batEye));
        assertEquals(1,player.getCupboard().getCount(batWing));



        //Get IllegalStateException when trying to loot again due to no items left
        assertThrows(IllegalStateException.class, () -> bat.getLooted());
    }
    @Test
    void PlayerAttacksWolfAndGains100ExperiencePoints(){
        Player player = createPlayer();
        Monster wolf = new Wolf();
        for(int i = 0; i < 8; i++){
            assertTrue(player.attack(wolf));
        }
        assertEquals(0, wolf.getHealthPoint());
        assertEquals(150, player.getExperiencePoint());
    }

    @Test
    void PlayerAttacksBatWithRangedAttackAndReturnsTrue(){
        Player player = createPlayer();
        Monster bat = new Bat();
        Attack rangedAttack = new Attack(player.getAttackPower(), true);
        assertTrue(player.attack(bat, rangedAttack));
        assertEquals(4, bat.getHealthPoint());
    }
    //Need tests for ranged attacks and update player looting
    @Test
    void PlayerKillsTwoWolvesWithDifferentItemsAndLootsThemItemsAreInInventory(){
        Player player = createPlayer();
        Ingredient wolfHair = new Ingredient("Wolf Hair", "Hair from a wolf");
        ArrayList wolfOneLoot = new ArrayList();
        wolfOneLoot.add(wolfHair);
        Ingredient wolfEye = new Ingredient("Wolf Eye", "An eye from a wolf");
        ArrayList wolfTwoLoot = new ArrayList();
        wolfTwoLoot.add(wolfEye);
        Monster wolf = new Wolf(wolfOneLoot);
        Monster wolf2 = new Wolf("One-eyed Wolf", STANDARD_WOLF_HEALTH, STANDARD_WOLF_ATTACKPOWER, STANDARD_WOLF_EXPERIENCEPOINTS, wolfTwoLoot, null, null);

        player.setAttackPower(10);
        assertTrue(player.attack(wolf));
        assertTrue(player.attack(wolf2));

        player.loot(wolf);
        player.loot(wolf2);
        assertEquals(1, player.getCupboard().getCount(wolfHair));
        assertEquals(1, player.getCupboard().getCount(wolfEye));
    }

    @Test
    void PlayerGetExperiencePointsZeroBecauseEnemyIsAlive(){
        Player player = createPlayer();
        Monster bat = new Bat();
        player.increaseExperiencePoint(bat.getExperiencePoints());
    }
}