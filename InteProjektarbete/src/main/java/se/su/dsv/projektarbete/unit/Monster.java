package main.java.se.su.dsv.projektarbete.unit;

public abstract class Monster {
    private String name;
    private boolean isGrounded;
    private int health;

    public Monster(String name){
        setName(name);
        this.isGrounded = true;
    }

    public Monster(String name, boolean isGrounded){
        setName(name);
        this.isGrounded = isGrounded;
    }

    public String getName() {
        return name;
    }

    private void setName(String name){
        if(name.matches(".*\\d.*")){
            throw new IllegalStateException("No numbers allowed in name");
        }
        this.name = name;
    }

    public boolean isGrounded(){
        return isGrounded;
    }

    public int getHealth() {
        return health;
    }
    private void setHealth(int health){
        this.health = health;
    }

    public boolean Attack(Monster enemy){
        if(isGrounded() && !enemy.isGrounded()){
            return false;
        }
        enemy.setHealth(6);
        return true;
    }

}
