package quest;

import item.Inventory;
import item.Item;
import player.Player;
import unit.Monster;

public class StealthAndAttack extends Quest {

    //Belöning magic
    //Ändra belöning från mina klasser till Christians

    private boolean discovered;
    private boolean attacked;
    private boolean talkedToEnemy; //Överflödig
    private String talkedTo;
    private int timer = 120;
    private final GuildMap guildMap = new GuildMap();
    private Player player = new Player("Tank", "Human", 200,200, 1500);

    public StealthAndAttack(boolean discovered, boolean attacked, boolean talkedToEnemy, String talkedTo){
        super("Stealth and Attack", "You have to follow your enemy without being seen and then attack him", "pending", true);
        this.discovered = discovered;
        this.attacked = attacked;
        this.talkedToEnemy = talkedToEnemy;
        this.talkedTo = talkedTo;
    }

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

    @Override
    public boolean startRequirementsFulfilled(Player player){
        if (player.getExperiencePoint() >= 1500 && player.isInInventory(guildMap)){ //&& player.questlog().contains("TalkToGuildLeader")
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
        player.fillHealthBar();
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
            description = "You succeeded not being seen, now you have to decide if you want to kill your enemy or negotiate with it.";
            return true;
        }
    }

    //TEST
    public boolean attack(Player player, Enemy enemy){
        if (stealth(player, enemy)) {
            Attack attack = new Attack(timer, player, enemy);
            attack.attackEnemy(enemy);
            attacked = true;
            description = "Attack before the enemy escapes!";
            while (attack.getTimer() > 0) {
                attack.decreaseTimerByOne();
                if (enemy.getHealth() == 0) {
                    break;
                } else if (attack.getTimer() == 0) {
                    description = "Your enemy escaped. Go talk to the Guild Leader.";
                    return true;
                } else if (player.getHealthPoint() == 0) {
                    resetQuest(player);
                    return false;
                }
            }
            description = "You succeeded killing your enemy on time. Go talk to the Guild Leader for your reward!";
            return true; //Returnerar alltid true?? testa
        } else {
            return false;
        }
    }

    //Dela upp metod, klass
    /*
    public boolean attack(Player player, Enemy enemy){
        if (stealth(player, enemy)){
            attacked = true;
            description = "Attack before the enemy escapes!";
            //enemy.attack();
            while(timer > 0){
                timer--;
                if (enemy.getHealth() == 0){
                    break;
                } else if (timer == 0){
                    //attackState = ""
                    description = "Your enemy escaped. Go talk to the Guild Leader.";
                    return true;
                } else if (player.getHealthPoint() == 0){
                    resetQuest(player);
                    return false;
                }
            }
            //attackedOnTime = true;
            description = "You succeeded killing your enemy on time. Go talk to the Guild Leader for your reward!";
            return true;
        } else {
            return false;
        }
    }*/

    public boolean negotiateWithEnemy(Player player, Enemy enemy){
        if (stealth(player, enemy)){
            enemy.negotiate();
            talkedToEnemy = true;
            description = "You decided to talk to your enemy instead of killing him. Now you cant reach the Guild so you have to talk to Townsman.";
            player.removeFromInventory(guildMap);
            return true;
        } else {
            return false;
        }
    }

    public void talkToQuestGiver(Player player, Enemy enemy, QuestGiver questGiver){
        if (attack(player, enemy) && player.isInInventory(guildMap)){
            questGiver.talkToPlayer();
            talkedTo = "questgiver"; //Ska vara till QuestGiver
        }
    }

    public void talkToTownsman(Player player, Enemy enemy){
        if (negotiateWithEnemy(player, enemy)){
            talkedTo = "townsman"; //Ska vara till TownsMan
        }
    }

    public boolean endRequirementsForNegotiatingWithEnemy(){
        return talkedTo == "townsman" && !attacked && talkedToEnemy;
    }

    public boolean endRequirementsForAttackingOnTime(){
        return talkedTo == "questgiver" && attacked && timer > 0 && !talkedToEnemy;
    }

    public boolean endRequirementsForNotAttackingOnTime(){
        return talkedTo == "questgiver" && attacked && timer == 0 && !talkedToEnemy;
    }

    @Override
    public boolean endRequirementsFulfilled(Player player){
        if (endRequirementsForNegotiatingWithEnemy() || endRequirementsForAttackingOnTime() || endRequirementsForNotAttackingOnTime()){
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
        player.increaseMaxHealthPoint(100);
        //Sämre relation med guild, -1000
    }

    @Override
    public void questCompleted(Player player){
        if (endRequirementsFulfilled(player)){
            state = "done";
            description= "You completed the quest!";
            player.fillHealthBar();
            rewardWhenAttackingOnTime(player);
            rewardWhenNotAttackingOnTime(player);
            rewardWhenTalkingToEnemy(player);
        }
    }

}
