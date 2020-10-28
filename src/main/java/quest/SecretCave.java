package quest;

import player.Player;

public class SecretCave extends Quest {
    public SecretCave() {
        super("Secret Cave", "You have found the secret cave. What secrets lies within it?", QuestState.PENDING, false);
    }

    @Override
    public boolean startRequirementsFulfilled(Player player) {
        return false;
    }

    @Override
    public boolean startQuest(Player player) {
        return false;
    }

    @Override
    public boolean endRequirementsFulfilled(Player player) {
        return false;
    }

    @Override
    public boolean completeQuest(Player player) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
