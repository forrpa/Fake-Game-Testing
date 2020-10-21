package unit;

public interface Combatant {
    boolean attack(Combatant enemy);
    void takeDamage(Attack attack);
    int getAttackPower();
    boolean isAlive();
    boolean isGrounded();
}
