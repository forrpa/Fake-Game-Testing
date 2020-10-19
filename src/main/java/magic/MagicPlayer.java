//@author Christoffer Öhman
package magic;

import player.Player;

import java.util.HashMap;
import java.util.Map;

// would be good if all units including player inherit from same "entity" or "unit"class.
public class MagicPlayer extends Player {
    final private Map<String, Spell> spellBook = new HashMap<> ();
    final private Map<String, Spell> learntSpells = new HashMap<> ();
    private int mana = 1;
    private int MaxMana = 5;

    // create magicLevel = MagicExp /10000 and NN,?

    // override levelup mechanics  from super?
    private int manaRegenSpeed = 1;
    private int magicLevel = 1;
    private int maximumLearntSpells = 1;

    // TODO: 2020-10-19 remove manaPoint from player class when done testing this class.
    public MagicPlayer(String playerClass, String race, int manaPoint, int healthPoint, int experiencePoint, int mana) {
        super (playerClass, race, manaPoint, healthPoint, experiencePoint);
        this.mana = mana;
    }

    public int getMaximumLearntSpells() {
        return maximumLearntSpells;
    }

    public void setMaximumLearntSpells(int maximumLearntSpells) {
        this.maximumLearntSpells = maximumLearntSpells;
    }

    public void addSpell(Spell spell) {

        spellBook.put (spell.getName (), spell);
    }

    public void removeSpell(Spell spell) {
        spellBook.remove (spell.getName ());
    }

    public Spell getSpell(String name) {
        return spellBook.get (name);
    }


    public void learnSpell() {
        // fix

    }

    public void castSpell() {
        // maybe make this an interface, different for every class?

        // check isCastable. /
        // get manaCost and check if enough.


        // check for magicObjects that effect spell.
        // cast spell
        //remove mana
        // add Magic exp
    }

    // what happens when leveling up     .
    private void levelUp() {
        // max mana level

    }

    // what happens when leveling down.
    private void levelDown() {


    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
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
        this.magicLevel = magicLevel;
    }

    public int getMaxMana() {
        return MaxMana;
    }

    public void setMaxMana(int maxMana) {
        this.MaxMana = maxMana;
    }
    // increaseMagicLevel // required level to learn and use spells and to wield magic equipment.
    //IncreaseMaxLearntSpells // different classes can learn different spells and memorize different amount of spells.


}
