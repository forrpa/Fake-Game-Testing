//@author Christoffer Öhman
package magic;

import player.Player;
import unit.Unit;

import java.util.HashMap;
import java.util.Map;

import static magic.ValueCheck.numberCheck;

public class MagicPlayer extends Player {
    final protected Map<String, Spell> spellBook = new HashMap<> ();
    final protected Map<String, Spell> learntSpells = new HashMap<> ();
    protected int manaPoint = 1;
    protected int maxManaPoint = 5;

    protected int magicSkill = 1 + (getIntelligence () / 5);
    private int maximumLearnableSpells = 1 + (getIntelligence () / 2);


    public MagicPlayer(String playerClass, String race, int healthPoint, int experiencePoint) {
        super (playerClass, race, healthPoint, experiencePoint);
    }

    public int getMaximumLearnableSpells() {
        return maximumLearnableSpells;
    }

    public void setMaximumLearnableSpells(int maximumLearnableSpells) {
        this.maximumLearnableSpells = numberCheck (maximumLearnableSpells);
    }

    //Add spell to spellBook.
    public void addSpell(Spell spell) {

        spellBook.put (spell.getName (), spell);
    }

    //Remove spell from spellBook
    public Spell removeSpell(String name) {
        return spellBook.remove (name);
    }

    public Spell getSpell(String name) {
        return spellBook.get (name);
    }

    // Adds spell to learntSpells and SpellBook
    public boolean learnSpell(Spell spell) {
        String name = spell.getName ();
        int requiredMagicSkill = spell.getRequiredMagicSkill ();


        if ((learntSpells.size () < maximumLearnableSpells) && (magicSkill >= requiredMagicSkill)) {

            learntSpells.put (name, spell);
            addSpell (spell);
            return true;
        } else {
            return false;
        }
    }

    // Removes spell from learntSpells
    public Spell unLearnSpell(String name) {
        return learntSpells.remove (name);
    }


    public boolean castSpell(Spell spell, Unit target) {
        String spellName = spell.getName ();
        int manaCost = spell.getManaCost ();


        if (manaPoint >= manaCost && learntSpells.get (spellName) == spell) {
            manaPoint -= manaCost;
            return spell.castSpell (this, target);
        } else {
            return false;
        }
    }


    public int getManaPoint() {
        return manaPoint;
    }

    public void setManaPoint(int manaPoint) {

        if (numberCheck (manaPoint) > maxManaPoint) {
            manaPoint = maxManaPoint;
        }
        this.manaPoint = manaPoint;
    }

    public int getMaxManaPoint() {
        return maxManaPoint;
    }

    public void setMaxManaPoint(int maxManaPoint) {
        this.maxManaPoint = numberCheck (maxManaPoint);
    }

    public int getMagicSkill() {
        return magicSkill;
    }

    public void setMagicSkill(int magicSkill) {
        final int maximumMagicSkill = 10;
        if (numberCheck (magicSkill) > maximumMagicSkill) {
            throw new IllegalArgumentException ("Error: value is too high");
        } else {
            this.magicSkill = magicSkill;
        }
    }

}
