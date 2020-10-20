package unit;

import java.util.ArrayList;
import item.*;

public class Wolf extends Monster{
    private final static int STANDARD_WOLF_HEALTH = 8;
    private final static int STANDARD_WOLF_ATTACKPOWER = 3;

    public Wolf(){
        this("Wolf", STANDARD_WOLF_HEALTH, STANDARD_WOLF_ATTACKPOWER, null, null);
    }
    public Wolf(ArrayList<Item> items){
        this("Wolf", STANDARD_WOLF_HEALTH, STANDARD_WOLF_ATTACKPOWER, items, null, null);
    }
    public Wolf(String name, int health, int attackPower, AttackType resistance, AttackType weakness){
        super(name, health, attackPower, resistance, weakness);
    }
    public Wolf(String name, int health, int attackPower, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        super(name, health, attackPower, items, resistance, weakness);
    }


}
