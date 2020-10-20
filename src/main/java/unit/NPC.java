package unit;

public class NPC {
    private String name;

    public NPC (String name){
        if(name.matches(".*\\d.*")){
            throw new IllegalArgumentException(("No numbers allowed in name"));
        }
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
