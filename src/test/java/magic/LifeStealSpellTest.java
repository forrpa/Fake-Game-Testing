package magic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LifeStealSpellTest {
    LifeStealSpell lsp;


    @BeforeEach
    private void beforeEach(){
        lsp = new LifeStealSpell ();


    }

    @Test
    void magicEffectParamTest() {
        String name = "Life Steal";
        String description = "Deal damage to an enemy and gain the same amount of health ";
        int manaCost = 5;
        int reqMagicskill = 4;
        int cooldownduration = 5;
        assertEquals (name,lsp.getName ());
        assertEquals (description, lsp.getDescription ());
        assertEquals (manaCost, lsp.getManaCost ());
        assertEquals (reqMagicskill, lsp.getRequiredMagicSkill ());
        assertEquals (cooldownduration, lsp.getCooldownDuration ());
    }

    // add test casting on some



}