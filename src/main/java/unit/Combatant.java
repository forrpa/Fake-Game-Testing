package unit;

public interface Combatant {
    boolean attack(Combatant enemy);
    boolean attack(Combatant enemy, Attack attack);
    void takeDamage(Attack attack);
    int getAttackPower();
    boolean isAlive();
    boolean isGrounded();
}
