package quest;

import player.Player;

public class StealtAndAttack extends Quest {

    public StealtAndAttack(String name, String description, String state, boolean mandatory){
        super("Stealt and Attack", "You have to follow your enemy without being seen and then attack him", "pending", true);
    }

    public boolean startRequirementsFulfilled(Player player){
        return true;
    }

    public void startQuest(Player player){

    }

    public boolean endRequirementsFulfilled(Player player){
        return true;
    }

    public void questCompleted(Player player){

    }

}
