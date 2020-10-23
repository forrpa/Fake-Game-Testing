package magic;

import unit.Attack;
import unit.AttackType;
import unit.Unit;

public class LifeStealSpell extends Spell {


    public LifeStealSpell() {
        super ("Life Steal", "Deal damage to an enemy and gain the same amount of health ", 5, 4, 5);
    }

    @Override
    public boolean magicEffect(Unit caster, Unit target) {
        // changed. not sending mana

        int targetHealthBefore = target.getHealthPoint ();
        int attackPower = 5;
        target.takeDamage (new Attack (attackPower, AttackType.PHYSICAL));
        int targetHealthAfter = target.getHealthPoint ();

        int targetDamage = targetHealthBefore - targetHealthAfter;

        caster.setHealthPoint (caster.getHealthPoint () + targetDamage);
        return true;
    }


}
