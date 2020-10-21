package unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {
    private final static int STANDARD_WOLF_ATTACKPOWER = 3;

    @Test
    void attackWithThreePowerReturnsThreeDamageIfNoResistanceOrWeakness(){
        Attack attack = new Attack(STANDARD_WOLF_ATTACKPOWER);
        assertEquals(STANDARD_WOLF_ATTACKPOWER, attack.getAttackPower(null, null));
    }
    @Test
    void attackWithThreePowerReturnsOneDamageIfResistant(){
        Attack attack = new Attack(STANDARD_WOLF_ATTACKPOWER, AttackType.FIRE);
        assertEquals(1, attack.getAttackPower(AttackType.FIRE, null));
    }
    @Test
    void attackWithThreePowerReturnsSixDamageIfWeak(){
        Attack attack = new Attack(STANDARD_WOLF_ATTACKPOWER, AttackType.ICE);
        assertEquals(6, attack.getAttackPower(null, AttackType.ICE));
    }
    @Test
    void attackWithNegativeThreePowerReturnsZero(){
        Attack attack = new Attack(-3);
        assertEquals(0, attack.getAttackPower(null, null));
    }

}