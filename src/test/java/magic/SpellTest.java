// @author Christoffer Öhman
package magic;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static magic.ValueCheck.stringCheck;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SpellTest {
    private final String standardName = "Heal";
    private final String standardDescription = "Adds 50 health points. Costs: 5 mana.  Requires Magic level 5.";
    private final int standardPosValue = 1;
    private final int standardNegativeNr = -1;
    Spell spell;


    @Test
    public void constructorParamTest() {

        Spell spell = new Spell (standardName, standardDescription, standardPosValue, standardPosValue, standardPosValue);
        assertEquals (standardName, spell.getName ());
        assertEquals (standardDescription, spell.getDescription ());
        assertEquals (standardPosValue, spell.getManaCost ());
        assertEquals (standardPosValue, spell.getRequiredMagicSkill ());
        assertEquals (standardPosValue, spell.getCooldownDuration ());



    }

        @ParameterizedTest
        @NullSource
        @EmptySource
        @ValueSource(strings = {" ", "   ", "\t", "\n"})
        void constructorEmptyStringCheckTest(final String testString) {
            Assertions.assertThrows (IllegalArgumentException.class, ()  -> new Spell (testString, standardDescription, standardNegativeNr, standardPosValue, standardPosValue));
            Assertions.assertThrows (IllegalArgumentException.class, ()  -> new Spell (testString, testString, standardNegativeNr, standardPosValue, standardPosValue));

        }

    @Test
    public void constructorNegativeNrTest() {

        Assertions.assertThrows (IllegalArgumentException.class, () -> new Spell (standardName, standardDescription, standardNegativeNr, standardPosValue, standardPosValue));
        Assertions.assertThrows (IllegalArgumentException.class, () -> new Spell (standardName, standardDescription, standardPosValue, standardNegativeNr, standardPosValue));
        Assertions.assertThrows (IllegalArgumentException.class, () -> new Spell (standardName, standardDescription, standardPosValue, standardPosValue, standardNegativeNr));

    }


    @Test
    void setManaCostTest() {
        Spell spell = new Spell ();

        // negative nr. test
        Assertions.assertThrows (IllegalArgumentException.class, () -> spell.setManaCost (-1));

        //  positive nr. test
        spell.setManaCost (standardPosValue);
        assertEquals (standardPosValue, spell.getManaCost ());

    }

    @Test
    void setRequiredMagicLevelTest() {
        Spell spell = new Spell ();

        // negative nr.test
        Assertions.assertThrows (IllegalArgumentException.class, () -> spell.setRequiredMagicSkill (-1));

        //  positive nr. test
        spell.setRequiredMagicSkill (standardPosValue);
        assertEquals (standardPosValue, spell.getRequiredMagicSkill ());
    }

    @Test
    void setCooldownDurationTest() {
        Spell spell = new Spell ();

        // negative nr test.
        Assertions.assertThrows (IllegalArgumentException.class, () -> spell.setCooldownDuration (-1));

        //  positive nr. test
        spell.setCooldownDuration (standardPosValue);
        assertEquals (standardPosValue, spell.getCooldownDuration ());
    }

    @Test
    void magicEffectTest() {
        // should be overrun by subclass, set to return false in super.
        Spell spell = new Spell ();

        assertFalse (spell.magicEffect (null, null));

    }
}



