// @author Christoffer Öhman
package magic;


import unit.Unit;

import static magic.ValueCheck.numberCheck;

public class HealSpell extends Spell {
    final int healthPointsToHeal;


    public HealSpell(String name, String description, int manaCost, int requiredMagicLevel, int cooldownDuration, int healthPointsToHeal) {
        super (name, description, manaCost, requiredMagicLevel, cooldownDuration);
        this.healthPointsToHeal = numberCheck(healthPointsToHeal);
    }

    @Override
    public boolean magicEffect(Unit caster, Unit target) {
        // check if dead?
        int HP = target.getHealthPoint () + healthPointsToHeal;
        target.setHealthPoint (HP);

        return true;

    }


}
