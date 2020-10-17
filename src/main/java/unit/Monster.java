package unit;

import java.util.ArrayList;
import java.util.List;

public abstract class Monster {
    private String name;
    private boolean isGrounded;
    private int health;
    private int attackPower;
    private ArrayList<Item> itemsOnMonster;

    public Monster(String name, int health, int attackPower){
        this(name, health, attackPower, true);
    }
    public Monster(String name, int health, int attackPower, ArrayList<Item> items){
        this(name, health, attackPower, true, items);
    }

    public Monster(String name, int health, int attackPower, boolean isGrounded){
        setName(name);
        setHealth(health);
        setAttackPower(attackPower);
        this.isGrounded = isGrounded;
    }

    public Monster(String name, int health, int attackPower, boolean isGrounded, ArrayList<Item> items){
        this(name, health, attackPower, isGrounded);
        itemsOnMonster = items;
    }

    public String getName() {
        return name;
    }

    private void setName(String name){
        if(name.matches(".*\\d.*")){
            throw new IllegalArgumentException(("No numbers allowed in name"));
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

    public boolean isDead(){
        return (health == 0);

    }

    private int getAttackPower() {
        return attackPower;
    }

    private void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public boolean attack(Monster enemy){
        if(isDead()){
            throw new IllegalStateException("The attacking unit is dead");
        }else if(enemy.isDead()){
            throw new IllegalStateException("The unit receiving damage is already dead");
        }else if(isGrounded() && !enemy.isGrounded()){
            return false;
        }else {
            enemy.takeDamage(getAttackPower());
            return true;
        }
    }

    private void takeDamage(int damage){
        int tempHealth = getHealth() - damage;
        if(tempHealth < 0){
            setHealth(0);
        }else {
            setHealth(tempHealth);
        }
    }

    public List<Item> getLooted(){
        throw new IllegalStateException("Monster can't be looted unless dead");
    }
}
