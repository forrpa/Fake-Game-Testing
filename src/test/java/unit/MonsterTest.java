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
        Item sword = new WidowsWail();
        ArrayList<Item> batItems = new ArrayList<>();
        batItems.add(potion);
        batItems.add(sword);
        ArrayList<Item> lootedItems = new ArrayList<>();
        Monster bat = new Bat("Bat", 0, 0,0, batItems, null, null);

        //Asserts that inventory is empty and bat is dead
        assertTrue(lootedItems.isEmpty());
        assertFalse(bat.isAlive());

        //Asserts that the items gets put in inventory
        lootedItems.addAll(bat.getLooted());
        assertTrue(lootedItems.contains(potion)&& lootedItems.contains(sword));
        assertEquals(2, lootedItems.size());
    }
    @Test
    void batGetsKilledAndHasItemsPotionAndSwordLootedSuccessfullyAndLootedAgainUnsuccessfullyIllegalStateException(){
        //Set-up
        Item potion = new Ingredient("Bat wing", "A wing from a bat.");
        Item sword = new WidowsWail();
        ArrayList<Item> batItems = new ArrayList<>();
        batItems.add(potion);
        batItems.add(sword);
        ArrayList<Item> lootedItems = new ArrayList<>();
        Monster bat = new Bat(batItems);
        Monster otherBat = new Bat();

        //Check that *inventory* is empty and bat is alive at start
        assertTrue(lootedItems.isEmpty());
        assertTrue(bat.isAlive());

        //Kill the bat with the items
        for(int i = 0; i < 3; i++){
            otherBat.attack(bat);
        }

        //Ensure bat is dead
        assertFalse(bat.isAlive());

        //Loot the items
        lootedItems.addAll(bat.getLooted());

        //Check that *inventory* contains the looted items
        assertTrue(lootedItems.contains(potion)&& lootedItems.contains(sword));
        assertEquals(2, lootedItems.size());

        //Get IllegalStateException when trying to loot again due to no items left
        assertThrows(IllegalStateException.class, () -> bat.getLooted());
    }
    @Test
    void PlayerAttacksWolfAndGains100ExperiencePoints(){
        Player player = new Player("Paladin", "Orc", 10, 50);
        Monster wolf = new Wolf();
        assertTrue(player.attack(wolf));
        assertTrue(player.attack(wolf));
        assertTrue(player.attack(wolf));
        assertTrue(player.attack(wolf));
        assertTrue(player.attack(wolf));
        assertTrue(player.attack(wolf));
        assertTrue(player.attack(wolf));
        assertTrue(player.attack(wolf));
        assertEquals(0, wolf.getHealthPoint());
        assertEquals(150, player.getExperiencePoint());
    }
}