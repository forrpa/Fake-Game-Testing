package unit;

public class Attack {
    public static final int RESISTANCE_AND_WEAKNESS_MODIFIER = 2;
    private int attackPower;
    private boolean isRanged;
    private AttackType attackType;

    public Attack(int attackPower){
        this(attackPower, false, AttackType.PHYSICAL);
    }
    public Attack(int attackPower, boolean isRanged){
        this(attackPower, isRanged, AttackType.PHYSICAL);
    }
    public Attack(int attackPower, AttackType attackType){
        this(attackPower, false, attackType);
    }
    public Attack(int attackPower, boolean isRanged, AttackType attackType){
        if(attackPower <= 0){
            this.attackPower = 0;
        }else{
            this.attackPower = attackPower;
        }
        this.attackType = attackType;
        this.isRanged = isRanged;
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

    public boolean isRanged() {
        return isRanged;
    }

    private int getAttackPower(){
        return getAttackPower(null, null);
    }

    public String toString(){
        return String.format("Base damage: %d.",getAttackPower());
    }
}
