package quest;

import edible.Ingredient;
import edible.Recipie;

public class HealingPotionRecipe extends Recipie {
    public HealingPotionRecipe(){
        super("Healing Potion Recipe", "Recipe for healing potion", new HealingPotion(), new Ingredient[]{new CrystalChard()}, 200, 1000);
    }

}
