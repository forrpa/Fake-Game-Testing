//@author Christoffer Öhman
package magic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagicPlayerTest {
    private final int negativeNr = -1;
    private final int positiveNr = 1;
    private final int maxIntValue = Integer.MAX_VALUE;


    @Test
    void getStandardMaximumLearnableSpellsTest() {
        MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        int standardMaxLearnableSpells = 1;
        assertEquals (standardMaxLearnableSpells, mp.getMaximumLearnableSpells ());
    }

    @Test
    void setPositiveMaximumLearnableSpellsTest() {
        MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        //testing an arbitrary value as 1 is standard.
        mp.setMaximumLearnableSpells (15);
        assertEquals (15, mp.getMaximumLearnableSpells ());

    }

    @Test
    void setNegativeMaximumLearnableSpellsTest() {
        MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        Assertions.assertThrows (IllegalArgumentException.class, () -> mp.setMaximumLearnableSpells (negativeNr));
    }

    @Test
    void addAndGetSpellTest() {
        // todo test adding  different spells.
        MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        final int manaCost = 6;
        final int requiredMagicLevel = 3;
        Spell spell = new HealSpell ("healspell", "random description", manaCost, requiredMagicLevel, 5, 50);
        mp.addSpell (spell);
        assertEquals (spell, mp.spellBook.get (spell.getName ()));
    }


    @Test
        //should also remove spell from learntSpells if present.
    void removeSpellNotLearntTest() {
        final String name = "funName";
        final MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        final Spell spell = new HealSpell (name, "description", positiveNr, positiveNr, positiveNr, positiveNr);
        mp.addSpell (spell);
        assertEquals (spell, mp.removeSpell (name));
        assertFalse (mp.learntSpells.containsValue (spell));
    }

    @Test
        //should also remove spell from learntSpells if present.
    void removeLearntSpellTest() {
        final String name = "funName";
        final MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        final Spell spell = new HealSpell (name, "description", positiveNr, positiveNr, positiveNr, positiveNr);
        mp.addSpell (spell);
        mp.learnSpell (spell);
        assertEquals (spell, mp.removeSpell (name));
        assertEquals (spell, mp.unLearnSpell (name));
    }

    @Test
    void removeSpellThatDoesNotExistTest() {
        final String wrongName = "does not exist";
        final MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        assertNull (mp.removeSpell (wrongName));
        assertNull (mp.unLearnSpell (wrongName));
    }


    @Test
    void getSpellTest() {
        final String name = "name";
        final MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        final Spell spell = new HealSpell (name, "description", positiveNr, positiveNr, positiveNr, positiveNr);
        mp.addSpell (spell);
        assertEquals (spell, mp.getSpell (name));
    }


    @Test
    void learnSpellTest() {
        final String name = "name";
        final int maxLearnableSpells = 1;
        final MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        int requiredMagicLevel = 0;
        final Spell spell = new HealSpell (name, "description", positiveNr, requiredMagicLevel, positiveNr, positiveNr);

        //spell should be added to learntSpell AND spellBook.
        mp.setMaximumLearnableSpells (maxLearnableSpells);
        mp.learnSpell (spell);

        assertEquals (spell, mp.learntSpells.get (name));
        assertEquals (spell, mp.spellBook.get (name));

        // needs to check if MinMagicLevel = magiclevel

    }


    @Test
    void learnSpellButMaximumLearnableSpellsReachedTest() {
        final String spellNameone = "name";
        final String spellNameTwo = "secondName";
        final int maxLearnableSpells = 1;
        int requiredMagicLevel = 0;
        final int maxLearnablespellsPlusOne = maxLearnableSpells + 1;
        final MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        final Spell spellOne = new HealSpell (spellNameone, "description", positiveNr, requiredMagicLevel, positiveNr, positiveNr);
        final Spell spellTwo = new HealSpell (spellNameTwo, "description", positiveNr, requiredMagicLevel, positiveNr, positiveNr);

        mp.setMaximumLearnableSpells (maxLearnableSpells);
        mp.learnSpell (spellOne);
        mp.learnSpell (spellTwo);

        assertTrue (mp.learntSpells.containsKey (spellNameone));
        assertTrue (mp.spellBook.containsKey (spellNameone));
        assertFalse (mp.learntSpells.containsKey (spellNameTwo));
        assertFalse (mp.learntSpells.containsKey (spellNameTwo));

        // increasing MaxLearnableSpells and testing again.
        mp.setMaximumLearnableSpells (maxLearnablespellsPlusOne);
        mp.learnSpell (spellTwo);

        assertTrue (mp.learntSpells.containsKey (spellNameTwo));
        assertTrue (mp.learntSpells.containsKey (spellNameTwo));
    }


    @Test
    public void unLearnSpellTest() {
        final String spellName = "name";
        final int maxLearnableSpells = 1;
        final MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        final Spell spell = new HealSpell (spellName, "description", positiveNr, positiveNr, positiveNr, positiveNr);

        mp.learnSpell (spell);
        assertEquals (spell, mp.learntSpells.get (spellName));
        assertEquals (spell, mp.getSpell (spellName));

        mp.unLearnSpell (spellName);
        assertNull (mp.learntSpells.get (spellName));
        assertEquals (spell, mp.getSpell (spellName));
    }

    @Test
    public void CastGenericHealSpellTest() {
        // Good case for TDD.
        // create this test and then commit changes.
        final String spellName = "spn";
        MagicPlayer mp = new MagicPlayer ("PlayerClass", "Race", positiveNr, positiveNr);
        final Spell spell = new HealSpell (spellName, "description", positiveNr, positiveNr, positiveNr, positiveNr);

        // spell is not learnt:
        assertFalse (mp.castSpell (spell, mp));


        //  if lernt
        // if  enough mana
        // if isCastable false // rename to isOnCoolDown.

        // if mana is reduced
        // if  isOnCoolDown is set to true
        // if effect is done.

        // should also test for every spell.

        // if added magicXP.

    }

    @Test
    void SetNegativeMagicSkillTest() {
        MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        Assertions.assertThrows (IllegalArgumentException.class, () -> mp.setMagicSkill (negativeNr));
    }

    @Test
    void SetMagicSkillAboveMaximumTest() {
        MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        int maxPlusOne = 11;
        Assertions.assertThrows (IllegalArgumentException.class, () -> mp.setMagicSkill (maxPlusOne));
    }


    @Test
    void getMana() {
        fail ();

    }

    @Test
    void setMana() {
        fail ();

    }

    @Test
    void getManaRegenSpeed() {
        fail ();

    }

    @Test
    void setManaRegenSpeed() {
        fail ();

    }

    @Test
    void getMagicLevel() {
        fail ();

    }

    @Test
    void setMagicLevel() {
        fail ();

    }

    @Test
    void getMaxMana() {
        fail ();

    }

    @Test
    void setMaxMana() {
        fail ();

    }
}