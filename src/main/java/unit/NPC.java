package unit;

public abstract class NPC extends Unit {
    private final String name;
    private final AttackType resistance;
    private final AttackType weakness;
    private int experiencePoints;

    public NPC (String name, int maxHealth, int attackPower, int experiencePoints, boolean isGrounded, AttackType resistance, AttackType weakness){
        super(maxHealth, attackPower, isGrounded);
        if((resistance != null && resistance.equals(weakness)) || (weakness != null && weakness.equals(resistance))){
            throw new IllegalArgumentException("Weakness can't be the same as resistance");
        }
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
        }else {
            return 0;
        }
    }

    public void takeDamage(Attack attack){
        int damage = attack.getAttackPower(getResistance(), getWeakness());
        decreaseHealth(damage);
    }
    public AttackType getResistance() {
        return resistance;
    }

    public AttackType getWeakness() {
        return weakness;
    }
}
