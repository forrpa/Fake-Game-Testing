package quest;

import equipment.ArmorType;
import equipment.Chest;

public class CommonBreastPlate extends Chest {
    private ArmorType PLATE;

    public CommonBreastPlate() {
        super("Common breast plate", "Lowest rarity of breatplates", 0, 200, 50, 25, 50, 0, 200);
    }

    @Override
    public ArmorType getArmorType() {return this.PLATE;}
}
