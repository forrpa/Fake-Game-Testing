package equipment;

public class RobesOfImmenseMagic extends Chest{
	private static final ArmorType ARMOR_TYPE = ArmorType.CLOTH;
	
	public RobesOfImmenseMagic() {
		super("Robes of Immense Magic", "A beautiful piece of silken robes that imbue the wearer with immense magical power!", 50, 30, 85, 0, 0, 100, 20);
	}

	@Override
	public ArmorType getArmorType() {
		return RobesOfImmenseMagic.ARMOR_TYPE;
	}

}
