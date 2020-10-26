//@author Christoffer Öhman
package magic;

import org.junit.jupiter.api.Test;
import unit.Bat;
import unit.Monster;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FireballSpellTest {

    @Test
    void constructorParamTest() {
        String name = "Fireball";
        String description = "Deal 3 fire-damage";
        int manaCost = 3;
        int requiredMagicSKill = 1;
        int cooldownDuration = 2;

        FireballSpell f = new FireballSpell ();


        assertEquals (name, f.getName ());
        assertEquals (description, f.getDescription ());
        assertEquals (requiredMagicSKill, f.getRequiredMagicSkill ());
        assertEquals (cooldownDuration, f.getCooldownDuration ());
        assertEquals (manaCost, f.getManaCost ());

    }

    @Test
    void castSpellTest() {
        Monster monster = new Bat ();
        FireballSpell fireball = new FireballSpell ();

        //cast fireball on bat. assert dammage

        assertEquals (5, monster.getHealthPoint ());

        fireball.castSpell (null, monster);
        assertEquals (2, monster.getHealthPoint ());


    }
}