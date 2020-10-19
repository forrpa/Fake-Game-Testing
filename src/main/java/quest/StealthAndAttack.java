package quest;

import player.Player;

public class StealthAndAttack extends Quest {

    private boolean discovered;
    private boolean attacked;
    private boolean talkedToQuestMaker;
    private int seconds = 120;

    public StealthAndAttack(String name, String description, String state, boolean mandatory, boolean discovered, boolean attacked, boolean talkedToQuestMaker){
        super("Stealth and Attack", "You have to follow your enemy without being seen and then attack him", "pending", true);
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

    @Override
    public boolean startRequirementsFulfilled(Player player){
        if (player.getExperiencePoint() >= 1500){ //&& player.getInventory().contains("Guild Map") && player.questlog().contains("TalkToGuildLeader")
            state = "unlocked";
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void startQuest(Player player){
        if (startRequirementsFulfilled(player)){
            state = "in progress";
            description = "Your first job is to follow the enemy without being seen.";
        } else {
            System.out.println("You cant start this quest yet.");
        }
    }

    public void resetQuest(Player player){
        player.setHealthPoint(100); //Ska vara max
        attacked = false;
        discovered = false;
        startQuest(player);
    }

    public boolean stealth(Player player){
        if (discovered){
            resetQuest(player);
            return false;
        } else {
            description = "You succeeded not being seen, now you have to decide if you want to kill your enemy or negotiate with him.";
            return true;
        }
    }

    public boolean endRequirementsForExchangingInfo(){
        return talkedToQuestMaker && !attacked;
    }

    public boolean endRequirementsForAttackingOnTime(){
        return talkedToQuestMaker && attacked && seconds > 0;
    }

    public boolean endRequirementsForNotAttackingOnTime(){
        return talkedToQuestMaker && attacked && seconds == 0;
    }

    @Override
    public boolean endRequirementsFulfilled(Player player){
        if (endRequirementsForExchangingInfo() || endRequirementsForAttackingOnTime() || endRequirementsForNotAttackingOnTime()){
            state = "completed";
            return true;
        } else {
            return false;
        }
    }

    public void rewardWhenAttackingOnTime(Player player){
        //Relation med guild +1000
        player.setExperiencePoint(2000);
    }

    public void rewardWhenNotAttackingOnTime(Player player){
        //Relation med guild +300
        player.setExperiencePoint(1000);
    }

    public void rewardWhenTalkingToEnemy(Player player){
        player.getInventory().remove("Guild Map");
        player.getInventory().add("Money"); //Typ 10000 pengar
        player.setExperiencePoint(1000);
        //Sämre relation med guild, -1000
    }

    @Override
    public void questCompleted(Player player){
        if (endRequirementsFulfilled(player)){
            state = "done";
            description= "You completed the quest!";
            rewardWhenAttackingOnTime(player);
            rewardWhenNotAttackingOnTime(player);
            rewardWhenTalkingToEnemy(player);
        }
    }

}
