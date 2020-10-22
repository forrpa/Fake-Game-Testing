package unit;

public abstract class NPC extends Unit {
    private String name;
    private AttackType resistance;
    private AttackType weakness;
    private int experiencePoints;

    public NPC (String name, int maxHealth, int attackPower, int experiencePoints, boolean isGrounded, AttackType resistance, AttackType weakness){
        super(maxHealth, attackPower, isGrounded);
        this.resistance = resistance;
        this.weakness = weakness;
        if(experiencePoints <= 0){
            this.experiencePoints = 0;
        }else {
            this.experiencePoints = experiencePoints;
        }
        if(name.matches(".*\\d.*")){
            throw new IllegalArgumentException(("No numbers allowed in name"));
        }else {
            this.name = name;
        }
    }
    public String getName() {
        return name;
    }


    public boolean attack(Combatant enemy){
        if(!isAlive()){
            throw new IllegalStateException("The attacking unit is dead");
        }else if(!enemy.isAlive()){
            throw new IllegalStateException("The unit receiving damage is already dead");
        }else if(isGrounded() && !enemy.isGrounded()){
            return false;
        }else {
            enemy.takeDamage(new Attack(getAttackPower()));
            return true;
        }
    }

    public int getExperiencePoints(){
        if (!isAlive()) {
            int tempExperience = experiencePoints;
            experiencePoints = 0;
            return tempExperience;
        }
        return 0;
    }

    public void takeDamage(Attack attack){
        int damage = attack.getAttackPower(getResistance(), getWeakness());
        int tempHealth = getHealthPoint() - damage;

        setHealthPoint(tempHealth);
    }
    public AttackType getResistance() {
        return resistance;
    }

    public AttackType getWeakness() {
        return weakness;
    }
}
