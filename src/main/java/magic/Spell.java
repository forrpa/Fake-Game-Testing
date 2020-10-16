// @author Christoffer Öhman
package magic;

public class Spell {
    private final String description;
    private final String name;
    private int manaCost;
    private final int requiredMagicLevel;
    private int cooldownDuration;


    public  Spell(String name, String description, int manaCost, int requiredMagicLevel, int cooldownDuration) {
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

