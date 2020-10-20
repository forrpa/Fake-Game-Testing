package quest;

import item.Inventory;
import item.Item;
import player.Player;
import unit.Monster;

public class StealthAndAttack extends Quest {

    private boolean discovered;
    private boolean attacked;
    private boolean talkedToEnemy;
    private String talkedTo;
    private int seconds = 120;
    private QuestItem questItem;
    //Player player

    public StealthAndAttack(String name, String description, String state, boolean mandatory, boolean discovered, boolean attacked, boolean talkedToEnemy, String talkedTo){
        super("Stealth and Attack", "You have to follow your enemy without being seen and then attack him", "pending", true);
        this.state = state;
        this.discovered = discovered;
        this.attacked = attacked;
        this.talkedToEnemy = talkedToEnemy;
        this.talkedTo = talkedTo;
    }

    /*public QuestItem getQuestItem(Inventory inventory, QuestItem questItem){
        //inventory.
        //hämta nyckel-värde i inventory
    }*/

    public boolean isDiscovered(){
        return discovered;
    }

    public boolean hasAttacked() {
        return attacked;
    }

    public boolean hasTalkedToEnemy(){
        return talkedToEnemy;
    }

    public String getTalkedTo() {
        return talkedTo;
    }

    public int getSeconds() {
        return seconds;
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

    public boolean stealth(Player player, Enemy enemy){
        if (enemy.discover(player)){
            discovered = true;
            resetQuest(player);
            return false;
        } else {
            discovered = false;
            description = "You succeeded not being seen, now you have to decide if you want to kill your enemy or negotiate with him.";
            return true;
        }
    }

    //Dela upp metod
    public boolean attack(Player player, Enemy enemy){
        if (stealth(player, enemy)){
            attacked = true;
            description = "Attack before the enemy escapes!";
            //enemy.attack();
            while(seconds > 0){
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

    public boolean exchangeInfo(Player player, Enemy enemy){
        if (stealth(player, enemy)){
            enemy.negotiate();
            talkedToEnemy = true;
            description = "You decided to talk yo your enemy instead of killing him. This will have consequences.";
            player.removeFromInventory(questItem); //Funkar ej
            return true;
        } else {
            return false;
        }
    }

    public void talkToQuestGiver(Player player, Enemy enemy, QuestGiver questGiver){
        if (attack(player, enemy) && player.isInInventory(questItem)){ //Funkar ej just nu
            questGiver.talkToPlayer();
            talkedTo = "questgiver"; //Ska vara till QuestGiver
        }
    }

    public void talkToTownsman(Player player, Enemy enemy){
        if (exchangeInfo(player, enemy)){
            talkedTo = "townsman"; //Ska vara till TownsMan
        }
    }

    public boolean endRequirementsForExchangingInfo(){
        return talkedTo == "townsman" && !attacked && talkedToEnemy;
    }

    public boolean endRequirementsForAttackingOnTime(){
        return talkedTo == "questgiver" && attacked && seconds > 0 && !talkedToEnemy;
    }

    public boolean endRequirementsForNotAttackingOnTime(){
        return talkedTo == "questgiver" && attacked && seconds == 0 && !talkedToEnemy;
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
        //player.addToInventory("Money");
        player.setExperiencePoint(1000);
        //Sämre relation med guild, -1000
    }

    @Override
    public void questCompleted(Player player){
        if (endRequirementsFulfilled(player)){
            state = "done";
            description= "You completed the quest!";
            player.setHealthPoint(200); //Sätt max
            rewardWhenAttackingOnTime(player);
            rewardWhenNotAttackingOnTime(player);
            rewardWhenTalkingToEnemy(player);
        }
    }

}
