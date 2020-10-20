package equipment;

public class RobesOfImmenseMagic extends Chest{
	private ArmorType CLOTH;
	
	public RobesOfImmenseMagic() {
		super("Robes of Immense Magic", "Robes that imbue the wearer with an increadibly boost to magic abilities!", 40, 50, 60, 0, 0, 50, 20);
	}

	@Override
	public ArmorType getArmorType() {
		return this.CLOTH;
	}

}
