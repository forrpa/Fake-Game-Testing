//@author Christoffer Öhman
package magic;

import org.junit.jupiter.api.Test;
import unit.Bat;
import unit.Monster;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FireballSpellTest {

    @Test
    void castSpellTest() {
        Monster monster = new Bat ();
        FireballSpell fireball = new FireballSpell ();
        int insufficientMana = fireball.getManaCost () - 1;
        int sufficientMana = fireball.getManaCost ();


        // insufficient Mana test
        assertFalse (fireball.castSpell (monster, insufficientMana));

        // sufficient mana test
        assertTrue (fireball.castSpell (monster,sufficientMana));


    }
}