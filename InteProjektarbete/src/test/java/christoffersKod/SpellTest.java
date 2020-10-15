package christoffersKod;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpellTest {


    @Test
    public void constructorTest(){
        String name = "Heal";
        String description = "Adds 50 health points. Costs: 5 mana.  Requires Magic level 5.";
        int standardValue = 1;
        Spell spell = new Spell (name,description,standardValue,standardValue,standardValue);
        assertEquals(name, spell.getName());
        assertEquals(description, spell.getDescription());
        assertEquals (standardValue, spell.getManaCost());
        assertEquals (standardValue, spell.getRequiredMagicLevel());
        assertEquals (standardValue, spell.getCooldownDuration());
    }

}


