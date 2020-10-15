package main.java.se.su.dsv.projektarbete.unit;

public class Monster {
    private String name;
    private boolean isGrounded;

    public Monster(String name){
        setName(name);
        if(name.equals("Bat")){ //NEED TO SUBCLASS
            isGrounded = false;
        }else{
            isGrounded = true;
        }
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
