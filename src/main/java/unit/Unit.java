package unit;

public class Unit {
    private boolean isGrounded;
    private int healthPoint;
    private int maxHealth;
    private AttackType resistance;
    private AttackType weakness;


    public Unit(int maxHealth, boolean isGrounded, AttackType resistance, AttackType weakness){
        setHealthPoint(maxHealth);
        this.maxHealth = maxHealth;
        this.isGrounded = isGrounded;
        this.resistance = resistance;
        this.weakness = weakness;
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
