package unit;

import java.util.ArrayList;
import java.util.List;

import item.*;

public abstract class Monster extends NPC implements Combatant {
    private boolean isGrounded;
    private int health;
    private int maxHealth;
    private int attackPower;
    private ArrayList<Item> itemsOnNPC;
    private AttackType resistance;
    private AttackType weakness;

    public Monster(String name, int maxHealth, int attackPower, AttackType resistance, AttackType weakness){
        this(name, maxHealth, attackPower, true, resistance, weakness);
    }
    public Monster(String name, int maxHealth, int attackPower, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        this(name, maxHealth, attackPower, true, items, resistance, weakness);
    }
    public Monster(String name, int maxHealth, int attackPower, boolean isGrounded, AttackType resistance, AttackType weakness){
        super(name);
        setHealth(maxHealth);
        if(attackPower < 0){
            this.attackPower = 0;
        }else {
            this.attackPower = attackPower;
        }
        this.maxHealth = maxHealth;
        this.isGrounded = isGrounded;
        this.resistance = resistance;
        this.weakness = weakness;
    }
    public Monster(String name, int maxHealth, int attackPower, boolean isGrounded, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        this(name, maxHealth, attackPower, isGrounded, resistance, weakness);
        itemsOnNPC = items;
    }

    public boolean isGrounded(){
        return isGrounded;
    }

    public int getHealth() {
        return health;
    }
    private void setHealth(int health){
        if(health < 0){
            this.health = 0;
        }else {
            this.health = health;
        }
    }

    public boolean isDead(){
        return (health == 0);

    }

    private int getAttackPower() {
        return attackPower;
    }

    public boolean attack(Combatant enemy){
        if(isDead()){
            throw new IllegalStateException("The attacking unit is dead");
        }else if(enemy.isDead()){
            throw new IllegalStateException("The unit receiving damage is already dead");
        }else if(isGrounded() && !enemy.isGrounded()){
            return false;
        }else {
            enemy.takeDamage(new Attack(getAttackPower()));
            return true;
        }
    }

    public void takeDamage(Attack attack){
        int damage = attack.getAttackPower(resistance, weakness);
        int tempHealth = getHealth() - damage;
        if(tempHealth < 0){
            setHealth(0);
        }else {
            setHealth(tempHealth);
        }
    }

    private boolean isLootable() {
        if (!isDead()) {
            throw new IllegalStateException("Monster can't be looted unless dead");
        } else if (itemsOnNPC == null || itemsOnNPC.isEmpty()) {
            throw new IllegalStateException("No items on monster");
        }else{
            return true;
        }
    }

    public List<Item> getLooted(){
        if(isLootable()){
            ArrayList<Item> lootedItems = new ArrayList<>();
            lootedItems.addAll(itemsOnNPC);
            itemsOnNPC.clear();
            return lootedItems;

        }else{
            return null;
        }
    }
}
