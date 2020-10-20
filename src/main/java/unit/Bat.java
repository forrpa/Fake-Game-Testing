package unit;

import item.*;
import java.util.ArrayList;

public class Bat extends Monster {
    private final static int STANDARD_BAT_HEALTH = 5;
    private final static int STANDARD_BAT_ATTACKPOWER = 2;

    public Bat(){
        this("Bat", STANDARD_BAT_HEALTH, STANDARD_BAT_ATTACKPOWER, null, null);
    }
    public Bat(ArrayList<Item> items){
        this("Bat", STANDARD_BAT_HEALTH, STANDARD_BAT_ATTACKPOWER, items, null, null);
    }
    public Bat(String name, int health, int attackPower, AttackType resistance, AttackType weakness){
        super(name, health, attackPower, false, resistance, weakness);
    }
    public Bat(String name, int health, int attackPower, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        super(name, health, attackPower, false, items, resistance, weakness);
    }
}
