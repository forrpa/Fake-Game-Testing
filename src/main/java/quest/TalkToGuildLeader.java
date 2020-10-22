package quest;
import edible.Ingredient;
import edible.Potion;
import edible.Recipie;
import equipment.BreastplateOfTesting;
import equipment.BucklerOfUselessness;
import player.Player;
import weapon.Heartsbane;
import weapon.WidowsWail;

public class TalkToGuildLeader extends Quest {

    //göra states till enum

    private boolean talkedToGuildLeader = false;
    Player player = new Player("Tank", "Human", 200,200, 1000);

    public TalkToGuildLeader(){
        super("Talk to Guild Leader", "You have to talk to the guild leader west of town.", "pending", true);
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
            player.increaseExperiencePoint(500);
            rewardBasedOnClass(player);
            rewardBasedOnRace(player);
            GuildMap guildMap = new GuildMap();
            player.addToInventory(guildMap);
        } else {
            System.out.println("You have not fulfilled quest requirements.");
        }
    }

    public void rewardBasedOnClass(Player player){
        switch (player.getPlayerClass()){
            case "Healer":
                player.addToInventory(new HealingPotion());
            case "Damage":
                player.addToInventory(new CrystalChard());
            case "Tank":
                player.addToInventory(new HealingPotionRecipe());
        }
    }

    public void rewardBasedOnRace(Player player){
        switch (player.getRace()){
            case "Human":
                if (player.getPlayerClass() == "Tank"){
                    player.addToInventory(new BreastplateOfTesting());
                } else {
                    player.addToInventory(new BucklerOfUselessness());
                }
            case "Orc":
                if (player.getPlayerClass() == "Damage"){
                    player.addToInventory(new WidowsWail());
                } else {
                    player.addToInventory(new Heartsbane());
                }
        }
    }
}