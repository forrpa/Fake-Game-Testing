package unit;

import java.util.ArrayList;

public class Bat extends Monster {

    public Bat(){
        this("Bat", 5, 2);
    }
    public Bat(ArrayList<Item> items){
        this("Bat", 5, 2, items);
    }
    public Bat(String name, int health, int attackPower){
        super(name, health, attackPower, false);
    }
    public Bat(String name, int health, int attackPower, ArrayList<Item> items){
        super(name, health, attackPower, false, items);
    }
}
