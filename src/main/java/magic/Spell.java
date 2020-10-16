// @author Christoffer Öhman
package magic;

public class Spell {
    private final String description;
    private final String name;
    private int manaCost = 50;
    private  int requiredMagicLevel =5;
    private int cooldownDuration =10;


    public  Spell(String name, String description, int manaCost, int requiredMagicLevel, int cooldownDuration) {
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.requiredMagicLevel = requiredMagicLevel;
        this.cooldownDuration = cooldownDuration;
    }

    public Spell (){

        // might randomize names and descriptions.
        name = "Unnamed spell";
        description ="Your ninny brain can not comprehend what this spell does.";
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


    public void setManaCost(int manaCost) {
        this.manaCost = positiveNrCheck (manaCost);
    }

    public void setRequiredMagicLevel(int requiredMagicLevel) {
        this.requiredMagicLevel = positiveNrCheck (requiredMagicLevel);
    }

    public void setCooldownDuration(int cooldownDuration) {
        this.cooldownDuration = positiveNrCheck (cooldownDuration);
    }

    // Checks that numbers are
    private int positiveNrCheck(int nr){
        if (nr >= 0) {
            return nr;
        } else {
            throw new IllegalArgumentException ("Error: negative natural numbers are not allowed");
        }

    }
}


