package unit;

public class NPC extends Unit {
    private String name;

    public NPC (String name, int maxHealth, boolean isGrounded, AttackType resistance, AttackType weakness){
        super(maxHealth, isGrounded, resistance, weakness);
        if(name.matches(".*\\d.*")){
            throw new IllegalArgumentException(("No numbers allowed in name"));
        }else {
            this.name = name;
        }
    }
    public String getName() {
        return name;
    }
}
