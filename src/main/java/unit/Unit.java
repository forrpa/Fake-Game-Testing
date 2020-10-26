package unit;

public abstract class Unit implements Combatant {
    protected static final int MAX_ALLOWED_ATTRIBUTEVALUE = 50000;

    protected boolean isGrounded;
    protected int healthPoint;
    protected int maxHealthPoint;
    protected int attackPower;


    public Unit(int maxHealth, int attackPower, boolean isGrounded){
        setMaxHealthPoint(maxHealth);
        setHealthPoint(maxHealth);
        this.isGrounded = isGrounded;
        setAttackPower(attackPower);
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
    public void setHealthPoint(int healthPoint){
        if(healthPoint <= 0){
            this.healthPoint = 0;
        }else if(healthPoint >= getMaxHealthPoint()) {
            this.healthPoint = getMaxHealthPoint();
        }else{
            this.healthPoint = healthPoint;
        }
    }
    public void increaseHealth(int pointsToIncrease) {
        int validatedPointsToIncrease = validateAttributeValue(pointsToIncrease);
        int newHealth = getHealthPoint() + validatedPointsToIncrease;
        setHealthPoint(newHealth);
    }
    public void decreaseHealth(int pointsToDecrease) {
        int validatedPointsToDecrease = validateAttributeValue(pointsToDecrease);
        int newHealth = getHealthPoint() - validatedPointsToDecrease;
        setHealthPoint(newHealth);
    }
    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setAttackPower(int attackPower) {
    this.attackPower = validateAttributeValue(attackPower);
    }

    public void setMaxHealthPoint(int healthPoint){
        maxHealthPoint = validateAttributeValue(healthPoint);
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

    public void restoreFullHealth(){
        setHealthPoint(maxHealthPoint);
    }

    public int validateAttributeValue(int attributeValue){
        if(attributeValue <= 0){
            return 0;
        }else if( attributeValue <= MAX_ALLOWED_ATTRIBUTEVALUE) {
            return attributeValue;
        }else{
                throw new IllegalArgumentException("Attribute value is over max allowed");
        }
    }
}
