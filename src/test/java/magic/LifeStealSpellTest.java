package magic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.Bat;
import unit.Monster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LifeStealSpellTest {
    LifeStealSpell lsp;


    @BeforeEach
    private void beforeEach() {
        lsp = new LifeStealSpell ();


    }

    @Test
    void magicEffectParamTest() {
        String name = "Life Steal";
        String description = "Deal damage to an enemy and gain the same amount of health ";
        int manaCost = 5;
        int reqMagicskill = 4;
        int cooldownduration = 5;
        assertEquals (name, lsp.getName ());
        assertEquals (description, lsp.getDescription ());
        assertEquals (manaCost, lsp.getManaCost ());
        assertEquals (reqMagicskill, lsp.getRequiredMagicSkill ());
        assertEquals (cooldownduration, lsp.getCooldownDuration ());
    }

    @Test
        // should deal damage to target and heal caster the same amount.
    void castLifeStealSpellTest() {
        int mpHealth = 50;
        MagicPlayer mp = new MagicPlayer ("Mage", "Human", mpHealth, 5);
        LifeStealSpell lifeSteal = new LifeStealSpell ();
        mp.setMaxManaPoint (20);
        mp.setManaPoint (10);
        mp.setMagicSkill (10);
        Monster monster = new Bat ();
        monster.setMaxHealthPoint (100);
        mp.setMaxHealthPoint (100);
        monster.setHealthPoint (10);
        mp.setMaximumLearnableSpells (5);

        assertTrue (mp.learnSpell (lifeSteal));
        assertEquals (10, mp.getMagicSkill ());
        assertEquals (4, lifeSteal.getRequiredMagicSkill ());
        assertTrue (mp.learntSpells.containsKey (lifeSteal.getName ()));

        lifeSteal.magicEffect (mp, monster);
        assertEquals (5, monster.getHealthPoint ());
        assertEquals (55, mp.getHealthPoint ());

        // Casting from mp
        assertTrue (mp.castSpell (lifeSteal, monster));
        assertEquals (0, monster.getHealthPoint ());


    }


}