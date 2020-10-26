// @author Christoffer Öhman
package magic;


import player.Player;
import unit.Unit;

public class HealSpell extends Spell {
    int healthPointsToHeal;


    public HealSpell(String name, String description, int manaCost, int requiredMagicLevel, int cooldownDuration, int healthPointsToHeal) {
        super (name, description, manaCost, requiredMagicLevel, cooldownDuration);
        this.healthPointsToHeal = healthPointsToHeal;
    }

    @Override
    public boolean magicEffect(Unit caster, Unit target) {
        // check if dead?
        int HP = target.getHealthPoint () + healthPointsToHeal;
        target.setHealthPoint (HP);

        return true;

    }


}
