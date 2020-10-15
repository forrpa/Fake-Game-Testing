public class TalkToGuildLeader extends Quest {

    public TalkToGuildLeader(String name, String description, boolean mandatory){
        super("Talk to Guild leader", "You have to talk to the guild leader west of town.", true);
    }

    @Override
    public void calculateReward(Player player) {
        player.setExperiencePoint(100);
        switch (player.getPlayerClass()){
            case "Healer":
                player.setHealthPoint(50);
            case "Tank":
                player.setManaPoint(25);
                player.setHealthPoint(25);
            case "Damage":
                player.setManaPoint(50);
        }

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