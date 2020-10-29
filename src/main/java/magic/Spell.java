// @author Christoffer Öhman
package magic;

import unit.Unit;

import static magic.ValueCheck.numberCheck;
import static magic.ValueCheck.stringCheck;

//SuperClass representing a castable spell.
@SuppressWarnings("ALL")
class Spell {
    private final String description;
    private final String name;
    private final boolean isOnCooldown = false;
    private int manaCost = 50;
    private int requiredMagicSkill = 5;
    private int cooldownDuration = 0;
    // implement isOnCooldown in castSpell.


    public Spell(String name, String description, int manaCost, int requiredMagicSkill, int cooldownDuration) {
        this.name = stringCheck (name);
        this.description = stringCheck (description);
        setManaCost (numberCheck (manaCost));
        setRequiredMagicSkill (numberCheck (requiredMagicSkill));
        setCooldownDuration (numberCheck (cooldownDuration));
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

    public void setManaCost(int manaCost) {
        this.manaCost = numberCheck (manaCost);
    }

    public int getRequiredMagicSkill() {
        return requiredMagicSkill;
    }

    public void setRequiredMagicSkill(int requiredMagicSkill) {
        this.requiredMagicSkill = numberCheck (requiredMagicSkill);
    }

    public int getCooldownDuration() {
        return cooldownDuration;
    }

    public void setCooldownDuration(int cooldownDuration) {
        this.cooldownDuration = numberCheck (cooldownDuration);
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




}
