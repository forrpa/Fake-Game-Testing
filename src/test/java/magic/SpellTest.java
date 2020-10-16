// @author Christoffer Öhman
package magic;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpellTest {
    private final String name = "Heal";
    private final String description = "Adds 50 health points. Costs: 5 mana.  Requires Magic level 5.";
    private final int standardValue = 1;
    private final int negativeNr = -1;


    @Test
    public void constructorTest() {

        Spell spell = new Spell (name, description, standardValue, standardValue, standardValue);
        assertEquals (name, spell.getName ());
        assertEquals (description, spell.getDescription ());
        assertEquals (standardValue, spell.getManaCost ());
        assertEquals (standardValue, spell.getRequiredMagicLevel ());
        assertEquals (standardValue, spell.getCooldownDuration ());


        // check for negative numbers
        // check for maximum name and description char.?
        // check exceptions.

    }


    @Test
    void setNegativeManaCost() {
        Spell spell = new Spell ();
        Assertions.assertThrows (IllegalArgumentException.class, () -> spell.setManaCost (-1));

    }

    @Test
    void setNegativeRequiredMagicLevel() {
        Spell spell = new Spell ();
        Assertions.assertThrows (IllegalArgumentException.class, () -> spell.setRequiredMagicLevel (-1));


    }

    @Test
    void setNegativeCooldownDuration() {
        Spell spell = new Spell ();
        Assertions.assertThrows (IllegalArgumentException.class, () -> spell.setCooldownDuration (-1));

    }
}


