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
        itemsOnNPC = new ArrayList<>();
        if(attackPower < 0){
            this.attackPower = 0;
        }else {
            this.attackPower = attackPower;
        }

    }
    public Monster(String name, int maxHealth, int attackPower, boolean isGrounded, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        this(name, maxHealth, attackPower, isGrounded, resistance, weakness);
        itemsOnNPC.addAll(items);
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

    public String getAllAvailableLoot(){
        String allItems = "";
        if(itemsOnNPC.size() > 0){
            StringBuilder sb = new StringBuilder();
            for(Item item : itemsOnNPC){
                sb.append(item.getName()).append(", ");
            }
            allItems = sb.deleteCharAt(sb.length() - 2).toString().trim();
        }
        return allItems;
    }

    @Override
    public String toString(){
        String toStringString = String.format("Name: %s, Max Health: %d, Current Health: %d, Attack Power: %d, Resistance: %s, Weakness %s.", getName(), getMaxHealth(), getHealthPoint(), getAttackPower(), getResistance(), getWeakness());
        if(!getAllAvailableLoot().isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append(toStringString).deleteCharAt(toStringString.length()-1);
            sb.append(String.format(", Loot: %s.", getAllAvailableLoot()));
            toStringString = sb.toString();
        }
        return toStringString;
    }
}
