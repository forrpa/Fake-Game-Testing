package quest;

import player.Player;
import unit.Monster;

public class StealthAndAttack extends Quest {

    private boolean discovered;
    private boolean attacked;
    private boolean talkedToEnemy;
    private boolean talkedToQuestMaker;
    private int seconds = 120;

    public StealthAndAttack(String name, String description, String state, boolean mandatory, boolean discovered, boolean attacked, boolean talkedToEnemy, boolean talkedToQuestMaker){
        super("Stealth and Attack", "You have to follow your enemy without being seen and then attack him", "pending", true);
        this.state = state;
        this.discovered = discovered;
        this.attacked = attacked;
        this.talkedToEnemy = talkedToEnemy;
        this.talkedToQuestMaker = talkedToQuestMaker;
    }

    public boolean isDiscovered(){
        return discovered;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public boolean hasTalkedToEnemy(){
        return talkedToEnemy;
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

    //Dela upp
    public boolean attack(Player player, Monster enemy){
        if (stealth(player)){
            attacked = true;
            while(seconds > 0){
                description = "Attack before the enemy escapes!";
                seconds--;
                if (enemy.getHealth() == 0){
                    break;
                } else if (seconds == 0){
                    description = "Your enemy escaped. Go talk to the Guild Leader.";
                    return true;
                } else if (player.getHealthPoint() == 0){
                    resetQuest(player);
                    return false;
                }
            }
            description = "You succeeded killing your enemy on time. Go talk to the Guild Leader for your reward!";
            return true;
        } else {
            return false;
        }
    }

    public boolean exchangeInfo(Player player){
        if (stealth(player)){
            talkedToEnemy = true;
            description = "You decided to talk yo your enemy instead of killing him. This will have consequences.";
            return true;
        } else {
            return false;
        }
    }

    public void talkToQuestMaker(Player player, Monster enemy){
        if (exchangeInfo(player) || attack(player, enemy) && player.getInventory().contains("Guild Map")){
            //NPC.talkTo();
            talkedToQuestMaker = true;
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
