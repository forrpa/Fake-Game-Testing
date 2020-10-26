package unit;

public class Villager extends NPC implements Talkable{

    public Villager(String name, int maxHealth, int attackPower, boolean isGrounded, AttackType resistance, AttackType weakness){
        super(name, maxHealth, attackPower, 50, true, null, null);
    }

    @Override
    public boolean talk() {
        return true;
    }
}
