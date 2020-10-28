package unit;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class DecisionTableTest {

    @Test
    void R1_DeadAttackerAttacksLivingEnemyISE(){
        Player player = new Player("Warrior", "Orc", 5,0);
        Bat bat = new Bat("Bat", 5, 2, 0, null, null);
        player.setAttackPower(2);
        player.setHealthPoint(0);
        assertFalse(player.isAlive());
        assertThrows(IllegalStateException.class, () -> player.attack(bat));
    }
    @Test
    void R2_AliveAttackerAttacksDeadEnemyISE(){
        Player player = new Player("Warrior", "Orc", 5,0);
        Bat bat = new Bat("Bat", 0, 2, 0, null, null);
        player.setAttackPower(2);
        assertFalse(bat.isAlive());
        assertThrows(IllegalStateException.class, () -> player.attack(bat));
    }
    @Test
    void R3_AliveFlyingAttackerAttacksPlayerForZeroDamage(){
        Player player = new Player("Warrior", "Orc", 5,0);
        Bat bat = new Bat("Bat", 5, 0, 0, null, null);
        assertFalse(bat.isGrounded);
        assertTrue(bat.attack(player));
        assertEquals(5,player.getHealthPoint());
    }
    @Test
    void R4_AliveFlyingAttackerAttacksPlayerWithRangedAttackForZeroDamage(){
        Player player = new Player("Warrior", "Orc", 5,0);
        Bat bat = new Bat("Bat", 5, 0, 0, null, null);
        Attack attack = new Attack(bat.getAttackPower(), true);
        assertFalse(bat.isGrounded);
        assertTrue(bat.attack(player, attack));
        assertEquals(5,player.getHealthPoint());
    }
    @Test
    void R4_AliveAttackerAttacksPlayerWithRangedAttackForZeroDamage(){
        Player player = new Player("Warrior", "Orc", 5,0);
        Bat bat = new Bat("Bat", 5, 0, 0, null, null);
        Attack attack = new Attack(bat.getAttackPower(), true);
        bat.getGrounded();
        assertTrue(bat.isGrounded);
        assertTrue(bat.attack(player, attack));
        assertEquals(5,player.getHealthPoint());
    }
    @Test
    void R5_AliveAttackerAttacksFlyingEnemyMisses(){
        Player player = new Player("Warrior", "Orc", 5,0);
        Bat bat = new Bat("Bat", 5, 0, 0, null, null);
        player.setAttackPower(0);
        assertFalse(bat.isGrounded);
        assertFalse(player.attack(bat));
        assertEquals(5,bat.getHealthPoint());
    }
    @Test
    void R6_AliveAttackerAttacksEnemyWithWeaknessForZeroDamage(){
        Player player = new Player("Warrior", "Orc", 5,0);
        Bat bat = new Bat("Bat", 5, 0, 0, null, AttackType.ICE);
        player.setAttackPower(0);
        Attack attack = new Attack(player.getAttackPower(), AttackType.ICE);
        bat.getGrounded();
        assertTrue(bat.isGrounded);
        assertTrue(player.attack(bat, attack));
        assertEquals(5,bat.getHealthPoint());
    }
    @Test
    void R7_AliveAttackerAttacksEnemyWithoutResistanceOrWeaknessWithIceAttackForTwoDamage(){
        Player player = new Player("Warrior", "Orc", 5,0);
        Bat bat = new Bat("Bat", 5, 0, 0, null, null);
        player.setAttackPower(2);
        Attack attack = new Attack(player.getAttackPower(), AttackType.ICE);
        bat.getGrounded();
        assertTrue(bat.isGrounded);
        assertTrue(player.attack(bat, attack));
        assertEquals(3,bat.getHealthPoint());
    }
    @Test
    void R8_AliveAttackerAttacksEnemyWithFireResistanceWithFireAttackForOneDamage(){
        Player player = new Player("Warrior", "Orc", 5,0);
        Bat bat = new Bat("Bat", 5, 0, 0, AttackType.FIRE, null);
        player.setAttackPower(2);
        Attack attack = new Attack(player.getAttackPower(), AttackType.FIRE);
        bat.getGrounded();
        assertTrue(bat.isGrounded);
        assertTrue(player.attack(bat, attack));
        assertEquals(4,bat.getHealthPoint());
    }
    @Test
    void R9_AliveAttackerAttacksEnemyWithPhysicalWeaknessWithPhysicalAttackForFourDamage(){
        Player player = new Player("Warrior", "Orc", 5,0);
        Bat bat = new Bat("Bat", 5, 0, 0, null, AttackType.PHYSICAL);
        player.setAttackPower(2);
        bat.getGrounded();
        assertTrue(bat.isGrounded);
        assertTrue(player.attack(bat));
        assertEquals(1,bat.getHealthPoint());
    }
}


