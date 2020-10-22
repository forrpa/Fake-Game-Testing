package unit;

import item.*;
import java.util.ArrayList;

public class Bat extends Monster {
    private final static int STANDARD_BAT_HEALTH = 5;
    private final static int STANDARD_BAT_ATTACKPOWER = 2;
    private final static int STANDARD_BAT_EXPERIENCEPOINTS = 50;

    public Bat(){
        this("Bat", STANDARD_BAT_HEALTH, STANDARD_BAT_ATTACKPOWER, STANDARD_BAT_EXPERIENCEPOINTS, null, null);
    }
    public Bat(ArrayList<Item> items){
        this("Bat", STANDARD_BAT_HEALTH, STANDARD_BAT_ATTACKPOWER, STANDARD_BAT_EXPERIENCEPOINTS, items, null, null);
    }
    public Bat(String name, int health, int attackPower, int experiencePoints, AttackType resistance, AttackType weakness){
        super(name, health, attackPower, experiencePoints, false, resistance, weakness);
    }
    public Bat(String name, int health, int attackPower, int experiencePoints, ArrayList<Item> items, AttackType resistance, AttackType weakness){
        super(name, health, attackPower, experiencePoints, false, items, resistance, weakness);
    }

    public void getGrounded(){
        if(isAlive()) {
            isGrounded = true;
        }
    }

    public void getOffGround(){
        if(isAlive()) {
            isGrounded = false;
        }
    }
}
