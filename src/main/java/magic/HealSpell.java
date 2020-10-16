// @author Christoffer Öhman
package magic;


import player.Player;

public class HealSpell extends Spell {
    int healthPointsToHeal;


    public HealSpell(String name, String description, int manaCost, int requiredMagicLevel, int cooldownDuration, int healthPointsToHeal) {
        super (name, description, manaCost, requiredMagicLevel, cooldownDuration);
        this.healthPointsToHeal = healthPointsToHeal;
    }

    public void castSpell(Player player) {
        int HP = player.getHealthPoint () + healthPointsToHeal;
        player.setHealthPoint (HP);

    }
}
