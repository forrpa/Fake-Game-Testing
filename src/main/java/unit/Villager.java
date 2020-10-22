package unit;

public class Villager extends NPC implements Talkable{

    Villager(String name, int maxHealth, int attackPower, boolean isGrounded, AttackType resistance, AttackType weakness){
        super(name, maxHealth, attackPower, 50, true, null, null);
    }

    @Override
    public String talk() {
        return null;
    }
}
