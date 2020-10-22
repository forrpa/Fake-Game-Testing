//@author Christoffer Öhman
package magic;

import unit.Attack;
import unit.AttackType;
import unit.Combatant;

public class FireballSpell extends  Spell {


    public FireballSpell() {
        super ("FireBall","Deal 5 fire-damage", 3, 1, 2);
    }

    @Override
    public boolean castSpell(Combatant target, int mana) {

        if ( mana < getManaCost ()) {
            return false;
        }
        int attackPower = 5;
        target.takeDamage (new Attack (attackPower,AttackType.FIRE));
        return true;
    }
}
