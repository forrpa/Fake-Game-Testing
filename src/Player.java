public class Player {
    private int manaPoint;
    private int healthPoint;
    private int experiencePoint;

    private PlayerClass playerClass;
    private Race race; //Beror på om vi vill göra klasserna Class och Race, jag kör med String sålänge

    public int getManaPoint() {
        return manaPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getExperiencePoint() {
        return experiencePoint;
    }

    public void setManaPoint(int manaPoint) {
        this.manaPoint += manaPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void setExperiencePoint(int experiencePoint) {
        this.experiencePoint = experiencePoint;
    }
}
