package main.java.se.su.dsv.projektarbete.unit;

public abstract class Monster {
    private String name;
    private boolean isGrounded;

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

    public boolean isGrounded(){
        return isGrounded;
    }

    private void setName(String name){
        if(name.matches(".*\\d.*")){
            throw new IllegalStateException("No numbers allowed in name");
        }
        this.name = name;
    }

    public boolean Attack(Monster enemy){
        if(isGrounded() && !enemy.isGrounded()){
            return false;
        }
        return true;
    }
}
