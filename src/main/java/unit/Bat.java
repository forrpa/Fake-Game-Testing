package main.java.se.su.dsv.projektarbete.unit;

public class Bat extends Monster {

    public Bat(){
        this("Bat", 5, 2);
    }
    public Bat(String name, int health, int attackPower){
        super(name, health, attackPower, false);
    }
}
