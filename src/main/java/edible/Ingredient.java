package edible;

public class Ingredient extends Item {

	// found on special Quest?
	private final int REQUIRED_LEVEL = 0;
	
	public Ingredient(String name, String description) {
		super(name, description);
	}

	@Override
<<<<<<< HEAD
	public int getRequiredLevel() {
		return this.REQUIRED_LEVEL;
=======
	public String toString() {
		return String.format("\"%s: %s\"", getName(), getDescription());
>>>>>>> branch 'master' of ssh://chne0432@git.dsv.su.se/git/emha7928/inte_projektarbete.git
	}

}
