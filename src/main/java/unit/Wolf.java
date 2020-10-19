package unit;

public class Wolf extends Monster{
    final static int STANDARD_WOLF_HEALTH = 8;
    final static int STANDARD_WOLF_ATTACKPOWER = 3;

    public Wolf(){
        this("Wolf", STANDARD_WOLF_HEALTH, STANDARD_WOLF_ATTACKPOWER);
    }
    public Wolf(String name, int health, int attackPower){
        super(name, health, attackPower);
    }


}
