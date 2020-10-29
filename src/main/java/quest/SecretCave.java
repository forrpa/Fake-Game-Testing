package quest;

import item.Item;
import player.Player;
import unit.Bat;
import java.util.ArrayList;

public class SecretCave extends Quest {

    private boolean witchVisited = false;
    private final Witch witch = new Witch();
    private int batsLooted;
    private static final int batsToLoot = 5;

    public SecretCave() {
        super("Secret Cave", "You have found the secret cave. What secrets lies within it?", QuestState.PENDING, false);
    }

    public int getBatsLooted() {
        return batsLooted;
    }

    public boolean isWitchVisited(){
        return witchVisited;
    }

    public Quest getSecretCaveQuestIfInAvailableQuests(Player player){
        ArrayList<Quest> availablePlayerQuests = player.getQuestLog().getAvailableQuests();
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
        if (player.getQuestLog().isInAvailableQuests(secretCave)){
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
            player.getQuestLog().addQuestToCurrentQuests(this);
            description = "First you have to kill and loot 5 bats. They hide around the cave. Be careful.";
            return true;
        } else {
            return false;
        }
    }

    public void attackBat(Player player, Bat bat){
        player.attack(bat);
        if (!bat.isAlive()){
            batsLooted++;
            for (Item i : bat.getLooted()){
                player.addToInventory(i);
            }
            if (batsLooted == batsToLoot){
                description = "You have looted five bats successfully. Now it's time to find the witch. She lurks somewhere in this cave!";
            }
        } else {
            bat.attack(player);
            if (player.getHealthPoint() == 0) {
                player.restoreFullHealth();
                startQuest(player);
            }
        }
    }

    public boolean visitWitch(Player player){
        if (witch.isFound(player) && !witch.potionGiven){
            witch.talk();
            witch.givePotion(player);
            if (witch.potionGiven){
                witchVisited = true;
                description = "You found the wicked witch and succeeded trading the bat loot for a powerful potion.";
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    @Override
    public boolean endRequirementsFulfilled(Player player) {
        if (witchVisited){
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
            player.getQuestLog().addQuestToCompletedQuests(this);
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
        return String.format("%s: %s State: %s, Mandatory: %b, Has visited witch: %b, Bats looted: %d", getName(), getDescription(), getState(), isMandatory(), isWitchVisited(), getBatsLooted());
    }
}
