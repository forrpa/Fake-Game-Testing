// @author Christoffer Öhman
package magic;

import player.Player;
import unit.Combatant;
import unit.Unit;

class Spell {
    private final String description;
    private final String name;
    private int manaCost = 50;
    private int requiredMagicLevel = 5;
    private int cooldownDuration = 0;

    // TODO: 2020-10-20 implement "damage interface"

    public Spell(String name, String description, int manaCost, int requiredMagicLevel, int cooldownDuration) {
        this.name = name;
        this.description = description;
        setManaCost (manaCost);
        setRequiredMagicLevel (requiredMagicLevel);
        setCooldownDuration (cooldownDuration);
    }

    public Spell() {

        // might randomize names and descriptions.
        name = "Unnamed spell";
        description = "Your ninny brain can not comprehend what this spell does.";
    }
    // TODO: 2020-10-16 add isCastable and coolDown Method.


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

    // Check that numbers are >=0
    private int positiveNrCheck(int nr) {
        if (nr >= 0) {
            return nr;
        } else {
            throw new IllegalArgumentException ("Error: negative numbers are not allowed here");
        }
    }


    public boolean castSpell(Unit caster ,Unit target) {
        return false;
    }

}


