package unit;

public class Attack {
    public static final int RESISTANCE_AND_WEAKNESS_MODIFIER = 2;
    int attackPower;
    AttackType attackType;

    public Attack(int attackPower){
        this(attackPower,AttackType.PHYSICAL);
    }
    public Attack(int attackPower, AttackType attackType){
        if(attackPower <= 0){
            this.attackPower = 0;
        }else{
            this.attackPower = attackPower;
        }
        this.attackType = attackType;
    }

    public int getAttackPower(AttackType enemyResistance, AttackType enemyWeakness) {
        if(enemyResistance != null && attackType.equals(enemyResistance)){
            return attackPower/RESISTANCE_AND_WEAKNESS_MODIFIER;
        }else if(enemyWeakness != null && attackType.equals(enemyWeakness)){
            return attackPower*RESISTANCE_AND_WEAKNESS_MODIFIER;
        }else {
            return attackPower;
        }
    }
    private int getAttackPower(){
        return getAttackPower(null, null);
    }

    public String toString(){
        return String.format("Base damage: %d.",getAttackPower());
    }
}
