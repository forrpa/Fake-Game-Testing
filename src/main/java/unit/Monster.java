package unit;

import java.util.ArrayList;
import java.util.List;

import item.*;

public abstract class Monster extends NPC implements Combatant {
    private int attackPower;
    private ArrayList<Item> itemsOnNPC;

    public Monster(String name, int maxHealth, int attackPower, AttackType resistance, AttackType weakness){
        this(name, maxHealth, attackPower, true, resistance, weakness);
    }
    public Monster(String name, int maxHealth, int attackPower, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        this(name, maxHealth, attackPower, true, items, resistance, weakness);
    }
    public Monster(String name, int maxHealth, int attackPower, boolean isGrounded, AttackType resistance, AttackType weakness){
        super(name, maxHealth, isGrounded, resistance, weakness);
        if(attackPower < 0){
            this.attackPower = 0;
        }else {
            this.attackPower = attackPower;
        }

    }
    public Monster(String name, int maxHealth, int attackPower, boolean isGrounded, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        this(name, maxHealth, attackPower, isGrounded, resistance, weakness);
        itemsOnNPC = items;
    }

    private int getAttackPower() {
        return attackPower;
    }

    public boolean attack(Combatant enemy){
        if(!isAlive()){
            throw new IllegalStateException("The attacking unit is dead");
        }else if(!enemy.isAlive()){
            throw new IllegalStateException("The unit receiving damage is already dead");
        }else if(isGrounded() && !enemy.isGrounded()){
            return false;
        }else {
            enemy.takeDamage(new Attack(getAttackPower()));
            return true;
        }
    }

    public void takeDamage(Attack attack){
        int damage = attack.getAttackPower(getResistance(), getWeakness());
        int tempHealth = getHealthPoint() - damage;
        if(tempHealth < 0){
            setHealthPoint(0);
        }else {
            setHealthPoint(tempHealth);
        }
    }

    private boolean isLootable() {
        if (isAlive()) {
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

    @Override
    public String toString(){
        return String.format("Name: %s, Max Health: %i, Current Health: %i, Attack Power: %i, Resistance: %s, Weakness %s.", getName(), getMaxHealth(), getHealthPoint(), getAttackPower(), getResistance(), getWeakness());
    }
}
