package unit;

public abstract class Unit implements Combatant {
    private boolean isGrounded;
    private int healthPoint;
    private int maxHealth;
    private int attackPower;
    private AttackType resistance;
    private AttackType weakness;


    public Unit(int maxHealth, int attackPower, boolean isGrounded, AttackType resistance, AttackType weakness){
        setHealthPoint(maxHealth);
        this.maxHealth = maxHealth;
        this.isGrounded = isGrounded;
        this.resistance = resistance;
        this.weakness = weakness;
        if(attackPower < 0){
            this.attackPower = 0;
        }else {
            this.attackPower = attackPower;
        }
    }

    public int getAttackPower() {
        return attackPower;
    }

    public boolean isGrounded(){
        return isGrounded;
    }
    public int getHealthPoint() {
        return healthPoint;
    }
    protected void setHealthPoint(int healthPoint){
        if(healthPoint <= 0){
            this.healthPoint = 0;
        }else {
            this.healthPoint = healthPoint;
        }
    }
    public boolean isAlive(){
        return (healthPoint > 0);

    }
    public int getMaxHealth(){
        return maxHealth;
    }

    public AttackType getResistance() {
        return resistance;
    }

    public AttackType getWeakness() {
        return weakness;
    }
}
