package unit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    private final static int STANDARD_BAT_HEALTH = 5;
    private final static int STANDARD_BAT_ATTACKPOWER = 2;
    private final static int STANDARD_WOLF_HEALTH = 8;
    private final static int STANDARD_WOLF_ATTACKPOWER = 3;

    @Test
    void batNameAndHealthSetByConstructor(){
        //Set-up
        Monster bat = new Bat("Jabba the Bat", STANDARD_BAT_HEALTH, STANDARD_BAT_ATTACKPOWER, null, null);

        //Assert that name and health is set correct;
        assertEquals("Jabba the Bat", bat.getName());
        assertEquals(STANDARD_BAT_HEALTH, bat.getHealth());
    }
    @Test
    void batNameSetTo556ByConstructorThrowsIllegalArgumentException(){
        //Constructor throws error for numbers in name
        assertThrows(IllegalArgumentException.class, () -> new Bat("556", STANDARD_BAT_HEALTH, STANDARD_BAT_ATTACKPOWER, null, null));
    }
    @Test
    void batMonsterAttackWolfSuccessful(){
        //Set-up
        Monster bat = new Bat();
        Monster wolf = new Wolf();

        //Assert that wolf has full health
        assertEquals(STANDARD_WOLF_HEALTH, wolf.getHealth());

        //Assert that attack is successful and health is deducted
        assertTrue(bat.attack(wolf));
        assertEquals(STANDARD_WOLF_HEALTH-STANDARD_BAT_ATTACKPOWER, wolf.getHealth());
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
        assertEquals(STANDARD_WOLF_HEALTH-STANDARD_WOLF_ATTACKPOWER, otherWolf.getHealth());
        assertFalse(otherWolf.isDead());
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
        assertEquals(0, otherWolf.getHealth());
        assertTrue(otherWolf.isDead());
    }
    @Test
    void wolfMonsterAttackOtherWolfWith0HealthThrowsIllegalStateException(){
        //Set-up
        Monster wolf = new Wolf();
        Monster otherWolf = new Wolf("Wolf", 0, 0, null, null);

        //Asserts that wolf cant attack another monster that has 0 health
        assertThrows(IllegalStateException.class, () -> wolf.attack((otherWolf)));
    }
    @Test
    void wolfWith0HealthAttacksOtherWolfThrowsIllegalStateException(){
        //Set-up
        Monster wolf = new Wolf("Wolf", 0, 4,null, null);
        Monster otherWolf = new Wolf();

        //Asserts that a dead monster cant attack
        assertThrows(IllegalStateException.class, () -> wolf.attack((otherWolf)));
    }
    @Test
    void batHasItemPotionNotLootableBecauseBatIsAliveIllegalStateException(){
        //Set-up
        ArrayList<Item> batItems = new ArrayList<>(Arrays.asList(new Item("Potion")));
        Monster bat = new Bat(batItems);

        //Asserts that a living monster can't get looted
        assertFalse(bat.isDead());
        assertThrows(IllegalStateException.class, () -> bat.getLooted());
    }
    @Test
    void batHasItemsPotionAndSwordLootedSuccessfully(){
        //Set-up
        Item potion = new Item("Potion");
        Item sword = new Item("Sword");
        ArrayList<Item> batItems = new ArrayList<>();
        batItems.add(potion);
        batItems.add(sword);
        ArrayList<Item> lootedItems = new ArrayList<>();
        Monster bat = new Bat("Bat", 0, 0, batItems, null, null);

        //Asserts that inventory is empty and bat is dead
        assertTrue(lootedItems.isEmpty());
        assertTrue(bat.isDead());

        //Asserts that the items gets put in inventory
        lootedItems.addAll(bat.getLooted());
        assertTrue(lootedItems.contains(potion)&& lootedItems.contains(sword));
        assertEquals(2, lootedItems.size());
    }
    @Test
    void batGetsKilledAndHasItemsPotionAndSwordLootedSuccessfullyAndLootedAgainUnsuccessfullyIllegalStateException(){
        //Set-up
        Item potion = new Item("Potion");
        Item sword = new Item("Sword");
        ArrayList<Item> batItems = new ArrayList<>();
        batItems.add(potion);
        batItems.add(sword);
        ArrayList<Item> lootedItems = new ArrayList<>();
        Monster bat = new Bat(batItems);
        Monster otherBat = new Bat();

        //Check that *inventory* is empty and bat is alive at start
        assertTrue(lootedItems.isEmpty());
        assertFalse(bat.isDead());

        //Kill the bat with the items
        for(int i = 0; i < 3; i++){
            otherBat.attack(bat);
        }

        //Ensure bat is dead
        assertTrue(bat.isDead());

        //Loot the items
        lootedItems.addAll(bat.getLooted());

        //Check that *inventory* contains the looted items
        assertTrue(lootedItems.contains(potion)&& lootedItems.contains(sword));
        assertEquals(2, lootedItems.size());

        //Get error when trying to loot again due to no items left
        assertThrows(IllegalStateException.class, () -> bat.getLooted());
    }
}