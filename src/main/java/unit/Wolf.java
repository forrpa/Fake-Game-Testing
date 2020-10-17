package unit;

public class Wolf extends Monster {
    public Wolf(){
        this("Wolf", 8, 3);
    }
    public Wolf(String name, int health, int attackPower){
        super(name, health, attackPower);
    }

}
