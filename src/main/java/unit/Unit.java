package unit;

public abstract class Unit implements Combatant {
    private boolean isGrounded;
    protected int healthPoint;
    protected int maxHealthPoint;
    protected int attackPower; //ändrade från priv till prot, hoppas det är okej. //Christian


    public Unit(int maxHealth, int attackPower, boolean isGrounded){
        setHealthPoint(maxHealth);
        this.maxHealthPoint = maxHealth;
        this.isGrounded = isGrounded;
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

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    protected void setMaxHealthPoint(int healthPoint){
        maxHealthPoint += healthPoint;
    }

    public void increaseMaxHealthPoint(int healthPoint){
        this.maxHealthPoint += healthPoint;
    }

    public boolean isAlive(){
        return (healthPoint > 0);

    }
    public int getMaxHealth(){
        return maxHealthPoint;
    }

    public void fillHealthBar(){
        setHealthPoint(maxHealthPoint);
    }
}
