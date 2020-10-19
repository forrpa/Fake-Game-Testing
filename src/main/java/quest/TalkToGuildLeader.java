package quest;
import player.Player;

public class TalkToGuildLeader extends Quest {

    private boolean talkedToGuildLeader = false;
    //private String chosenBranch;

    public TalkToGuildLeader(String name, String description, String state, boolean mandatory, boolean talkedToGuildLeader){
        super("Talk to Guild Leader", "You have to talk to the guild leader west of town.", "pending", true);
        this.talkedToGuildLeader = talkedToGuildLeader;
        this.state = state;
    }

    public boolean hasTalkedToGuildLeader(){
        return talkedToGuildLeader;
    }

    @Override
    public boolean startRequirementsFulfilled(Player player) {
        if (player.getExperiencePoint() >= 1000){
            state = "unlocked";
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void startQuest(Player player) {
        if (startRequirementsFulfilled(player)){
            state = "in progress";
        }
    }

    public void talkToGuildLeader(){
        talkedToGuildLeader = true;
    }

    @Override
    public boolean endRequirementsFulfilled(Player player) {
        if (talkedToGuildLeader){
            state = "completed";
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void questCompleted(Player player) {
        if (endRequirementsFulfilled(player)){
            state = "done";
            player.setExperiencePoint(100);
            rewardBasedOnClass(player);
            rewardBasedOnRace(player);
            player.getInventory().add("Guild Map"); //Till nästa quest, är ett krav i nästa quest
        } else {
            System.out.println("You have not fulfilled quest requirements.");
        }
    }

    public void rewardBasedOnClass(Player player){
        switch (player.getPlayerClass()){
            case "Healer":
                player.setHealthPoint(500); //Increase max points
            case "Tank":
                player.setManaPoint(250);
                player.setHealthPoint(250);
            case "Damage":
                player.setManaPoint(500);
        }
    }

    public void rewardBasedOnRace(Player player){
        switch (player.getRace()){
            case "Human":
                if (player.getPlayerClass() == "Tank"){
                    player.getInventory().add("Rare Steel Boots");
                } else {
                    player.getInventory().add("Common Steel Boots");
                }
            case "Orc":
                if (player.getPlayerClass() == "Damage"){
                    player.getInventory().add("Rare Leather Gauntlets");
                } else {
                    player.getInventory().add("Common Leather Gauntlets");
                }
        }
    }
}