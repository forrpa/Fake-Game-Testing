package magic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MagicPlayerTest {
    private final int negativeNr =-1;
    private final int positiveNr =-1;



    @Test
    void getMaximumLearnableSpells() {
        MagicPlayer mp = new MagicPlayer("playerClass","race",50,1);

        //  // standard maximum is one;
        assertEquals(positiveNr,mp.getMaximumLearnableSpells ());


    }

    @Test
    void setMaximumLearnableSpells() {
        MagicPlayer mp = new MagicPlayer("playerClass","race",50,1);

        mp.setMaximumLearnableSpells (15);
        assertEquals (15,mp.getMaximumLearnableSpells ());

    }

    @Test
    void addAndRemoveSpell() {
        // todo test adding  different spells.
        MagicPlayer mp = new MagicPlayer("playerClass","race",50,1);
        final int manacost = 6;
        final int requiredMagicLevel = 3;
        Spell spell = new HealSpell ("healspell","random description",manacost,requiredMagicLevel,5,50);
        mp.addSpell (spell);

        assertEquals (spell,mp.spellBook.get(spell.getName ()));
    }

    @Test
    void removeSpell() {
        fail ();
    }

    @Test
    void getSpell() {
        fail ();
    }

    @Test
    void learnSpell() {
        fail ();

    }

    @Test
    void castSpell() {
        fail ();

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