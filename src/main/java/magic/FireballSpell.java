//@author Christoffer Öhman
package magic;

import unit.Attack;
import unit.AttackType;
import unit.Combatant;
import unit.Unit;

public class FireballSpell extends  Spell {


    public FireballSpell() {
        super ("FireBall","Deal 3 fire-damage", 3, 1, 2);
    }

    @Override
    public boolean castSpell(Unit caster , Unit target) {

        int attackPower = 3;
        target.takeDamage (new Attack (attackPower,AttackType.FIRE));
        return true;
    }
}
