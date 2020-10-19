package unit;

public class Attack {
    int attackPower;
    AttackType attackType;

    public Attack(int attackPower){
        this(attackPower,AttackType.PHYSICAL);
    }
    public Attack(int attackPower, AttackType attackType){
        this.attackPower = attackPower;
        this.attackType = attackType;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public AttackType getAttackType() {
        return attackType;
    }
}
