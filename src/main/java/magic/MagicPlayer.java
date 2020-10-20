//@author Christoffer Öhman
package magic;
import magic.Spell;
import player.Player;
import java.util.HashMap;
import java.util.Map;

// would be good if all units including player inherit from same "entity" or "unit"class.
public class MagicPlayer extends Player {
    final protected   Map<String, Spell> spellBook = new HashMap<> ();
    final private Map<String, Spell> learntSpells = new HashMap<> ();
    private int manaPoint = 1;
    private int MaxManaPoint = 5;

    // create magicLevel = MagicExp /10000 and NN,?

    // override levelup mechanics  from super?
    private int manaRegenSpeed = 1;
    private int magicLevel = 1;
    private int maximumLearnableSpells = 1;

    // TODO: 2020-10-19 remove manaPoint from player class when done testing this class.
    public MagicPlayer(String playerClass, String race,int healthPoint, int experiencePoint) {

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

    public void removeSpell(String name) {
        spellBook.remove (name);
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
        int maximumLevel = 10;
        if (magicLevel <=10 ){
            this.magicLevel= positiveNrCheck (magicLevel);
        }else{
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
