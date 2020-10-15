package christoffersKod;

public class Spell {
    private String description;
    private String name;
    private int manaCost;
    private int requiredMagicLevel;
    private int cooldownDuration;


    public Spell(String name, String description, int manaCost, int requiredMagicLevel, int cooldownDuration) {
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.requiredMagicLevel = requiredMagicLevel;
        this.cooldownDuration = cooldownDuration;
    }


    public String getName() {
        return name;
    }



    public String getDescription() {
        return description;
    }



    public int getManaCost() {
        return manaCost;
    }



    public int getRequiredMagicLevel() {
        return requiredMagicLevel;
    }



    public int getCooldownDuration() {
        return cooldownDuration;
    }


}

