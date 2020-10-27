package quest;

import unit.Monster;
import unit.Talkable;

public class StealthAndAttackEnemy extends Monster implements Talkable {

    public StealthAndAttackEnemy() {
        super("Stealth and attack enemy", 8, 3, 100, null, null);
    }

    @Override
    public boolean talk() {
        return true;
    }
}
