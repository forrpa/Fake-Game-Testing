package unit;

import java.util.ArrayList;

public class Bat extends Monster {
    final static int STANDARD_BAT_HEALTH = 5;
    final static int STANDARD_BAT_ATTACKPOWER = 2;

    public Bat(){
        this("Bat", STANDARD_BAT_HEALTH, STANDARD_BAT_ATTACKPOWER);
    }
    public Bat(ArrayList<Item> items){
        this("Bat", STANDARD_BAT_HEALTH, STANDARD_BAT_ATTACKPOWER, items);
    }
    public Bat(String name, int health, int attackPower){
        super(name, health, attackPower, false);
    }
    public Bat(String name, int health, int attackPower, ArrayList<Item> items){
        super(name, health, attackPower, false, items);
    }
}
