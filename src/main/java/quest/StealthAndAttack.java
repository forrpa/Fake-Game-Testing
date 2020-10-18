package quest;

import player.Player;

public class StealthAndAttack extends Quest {

    private boolean discovered;
    private boolean attacked;
    private boolean talkedToQuestMaker;

    public StealthAndAttack(String name, String description, String state, boolean mandatory, boolean discovered, boolean attacked, boolean talkedToQuestMaker){
        super("Stealt and Attack", "You have to follow your enemy without being seen and then attack him", "pending", true);
        this.state = state;
        this.discovered = discovered;
        this.attacked = attacked;
        this.talkedToQuestMaker = talkedToQuestMaker;
    }

    public boolean isDiscovered(){
        return discovered;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public boolean hasTalkedToQuestMaker(){
        return talkedToQuestMaker;
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
