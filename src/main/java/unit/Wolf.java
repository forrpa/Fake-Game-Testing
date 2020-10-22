package unit;

import java.util.ArrayList;
import item.*;

public class Wolf extends Monster{
    private final static int STANDARD_WOLF_HEALTH = 8;
    private final static int STANDARD_WOLF_ATTACKPOWER = 3;
    private final static int STANDARD_WOLF_EXPERIENCEPOINTS = 100;

    public Wolf(){
        this("Wolf", STANDARD_WOLF_HEALTH, STANDARD_WOLF_ATTACKPOWER, STANDARD_WOLF_EXPERIENCEPOINTS, null, null);
    }
    public Wolf(ArrayList<Item> items){
        this("Wolf", STANDARD_WOLF_HEALTH, STANDARD_WOLF_ATTACKPOWER, STANDARD_WOLF_EXPERIENCEPOINTS, items, null, null);
    }
    public Wolf(String name, int health, int attackPower, int experiencePoints, AttackType resistance, AttackType weakness){
        super(name, health, attackPower, experiencePoints, resistance, weakness);
    }
    public Wolf(String name, int health, int attackPower, int experiencePoints, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        super(name, health, attackPower, experiencePoints, items, resistance, weakness);
    }


}
