package main.java.se.su.dsv.projektarbete.unit;

public abstract class Monster {
    private String name;
    private boolean isGrounded;
    private int health;
    private int attackPower;

    public Monster(String name, int health, int attackPower){
        setName(name);
        setHealth(health);
        setAttackPower(attackPower);
        this.isGrounded = true;
    }

    public Monster(String name, int health, int attackPower, boolean isGrounded){
        this(name, health, attackPower);
        this.isGrounded = isGrounded;
    }

    public String getName() {
        return name;
    }

    private void setName(String name){
        if(name.matches(".*\\d.*")){
            throw new IllegalStateException("No numbers allowed in name");
        }
        this.name = name;
    }

    private boolean isGrounded(){
        return isGrounded;
    }

    public int getHealth() {
        return health;
    }
    private void setHealth(int health){
        this.health = health;
    }

    private boolean isDead(){
        if(health == 0){
            return true;
        }else{
            return false;
        }
    }

    private int getAttackPower() {
        return attackPower;
    }

    private void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public boolean Attack(Monster enemy){
        if(isGrounded() && !enemy.isGrounded() || enemy.isDead() || isDead()){
            return false;
        }
        enemy.takeDamage(getAttackPower());
        return true;
    }

    private void takeDamage(int damage){
        int tempHealth = getHealth() - damage;
        if(tempHealth < 0){
            setHealth(0);
        }else {
            setHealth(tempHealth);
        }
    }

}