package quest;

import player.Player;

public abstract class Quest {

    private String name;
    private String description;
    private String state;
    private boolean mandatory;
    private String startRequirement;
    private String endRequirement;

    public Quest(String name, String description, boolean mandatory){
        this.name = name;
        this.description = description;
        this.mandatory = mandatory;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public abstract void calculateReward(Player player);


}