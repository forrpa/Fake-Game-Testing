package quest;
import equipment.BreastplateOfTesting;
import equipment.BucklerOfUselessness;
import player.Player;
import unit.Questgiver;
import weapon.Heartsbane;
import weapon.WidowsWail;

public class TalkToGuildLeader extends Quest {

    private boolean talkedToGuildLeader = false;
    GuildLeader guildLeader = new GuildLeader("Guild Leader Mar tin");
    Questgiver questgiver = new Questgiver("Robert", this);

    public TalkToGuildLeader(){
        super("Talk to Guild Leader", "You have to talk to the guild leader west of town.", QuestState.PENDING, true);
    }

    public boolean hasTalkedToGuildLeader(){
        return talkedToGuildLeader;
    }

    @Override
    public boolean startRequirementsFulfilled(Player player) {
        if (player.getExperiencePoint() >= 1000){
            state = QuestState.UNLOCKED;
            player.addQuestToAvailableQuests(this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean startQuest(Player player) {
        if (startRequirementsFulfilled(player)){
            questgiver.talk();
            state = QuestState.IN_PROGRESS;
            player.addQuestToCurrentQuests(this);
            return true;
        } else {
            return false;
        }
    }

    public void talkToGuildLeader(){
        guildLeader.talk();
        talkedToGuildLeader = true;
    }

    @Override
    public boolean endRequirementsFulfilled(Player player) {
        if (talkedToGuildLeader){
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
            player.increaseExperiencePoint(500);
            //rewardBasedOnClass(player);
            rewardBasedOnRace(player);
            GuildMap guildMap = new GuildMap();
            player.addToInventory(guildMap);
            return true;
        } else {
            return false;
        }
    }

    /*public void rewardBasedOnClass(Player player){
        switch (player.getPlayerClass()){
            case "Healer":
                player.addToInventory(new HealingPotion());
            case "Damage":
                player.addToInventory(new CrystalChard());
            case "Tank":
                player.addToInventory(new HealingPotionRecipe());
        }
    }*/

    public void rewardBasedOnRace(Player player){
        switch (player.getRace()){
            case "Human":
                if (player.getPlayerClass().equals("Tank")){
                    player.addToInventory(new BreastplateOfTesting());
                } else {
                    player.addToInventory(new BucklerOfUselessness());
                }
            case "Orc":
                if (player.getPlayerClass().equals("Damage")){
                    player.addToInventory(new WidowsWail());
                } else {
                    player.addToInventory(new Heartsbane());
                }
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s. %s, %b, %b", getName(), getDescription(), getState(), isMandatory(), hasTalkedToGuildLeader());
    }
}