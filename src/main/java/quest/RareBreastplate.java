package quest;

import equipment.ArmorType;
import equipment.Chest;

public class RareBreastplate extends Chest {
    private ArmorType PLATE;

    public RareBreastplate() {
        super("Rare breast plate", "Highest rarity of breatplates", 0, 500, 100, 50, 100, 0, 400);
    }

    @Override
    public ArmorType getArmorType() {return this.PLATE;}
}
