package quest;

import player.Player;
import unit.Monster;
import unit.Questgiver;
import unit.Villager;
import unit.Wolf;

public class StealthAndAttack extends Quest {

    //Belöning magic
    //Ändra belöning från mina klasser till Christians

    private boolean discovered;
    private boolean attacked;
    private boolean talkedToEnemy;
    private Villager talkedTo;
    //private int timer = 120;
    private final GuildMap guildMap = new GuildMap();
    private final GuildMaster guildMaster = new GuildMaster("Robert");
    private final Townsman townsman = new Townsman("Jennie J");

    public StealthAndAttack(){
        super("Stealth and Attack", "You have to follow your enemy without being seen and then attack him", QuestState.PENDING, true);
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

    public Villager getTalkedTo() {
        return talkedTo;
    }

    public GuildMaster getGuildMaster(){
        return guildMaster;
    }

    public Townsman getTownsman(){
        return townsman;
    }

    @Override
    public boolean startRequirementsFulfilled(Player player){
        if (player.getExperiencePoint() >= 1500 && player.isInInventory(guildMap)){ //&& player.questlog().contains("TalkToGuildLeader")
            state = QuestState.UNLOCKED;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean startQuest(Player player){
        if (startRequirementsFulfilled(player)){
            state = QuestState.IN_PROGRESS;
            description = "Your first job is to follow the enemy without being seen.";
            return true;
        } else {
            return false;
        }
    }

    public boolean resetQuest(Player player){
        player.restoreFullHealth();
        attacked = false;
        discovered = false;
        startQuest(player);
        return true;
    }

    public boolean stealth(Player player, Monster monster){
        if (player.getHealthPoint() < monster.getHealthPoint()){
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
    /*public boolean attack(Player player, Monster monster){
        if (stealth(player, monster)) {
            //Attack attack = new Attack(timer, player, enemy);
            player.attack(monster);
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
    }*/

    //Dela upp metod, klass

    public void attack(Player player, Monster monster){
        if (stealth(player, monster)) {
            description = "Attack before the enemy escapes!";
            player.attack(monster); //Nytt
            if (!monster.isAlive()) { //Nytt
                description = "You succeeded killing your enemy on time. Go talk to the Guild Leader for your reward!";
                attacked = true;
            } else {
                resetQuest(player);
            }
        }
            /*attacked = true;
            description = "Attack before the enemy escapes!";
            while(timer > 0){
                timer--;
                if (monster.getHealthPoint() == 0){
                    description = "You succeeded killing your enemy on time. Go talk to the Guild Leader for your reward!";
                    return true;
                } else if (timer == 0){
                    //attackState = ""
                    description = "Your enemy escaped. Go talk to the Guild Leader.";
                    return true;
                } else if (player.getHealthPoint() == 0){
                    resetQuest(player);
                    return false;
                }*/
            //return true;
        /*} else {
            return false;
        }*/
    }

    public void negotiateWithEnemy(Player player, Monster monster){
        if (stealth(player, monster)){
            //enemy.negotiate();
            talkedToEnemy = true;
            description = "You decided to talk to your enemy instead of killing him. Now you cant reach the Guild so you have to talk to Townsman.";
            player.removeFromInventory(guildMap);
        }
    }

    public boolean talkToGuildMaster(Player player){
        if (attacked && player.isInInventory(guildMap)){
            guildMaster.talk();
            talkedTo = guildMaster;
            return true;
        } else {
            return false;
        }
    }

    public boolean talkToTownsman(){
        if (talkedToEnemy){
            townsman.talk();
            talkedTo = townsman;
            return true;
        } else {
            return false;
        }
    }

    public boolean endRequirementsForNegotiatingWithEnemy(){
        return talkedTo == townsman && !attacked && talkedToEnemy;
    }

    public boolean endRequirementsForAttackingOnTime(){
        //return talkedTo == "questgiver" && attacked && timer > 0 && !talkedToEnemy;
        return talkedTo == guildMaster && attacked && !talkedToEnemy;
    }

    /*public boolean endRequirementsForNotAttackingOnTime(){
        return talkedTo == "questgiver" && attacked && timer == 0 && !talkedToEnemy;
    }*/

    @Override
    public boolean endRequirementsFulfilled(Player player){
        //if (endRequirementsForNegotiatingWithEnemy() || endRequirementsForAttackingOnTime() || endRequirementsForNotAttackingOnTime()){
            if (endRequirementsForNegotiatingWithEnemy() || endRequirementsForAttackingOnTime()){
            state = QuestState.COMPLETED;
            return true;
        } else {
            return false;
        }
    }

    public void getReward(Player player){
        if (endRequirementsForNegotiatingWithEnemy()){
            rewardWhenNegotiatingWithEnemy(player);
        } /*else if (endRequirementsForNotAttackingOnTime()){
            rewardWhenNotAttackingOnTime(player);
        } */else if (endRequirementsForAttackingOnTime()){
            rewardWhenAttackingOnTime(player);
        }
    }

    public void rewardWhenAttackingOnTime(Player player){
        //Relation med guild +1000
        player.increaseExperiencePoint(1000);
    }

    /*
    public void rewardWhenNotAttackingOnTime(Player player){
        //Relation med guild +300
        player.increaseExperiencePoint(300);
    }*/

    public void rewardWhenNegotiatingWithEnemy(Player player){
        //player.addToInventory("Money");
        player.increaseExperiencePoint(500);
        player.increaseMaxHealthPoint(100);
        //Sämre relation med guild, -1000
    }

    @Override
    public boolean completeQuest(Player player){
        if (endRequirementsFulfilled(player)){
            state = QuestState.DONE;
            description= "You completed the quest!";
            getReward(player);
            player.restoreFullHealth();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s. %s, %b, %b, %b, %b, %s", getName(), getDescription(), getState(), isMandatory(), isDiscovered(), hasAttacked(), hasTalkedToEnemy(), talkedTo);
    }
}
