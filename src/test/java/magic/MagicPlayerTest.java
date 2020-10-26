//@author Christoffer Öhman
package magic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MagicPlayerTest {
    private final int negativeNr = -1;
    private final int positiveNr = 1;
    private final int maxIntValue = Integer.MAX_VALUE -1; // use?
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

        mp.addSpell (spell);
        mp.learnSpell (spell);
        assertEquals (spell, mp.removeSpell (spellName));
        assertEquals (spell, mp.unLearnSpell (spellName));
        assertFalse (mp.spellBook.containsKey (spellName));
        assertFalse (mp.learntSpells.containsKey (spellName));
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


        //spell should be added to learntSpell AND spellBook.
        mp.setMaximumLearnableSpells (maxLearnableSpells);

        assertTrue (mp.learnSpell (spell));
        assertEquals (spell, mp.learntSpells.get (spellName));
        assertEquals (spell, mp.spellBook.get (spellName));

    }

    @Test
    void LearnSpellWithInadequateMagicSkillLevelTest() {
        // Testing learning spells with less than required magicSkill.

        mp.setMagicSkill (0);
        assertFalse (mp.learnSpell (spell));
        assertFalse (mp.learntSpells.containsValue (spell));

        // check that a spell with 0 magicSkill is learnt with 0 skill.
        HealSpell spellZero = new HealSpell ("Name","Description",1,0,1,50);
        assertTrue (mp.learnSpell (spellZero));
        assertTrue (mp.learntSpells.containsValue (spellZero));
    }


    @Test
    void learnSpellButMaximumLearnableSpellsReachedTest() {
        final String spellNameOne = "name";
        final String spellNameTwo = "secondName";
        final int maxLearnableSpells = 1;
        int requiredMagicSkill = 0;
        final int maxLearnableSpellsPlusOne = maxLearnableSpells + 1;
        final Spell spellOne = new HealSpell (spellNameOne, "description", positiveNr, requiredMagicSkill, positiveNr, positiveNr);
        final Spell spellTwo = new HealSpell (spellNameTwo, "description", positiveNr, requiredMagicSkill, positiveNr, positiveNr);

        mp.setMaximumLearnableSpells (maxLearnableSpells);
        mp.learnSpell (spellOne);
        mp.learnSpell (spellTwo);

        // spellNameOne should be learnt
        assertTrue (mp.learntSpells.containsKey (spellNameOne));
        assertTrue (mp.spellBook.containsKey (spellNameOne));

        //spellNameTwo should not be learnt. (out of memory).
        assertFalse (mp.spellBook.containsKey (spellNameTwo));
        assertFalse (mp.learntSpells.containsKey (spellNameTwo));

        // increasing MaxLearnableSpells and testing again. spells should be learnt now.
        mp.setMaximumLearnableSpells (maxLearnableSpellsPlusOne);
        mp.learnSpell (spellTwo);

        assertTrue (mp.learntSpells.containsKey (spellNameTwo));
        assertTrue (mp.learntSpells.containsKey (spellNameTwo));
    }


    @Test
    public void unLearnSpellTest() {

        mp.learnSpell (spell);

        // assert that the spell is learnt
        assertEquals (spell, mp.learntSpells.get (spellName));
        assertEquals (spell, mp.getSpell (spellName));

        // assert that spell is only remove from learntSpells.
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
    void getManaPointTest() {
        assertEquals (positiveNr, mp.getManaPoint ());

    }


    // Trying out  @ParameterizedTest.  I understand that only  testing one  and ten would suffice.
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void setAcceptableManaPointTest(int number) {

        mp.setMaxManaPoint (10);
        mp.setManaPoint (number);
        assertEquals (number, mp.manaPoint);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, maxIntValue})
    void setManaPointHigherThanMaxManaTest(int number) {

        int maxManaPoint = 0;
        mp.setMaxManaPoint (maxManaPoint);
        mp.setManaPoint (number);
        assertEquals (maxManaPoint, mp.manaPoint);
    }

    @Test
    void setNegativeManaPointTest() {
        mp.setMaxManaPoint (10);
        Assertions.assertThrows (IllegalArgumentException.class, () -> mp.setManaPoint (-1));
    }


    @Test
    void getMaxManaPointTest() {
        assertEquals (mp.maxManaPoint,mp.getMaxManaPoint ());
    }

    @Test
    void setMaxManaTest() {
       mp.setMaxManaPoint (positiveNr);
       assertEquals (positiveNr,mp.maxManaPoint);

       mp.setMaxManaPoint (maxIntValue);
       assertEquals (maxIntValue,mp.maxManaPoint);
    }

    @Test
    void setNegativeManaTest(){
        Assertions.assertThrows (IllegalArgumentException.class, () -> mp.setMaxManaPoint (negativeNr));

    }
    @Test
    void getMagicSkillTest(){
        assertEquals (mp.magicSkill,mp.getMagicSkill ());
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
}