package quest;

import player.Player;
import unit.Bat;

import java.util.ArrayList;

public class SecretCave extends Quest {

    private int batsKilled;
    private static final int batsToKill = 5;

    public SecretCave() {
        super("Secret Cave", "You have found the secret cave. What secrets lies within it?", QuestState.PENDING, false);
    }

    public int getBatsKilled() {
        return batsKilled;
    }

    public Quest getSecretCaveQuestIfInAvailableQuests(Player player){
        ArrayList<Quest> availablePlayerQuests = player.getPlayerAvailableQuests();
        for (Quest q : availablePlayerQuests){
            if (q instanceof SecretCave){
                return q;
            } else {
                continue;
            }
        }
        return null;
    }

    @Override
    public boolean startRequirementsFulfilled(Player player) {
        Quest secretCave = getSecretCaveQuestIfInAvailableQuests(player);
        if (player.isInAvailableQuests(secretCave)){
            state = QuestState.UNLOCKED;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean startQuest(Player player) {
        if (startRequirementsFulfilled(player)){
            state = QuestState.IN_PROGRESS;
            player.addQuestToCurrentQuests(this);
            description = "First you have to kill and loot 5 bats. They hide around the cave. Be careful.";
            return true;
        } else {
            return false;
        }
    }

    public void attackBat(Player player, Bat bat){
        player.attack(bat);
        if (!bat.isAlive()){
            batsKilled++;
            //bat.getLooted();
        } else {
            bat.attack(player);
            if (player.getHealthPoint() == 0) {
                player.restoreFullHealth();
                startQuest(player);
            }
        }
    }

    public boolean visitWitch(){
        if (batsKilled == batsToKill){
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean endRequirementsFulfilled(Player player) {
        if (batsKilled == batsToKill){
            state = QuestState.COMPLETED;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean completeQuest(Player player) {
        if (endRequirementsFulfilled(player)){
            state = QuestState.DONE;
            player.addQuestToCompletedQuests(this);
            description= "You completed the quest!";
            player.increaseExperiencePoint(5000);
            player.restoreFullHealth();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s. %s, %b", getName(), getDescription(), getState(), isMandatory());
    }
}
