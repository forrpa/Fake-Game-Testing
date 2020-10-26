//@author Christoffer Öhman
package magic;

import player.Player;
import unit.Unit;

import java.util.HashMap;
import java.util.Map;

public class MagicPlayer extends Player {
    final protected Map<String, Spell> spellBook = new HashMap<> ();
    final protected Map<String, Spell> learntSpells = new HashMap<> ();
    protected int manaPoint = 1;
    protected int maxManaPoint = 5;

    protected int magicSkill = 1; // remove? or factor with player.level
    private int maximumLearnableSpells = 1;

    // TODO: 2020-10-21 Ta bort MagicLevel och byt ut det till level. mindre komplext.

    public MagicPlayer(String playerClass, String race, int healthPoint, int experiencePoint) {

        super (playerClass, race, healthPoint, experiencePoint);
    }

    public int getMaximumLearnableSpells() {
        return maximumLearnableSpells;
    }

    public void setMaximumLearnableSpells(int maximumLearnableSpells) {
        this.maximumLearnableSpells = Check.numberCheck (maximumLearnableSpells);
    }

    public void addSpell(Spell spell) {

        spellBook.put (spell.getName (), spell);
    }

    public Spell removeSpell(String name) {
        return spellBook.remove (name);
    }

    public Spell getSpell(String name) {
        return spellBook.get (name);
    }


    public boolean learnSpell(Spell spell) {
        String name = spell.getName ();
        int requiredMagicSkill = spell.getRequiredMagicSkill ();

        if (learntSpells.size () >= maximumLearnableSpells || magicSkill < requiredMagicSkill) {
            return false;
        }
        learntSpells.put (name, spell);
        addSpell (spell);
        return true;
    }

    public Spell unLearnSpell(String name) {
        return learntSpells.remove (name);
    }


    public boolean castSpell(Spell spell, Unit target) {
        String spellName = spell.getName ();
        int manaCost = spell.getManaCost ();
        if (manaPoint < manaCost || learntSpells.get (spellName) != spell) {
            return false;
        }
        manaPoint -= manaCost;
        return spell.castSpell (this, target);
    }


    public int getManaPoint() {
        return manaPoint;
    }

    public void setManaPoint(int manaPoint) {

        if (Check.numberCheck (manaPoint) > maxManaPoint) {
            manaPoint = maxManaPoint;
        }
        this.manaPoint = manaPoint;
    }

    public int getMaxManaPoint() {
        return maxManaPoint;
    }

    public void setMaxManaPoint(int maxManaPoint) {
        this.maxManaPoint = Check.numberCheck (maxManaPoint);
    }

    public int getMagicSkill() {
        return magicSkill;
    }

    public void setMagicSkill(int magicSkill) {
        final int maximumMagicSkill = 10;
        if (magicSkill <= maximumMagicSkill) {
            this.magicSkill = Check.numberCheck (magicSkill);
        } else {
            throw new IllegalArgumentException ("Error: negative numbers are not allowed here");
        }
    }




    // what happens when leveling up   // override player mec.  .


    // what happens when leveling down. // override player mec.

}
// increaseMagicLevel // required level to learn and use spells and to wield magic equipment.
//IncreaseMaxLearntSpells // different classes can learn different spells and memorize different amount of spells.
