package unit;

import java.util.ArrayList;
import java.util.List;

import item.*;

public abstract class Monster extends NPC {
    private ArrayList<Item> itemsOnNPC;

    public Monster(String name, int maxHealth, int attackPower,int experiencePoints, AttackType resistance, AttackType weakness){
        this(name, maxHealth, attackPower, experiencePoints, true, resistance, weakness);
    }
    public Monster(String name, int maxHealth, int attackPower, int experiencePoints, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        this(name, maxHealth, attackPower, experiencePoints, true, items, resistance, weakness);
    }
    public Monster(String name, int maxHealth, int attackPower, int experiencePoints, boolean isGrounded, AttackType resistance, AttackType weakness){
        super(name, maxHealth, attackPower, experiencePoints, isGrounded, resistance, weakness);
        itemsOnNPC = new ArrayList<>();

    }
    public Monster(String name, int maxHealth, int attackPower, int experiencePoints, boolean isGrounded, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        this(name, maxHealth, attackPower, experiencePoints, isGrounded, resistance, weakness);
        itemsOnNPC.addAll(items);
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
        ArrayList<Item> lootedItems = new ArrayList<>();
        if(isLootable()){
            lootedItems.addAll(itemsOnNPC);
            itemsOnNPC.clear();
        }
        return lootedItems;
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
        String toStringString = String.format("Name: %s, Max Health: %d, Current Health: %d, Attack Power: %d, Resistance: %s, Weakness %s.", getName(), getMaxHealthPoint(), getHealthPoint(), getAttackPower(), getResistance(), getWeakness());
        if(!getAllAvailableLoot().isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append(toStringString).deleteCharAt(toStringString.length()-1);
            sb.append(String.format(", Loot: %s.", getAllAvailableLoot()));
            toStringString = sb.toString();
        }
        return toStringString;
    }
}
