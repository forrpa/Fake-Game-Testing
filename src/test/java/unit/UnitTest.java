package unit;
import edible.Potion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {



    @Test
    void batNameBatSetByConstructor(){
        Monster bat = new Bat("Jabba the Bat", 5, 2);
        assertEquals("Jabba the Bat", bat.getName());
    }
    @Test
    void batNameSetTo556ByConstructorThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Bat("556", 5, 2));
    }
    @Test
    void batMonsterAttackWolfSuccessful(){
        Monster bat = new Bat();
        Monster wolf = new Wolf();
        assertTrue(bat.attack(wolf));
    }
    @Test
    void wolfMonsterAttackBatUnsuccessful(){
        Monster bat = new Bat();
        Monster wolf = new Wolf();
        assertFalse(wolf.attack(bat));
    }
    @Test
    void batMonsterAttackWolfFor2HealthWolfHas6HealthRemaining(){
        Monster bat = new Bat();
        Monster wolf = new Wolf();
        assertTrue(bat.attack(wolf));
        assertEquals(6,wolf.getHealth());
    }
    @Test
    void wolfMonsterAttackOtherWolfFor3HealthOtherWolfHas5HealthRemaining(){
        Monster wolf = new Wolf();
        Monster otherWolf = new Wolf();
        assertTrue(wolf.attack(otherWolf));
        assertEquals(5, otherWolf.getHealth());
        assertFalse(otherWolf.isDead());
    }
    @Test
    void wolfMonsterAttackOtherWolfFor3ThriceOtherWolfHas0HealthRemaining(){
        Monster wolf = new Wolf();
        Monster otherWolf = new Wolf();
        for (int i = 0;i < 3; i++){
            assertTrue(wolf.attack(otherWolf));
        }
        assertEquals(0, otherWolf.getHealth());
        assertTrue(otherWolf.isDead());
    }
    @Test
    void wolfMonsterAttackOtherWolfWith0HealthThrowsIllegalStateException(){
        Monster wolf = new Wolf();
        Monster otherWolf = new Wolf("Wolf", 0, 0);
        assertThrows(IllegalStateException.class, () -> wolf.attack((otherWolf)));
    }
    @Test
    void wolfWith0HealthAttacksOtherWolfThrowsIllegalStateException(){
        Monster wolf = new Wolf("Wolf", 0, 4);
        Monster otherWolf = new Wolf();
        assertThrows(IllegalStateException.class, () -> wolf.attack((otherWolf)));
    }
    @Test
    void batHasItemPotionNotLootableBecauseBatIsAliveIllegalStateException(){
        ArrayList<Item> batItems = new ArrayList<>(Arrays.asList(new Item("Potion")));
        Monster bat = new Bat(batItems);
        assertFalse(bat.isDead());
        assertThrows(IllegalStateException.class, () -> bat.getLooted());
    }
    

}