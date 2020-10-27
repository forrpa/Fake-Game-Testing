package quest;

import player.Player;
import unit.Villager;

import java.util.ArrayList;

public class StealthAndAttack extends Quest {

    private boolean succeededStealth = false;
    private boolean attacked;
    private boolean talkedToEnemy;
    private Villager talkedTo;
    private final GuildMap guildMap = new GuildMap();
    private final GuildMaster guildMaster = new GuildMaster("Robert");
    private final Townsman townsman = new Townsman("Jennie J");

    public StealthAndAttack(){
        super("Stealth and Attack", "You have to follow your enemy without being seen and then attack him", QuestState.PENDING, true);
    }

    public boolean hasSucceededStealth(){
        return succeededStealth;
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

    public Quest getRequiredQuest(Player player){
        Quest quest;
        ArrayList<Quest> completedPlayerQuests = player.getPlayerCompletedQuests();
        for (Quest q : completedPlayerQuests){
            if (q instanceof TalkToGuildLeader){
                return q;
            }
        } //Inte 100% coverage
        throw new NullPointerException("Completed Quests doesnt have a Talk To Guild Leader Quest");
    }

    @Override
    public boolean startRequirementsFulfilled(Player player){
        Quest quest = getRequiredQuest(player);
        if (player.getExperiencePoint() >= 1500 && player.isInInventory(guildMap) && player.isInCompletedQuests(quest) ){
            state = QuestState.UNLOCKED;
            player.addQuestToAvailableQuests(this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean startQuest(Player player){
        if (startRequirementsFulfilled(player)){
            state = QuestState.IN_PROGRESS;
            player.addQuestToCurrentQuests(this);
            description = "Your first job is to follow the enemy without being seen.";
            return true;
        } else {
            return false;
        }
    }

    public void resetQuest(Player player){
        player.restoreFullHealth();
        attacked = false;
        startQuest(player);
    }

    public boolean stealth(Player player, StealthAndAttackEnemy enemy){
        if (player.getHealthPoint() < enemy.getHealthPoint()){
            succeededStealth = false;
            resetQuest(player);
            return false;
        } else {
            succeededStealth = true;
            description = "You succeeded not being seen, now you have to decide if you want to kill your enemy or negotiate with it.";
            return true;
        }
    }

    public boolean attack(Player player, StealthAndAttackEnemy enemy){
        if (succeededStealth) {
            description = "Attack before the enemy escapes!";

            while (player.getHealthPoint() > 0){
                player.attack(enemy);
                if (!enemy.isAlive()){
                    description = "You succeeded killing your enemy. Go talk to the Guild Leader for your reward!";
                    attacked = true;
                    break;
                } else {
                    enemy.attack(player);
                    if (player.getHealthPoint() == 0){
                        resetQuest(player);
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public void negotiateWithEnemy(Player player, StealthAndAttackEnemy enemy){
        if (succeededStealth){
            enemy.talk();
            talkedToEnemy = true;
            description = "You decided to talk to your enemy instead of killing him. Now you cant reach the Guild so you have to talk to the Townsman.";
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

    public boolean endRequirementsForAttackingTheEnemy(){
        return talkedTo == guildMaster && attacked && !talkedToEnemy;
    }

    @Override
    public boolean endRequirementsFulfilled(Player player){
        if (endRequirementsForNegotiatingWithEnemy() || endRequirementsForAttackingTheEnemy()){
            state = QuestState.COMPLETED;
            return true;
        } else {
            return false;
        }
    }

    public void getReward(Player player){
        if (endRequirementsForNegotiatingWithEnemy()){
            rewardWhenNegotiatingWithEnemy(player);
        } else if (endRequirementsForAttackingTheEnemy()){
            rewardWhenAttackingTheEnemy(player);
        }
    }

    public void rewardWhenAttackingTheEnemy(Player player){
        player.increaseExperiencePoint(1000);
    }

    public void rewardWhenNegotiatingWithEnemy(Player player){
        player.increaseExperiencePoint(500);
        player.increaseMaxHealthPoint(100);
    }

    @Override
    public boolean completeQuest(Player player){
        if (endRequirementsFulfilled(player)){
            state = QuestState.DONE;
            player.addQuestToCompletedQuests(this);
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
        return String.format("%s: %s. %s, %b, %b, %b, %b, %s", getName(), getDescription(), getState(), isMandatory(), hasSucceededStealth(), hasAttacked(), hasTalkedToEnemy(), talkedTo);
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