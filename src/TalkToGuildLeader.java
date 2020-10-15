public class TalkToGuildLeader extends Quest {

    public TalkToGuildLeader(String name, String description, boolean mandatory){
        super("Talk to Guild leader", "You have to talk to the guild leader west of town.", true);
    }

    @Override
    public void calculateReward(Player player, String playerClass, String race) {
        switch (playerClass){
            case "Healer":
                player.setManaPoint(50);
            case "Tank":

            case "Damage":
        }
    }
}