// @author Christoffer Öhman
package magic;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealSpellTest {
    private final String name = "Heal";
    private final String description = "Adds 50 health points. Costs: 5 mana.  Requires Magic level 5.";
    private final int standardValue = 1;


    @Test
    void castSpell() {
        int manaCost = standardValue;
        int requiredMagicLevel = standardValue;
        int cooldownDuration = standardValue;
        int healthPoints = 5;
        int experiencePoints = standardValue;
        int healthPointsToHeal = 50;

        HealSpell spell = new HealSpell (name,description,manaCost,requiredMagicLevel,cooldownDuration,healthPointsToHeal );


        // create player with 5 health
        Player player = new Player ("playerclass","race",healthPoints,experiencePoints);

        // assert that health = 5
        assertEquals(healthPoints,player.getHealthPoint ());

        spell.castSpell(player);

        assertEquals (healthPoints + 50  ,player.getHealthPoint ());




        //assert new value

    }
}