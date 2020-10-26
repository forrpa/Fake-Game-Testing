// @author Christoffer Öhman
package magic;

import unit.Unit;

//SuperClass representing a castable spell.
class Spell {
    private final String description;
    private final String name;
    private int manaCost = 50;
    private int requiredMagicSkill = 5;
    private int cooldownDuration = 0;
    private final boolean isOnCooldown = false;
    // implement isOnCooldown in castSpell.

    // TODO: 2020-10-20 implement "damage interface"

    public Spell(String name, String description, int manaCost, int requiredMagicSkill, int cooldownDuration) {
        this.name = name;
        this.description = description;
        setManaCost (manaCost);
        setRequiredMagicSkill (requiredMagicSkill);
        setCooldownDuration (cooldownDuration);
    }

    public Spell() {

        name = "Unnamed spell";
        description = "Your ninny brain can not comprehend what this spell does.";
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


    public int getRequiredMagicSkill() {
        return requiredMagicSkill;
    }


    public int getCooldownDuration() {
        return cooldownDuration;
    }


    public void setManaCost(int manaCost) {
        this.manaCost = positiveNrCheck (manaCost);
    }

    public void setRequiredMagicSkill(int requiredMagicSkill) {
        this.requiredMagicSkill = positiveNrCheck (requiredMagicSkill);
    }

    public void setCooldownDuration(int cooldownDuration) {
        this.cooldownDuration = positiveNrCheck (cooldownDuration);
    }

    public boolean castSpell(Unit caster, Unit target) {

        // if enough time do:
        // if isOnCooldown return
        // if cooldownDuration !=0 set isOncooldown

        return magicEffect (caster, target);
    }
// should be overrun in subClass

    boolean magicEffect(Unit caster, Unit target) {
        return false;
    }


    // Check if  numbers are >=0
    private int positiveNrCheck(int nr) {
        if (nr >= 0) {
            return nr;
        } else {
            throw new IllegalArgumentException ("Error: negative numbers are not allowed here");
        }
    }

    // Check if Strings are not blank, empty or null.
    private void stringCheck(String string) {
        if (string == null || string.trim ().isEmpty ()) {
            throw new IllegalArgumentException ("Error: a blank string is not allowed here");
        }
        // use param test?
    }


}

