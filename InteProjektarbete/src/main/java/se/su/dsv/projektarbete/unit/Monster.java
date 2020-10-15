package main.java.se.su.dsv.projektarbete.unit;

public class Monster {
    private String name;

    public Monster(String name){
        setName(name);
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
}
