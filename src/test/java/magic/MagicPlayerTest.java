//@author Christoffer Öhman
package magic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagicPlayerTest {
    private final int negativeNr = -1;
    private final int positiveNr = 1;
    private final int maxIntValue = Integer.MAX_VALUE; // use?
    MagicPlayer mp;
    Spell spell;
    String spellName;

    @BeforeEach
    void beforeEach() {
        spellName = "spellNameString";
        mp = new MagicPlayer ("playerClass", "race", 50, 1);
        spell = new HealSpell (spellName, "description", positiveNr, positiveNr, positiveNr, positiveNr);


    }

    @AfterEach
    void afterEach() {


    }

    @Test
    void getStandardMaximumLearnableSpellsTest() {
        int standardMaxLearnableSpells = 1;
        assertEquals (standardMaxLearnableSpells, mp.getMaximumLearnableSpells ());
    }

    @Test
    void setPositiveMaximumLearnableSpellsTest() {
        //testing an arbitrary value as 1 is standard.
        mp.setMaximumLearnableSpells (15);
        assertEquals (15, mp.getMaximumLearnableSpells ());

    }

    @Test
    void setNegativeMaximumLearnableSpellsTest() {
        Assertions.assertThrows (IllegalArgumentException.class, () -> mp.setMaximumLearnableSpells (negativeNr));
    }

    @Test
    void addAndGetSpellTest() {
        // add different spells?
        final int manaCost = 6;
        final int requiredMagicLevel = 3;
        Spell spell = new HealSpell ("healspell", "random description", manaCost, requiredMagicLevel, 5, 50);
        mp.addSpell (spell);
        assertEquals (spell, mp.spellBook.get (spell.getName ()));
    }


    @Test
        //should also remove spell from learntSpells if present.
    void removeSpellNotLearntTest() {
        mp.addSpell (spell);
        assertEquals (spell, mp.removeSpell (spellName));
        assertFalse (mp.learntSpells.containsValue (spell));
    }

    @Test
    void removeLearntSpellTest() {
        final String name = "funName";
        mp.addSpell (spell);
        mp.learnSpell (spell);
        assertEquals (spell, mp.removeSpell (name));
        assertEquals (spell, mp.unLearnSpell (name));
    }

    @Test
    void removeSpellThatDoesNotExistTest() {
        final String wrongName = "does not exist";
        assertNull (mp.removeSpell (wrongName));
        assertNull (mp.unLearnSpell (wrongName));
    }


    @Test
    void getSpellTest() {
        mp.addSpell (spell);
        assertEquals (spell, mp.getSpell (spellName));
    }


    @Test
    void learnSpellTest() {
        final int maxLearnableSpells = 1;
        int requiredMagicLevel = 0;

        //spell should be added to learntSpell AND spellBook.
        mp.setMaximumLearnableSpells (maxLearnableSpells);
        mp.learnSpell (spell);

        assertEquals (spell, mp.learntSpells.get (spellName));
        assertEquals (spell, mp.spellBook.get (spellName));

        // needs to add tests for  if MinMagicLevel

    }


    @Test
    void learnSpellButMaximumLearnableSpellsReachedTest() {
        final String spellNameOne = "name";
        final String spellNameTwo = "secondName";
        final int maxLearnableSpells = 1;
        int requiredMagicLevel = 0;
        final int maxLearnablespellsPlusOne = maxLearnableSpells + 1;
        final MagicPlayer mp = new MagicPlayer ("playerClass", "race", 50, 1);
        final Spell spellOne = new HealSpell (spellNameOne, "description", positiveNr, requiredMagicLevel, positiveNr, positiveNr);
        final Spell spellTwo = new HealSpell (spellNameTwo, "description", positiveNr, requiredMagicLevel, positiveNr, positiveNr);

        mp.setMaximumLearnableSpells (maxLearnableSpells);
        mp.learnSpell (spellOne);
        mp.learnSpell (spellTwo);

        assertTrue (mp.learntSpells.containsKey (spellNameOne));
        assertTrue (mp.spellBook.containsKey (spellNameOne));
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
        final int maxLearnableSpells = 1;

        mp.learnSpell (spell);
        assertEquals (spell, mp.learntSpells.get (spellName));
        assertEquals (spell, mp.getSpell (spellName));

        // should only remove from learntSpells.
        mp.unLearnSpell (spellName);
        assertNull (mp.learntSpells.get (spellName));
        assertEquals (spell, mp.getSpell (spellName));
    }

    @Test
    // testing the method castSpell, not the spell that is cast.
    public void CastGenericHealSpellTest() {
        // spell is not learnt:
        assertFalse (mp.castSpell (spell, mp));

        // spell is learnt and sufficient mana :
        mp.learnSpell (spell);
        assertTrue (mp.castSpell (spell, mp));
        assertEquals (0, mp.getManaPoint ());

        // casting with insufficient mana (used up by last test)
        assertFalse (mp.castSpell (spell, mp));

    }

    @Test
    void SetNegativeMagicSkillTest() {
        Assertions.assertThrows (IllegalArgumentException.class, () -> mp.setMagicSkill (negativeNr));
    }

    @Test
    void SetMagicSkillAboveMaximumTest() {
        int maxPlusOne = 11;
        Assertions.assertThrows (IllegalArgumentException.class, () -> mp.setMagicSkill (maxPlusOne));
    }


    @Test
    void getManaPointTest() {
        assertEquals (positiveNr, mp.getManaPoint ());

    }

    @Test
    void setManaPointTest() {


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