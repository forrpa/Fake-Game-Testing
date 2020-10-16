package quest;
import player.Player;

//Tillstånd: pending (default), unlocked (startReq = true), in progress(efter startat quest), completed(efter avslutat quest)

public class TalkToGuildLeader extends Quest {

    private boolean talkedToGuildLeader = false;

    public TalkToGuildLeader(String name, String description, String state, boolean mandatory, boolean talkedToGuildLeader){
        super("Talk to Guild leader", "You have to talk to the guild leader west of town.", "pending", true);
        this.talkedToGuildLeader = talkedToGuildLeader;
    }

    public boolean isTalkedToGuildLeader(){
        return talkedToGuildLeader;
    }

    @Override
    public boolean startRequirementsFulfilled(Player player) {
        if (player.getExperiencePoint() >= 1000 && player.getInventory().contains("Guild Map")){
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


    @Override
    public boolean endRequirementsFulfilled(Player player) {
        if (talkedToGuildLeader && player.getInventory().contains("Guild Map")){
            state = "completed";
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void questCompleted(Player player) {
        state = "done";
        player.setExperiencePoint(100);
        rewardBasedOnClass(player);
        rewardBasedOnRace(player);
    }

    public void rewardBasedOnClass(Player player){
        switch (player.getPlayerClass()){
            case "Healer":
                player.setHealthPoint(50);
            case "Tank":
        //        player.setManaPoint(25);
                player.setHealthPoint(25);
            case "Damage":
            //    player.setManaPoint(50);
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