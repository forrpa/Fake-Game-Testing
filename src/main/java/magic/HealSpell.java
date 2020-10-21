// @author Christoffer Öhman
package magic;


import player.Player;

public class HealSpell extends Spell {
    int healthPointsToHeal;


    public HealSpell(String name, String description, int manaCost, int requiredMagicLevel, int cooldownDuration, int healthPointsToHeal) {
        super (name, description, manaCost, requiredMagicLevel, cooldownDuration);
        this.healthPointsToHeal = healthPointsToHeal;
    }

    @Override
    public boolean castSpell(Player target) {
        int HP = target.getHealthPoint () + healthPointsToHeal;
        target.setHealthPoint (HP);

        // todo implement castSpell interface in spell?  or move spellcasting to own Class/handler.
        return false;

    }


}
