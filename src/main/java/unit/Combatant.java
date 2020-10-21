package unit;

public interface Combatant {
    boolean attack(Combatant enemy);
    void takeDamage(Attack attack);
    boolean isAlive();
    boolean isGrounded();
}
