//@author Christoffer Öhman
package magic;

import player.Player;

import java.util.HashMap;
import java.util.Map;

// would be good if all units including player inherit from same "entity" or "unit"class.
public class MagicPlayer extends Player {
    final protected Map<String, Spell> spellBook = new HashMap<> ();
    final protected Map<String, Spell> learntSpells = new HashMap<> ();
    private int manaPoint = 1;
    private int MaxManaPoint = 5;

    // create magicLevel = MagicExp /10000 and NN,?

    // override levelup mechanics  from super?
    private int manaRegenSpeed = 1;
    private int magicLevel = 1; // remove 
    private int maximumLearnableSpells = 1;

    // TODO: 2020-10-21 Ta bort MagicLevel och byt ut det till level. mindre komplext.

    public MagicPlayer(String playerClass, String race, int healthPoint, int experiencePoint) {

        super (playerClass, race, healthPoint, experiencePoint);
    }

    public int getMaximumLearnableSpells() {
        return maximumLearnableSpells;
    }

    public void setMaximumLearnableSpells(int maximumLearnableSpells) {
        this.maximumLearnableSpells = positiveNrCheck (maximumLearnableSpells);
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


    public void learnSpell(Spell spell) {
        String name = spell.getName ();

        if (learntSpells.size () >= maximumLearnableSpells) {
            return;
        }
        learntSpells.put (name, spell);
        addSpell (spell);
    }

    public Spell unLearnSpell(String name) {
        return learntSpells.remove (name);
    }


    public boolean castSpell(Spell spell) {
        // maybe make this an interface, different for every class?
        return false;
    }

    // what happens when leveling up     .
    private void levelUp() {
        // max mana level
    }

    // what happens when leveling down.
    private void levelDown() {
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public void setManaPoint(int manaPoint) {
        this.manaPoint = manaPoint;
    }

    public int getManaRegenSpeed() {
        return manaRegenSpeed;
    }

    public void setManaRegenSpeed(int manaRegenSpeed) {
        this.manaRegenSpeed = manaRegenSpeed;
    }

    public int getMagicLevel() {
        return magicLevel;
    }

    public void setMagicLevel(int magicLevel) {
        final int maximumMagicLevel = 10;
        if (magicLevel <= maximumMagicLevel) {
            this.magicLevel = positiveNrCheck (magicLevel);
        } else {
            throw new IllegalArgumentException ("Error: negative numbers are not allowed here");
        }
    }

    public int getMaxManaPoint() {
        return MaxManaPoint;
    }

    public void setMaxManaPoint(int maxManaPoint) {
        this.MaxManaPoint = maxManaPoint;
    }

    // Check that numbers are >=0
    private int positiveNrCheck(int nr) {
        if (nr >= 0) {
            return nr;
        } else {
            throw new IllegalArgumentException ("Error: negative numbers are not allowed here");
        }
    }
    // increaseMagicLevel // required level to learn and use spells and to wield magic equipment.
    //IncreaseMaxLearntSpells // different classes can learn different spells and memorize different amount of spells.
}
