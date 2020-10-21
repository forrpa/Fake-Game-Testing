package unit;

public abstract class NPC extends Unit {
    private String name;
    private AttackType resistance;
    private AttackType weakness;

    public NPC (String name, int maxHealth, int attackPower, boolean isGrounded, AttackType resistance, AttackType weakness){
        super(maxHealth, attackPower, isGrounded);
        this.resistance = resistance;
        this.weakness = weakness;
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

    public void takeDamage(Attack attack){
        int damage = attack.getAttackPower(getResistance(), getWeakness());
        int tempHealth = getHealthPoint() - damage;
        if(tempHealth < 0){
            setHealthPoint(0);
        }else {
            setHealthPoint(tempHealth);
        }
    }
    public AttackType getResistance() {
        return resistance;
    }

    public AttackType getWeakness() {
        return weakness;
    }
}
