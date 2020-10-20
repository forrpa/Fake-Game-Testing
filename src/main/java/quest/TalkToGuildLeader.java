package quest;
import edible.Ingredient;
import edible.Potion;
import edible.Recipie;
import player.Player;
import weapon.WidowsWail;

public class TalkToGuildLeader extends Quest {

    private boolean talkedToGuildLeader = false;

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
            player.setExperiencePoint(500);
            rewardBasedOnClass(player);
            rewardBasedOnRace(player);
            QuestItem guildMap = new QuestItem("Guild Map", "Map to find the Guild");
            player.addToInventory(guildMap);
        } else {
            System.out.println("You have not fulfilled quest requirements.");
        }
    }

    public void rewardBasedOnClass(Player player){
        //Skapa 3 klasser....
        Potion healingPotion = new Potion("Healing Potion", "Potion that heals", 200, 100, 0);
        Ingredient crystalChard = new Ingredient("Crystal Chard", "For making potions");
        Recipie healingPotionRecipe = new Recipie("Healing Potion Recipe", "Recipe for healing potion", healingPotion, new Ingredient[]{crystalChard}, 200, 1000);

        switch (player.getPlayerClass()){
            case "Healer":
                player.addToInventory(healingPotion);
                //player.getCupboard().store(healingPotion); //Ska man lägga till i båda?
            case "Damage":
                player.addToInventory(crystalChard);
            case "Tank":
                player.addToInventory(healingPotionRecipe);
        }
    }

    public void rewardBasedOnRace(Player player){
        switch (player.getRace()){
            case "Human":
                if (player.getPlayerClass() == "Tank"){
                    player.addToInventory(new RareBreastplate());
                } else {
                    player.addToInventory(new CommonBreastPlate());
                }
            case "Orc":
                if (player.getPlayerClass() == "Damage"){
                    player.addToInventory(new WidowsWail());
                } else {
                    player.addToInventory(new CommonSword());
                }
        }
    }
}