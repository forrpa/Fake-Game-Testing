package magic;

import unit.Unit;

// Spell that deals damage to a target, while the caster gains the same amount of health
public class LifeStealSpell extends Spell {


    public LifeStealSpell() {
        super ("Life Steal", "Deal damage to an enemy and gain the same amount of health ", 5, 4, 5);
    }

    @Override
     boolean magicEffect(Unit caster, Unit target) {
        // changed. not sending mana

        int power = 5;
        target.setHealthPoint (target.getHealthPoint () - power);
        caster.setHealthPoint (caster.getHealthPoint () + power);
        return true;
    }

}
