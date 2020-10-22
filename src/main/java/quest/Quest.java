package quest;

import player.Player;

public abstract class Quest {

    private String name;
    protected String description;
    protected Enum state;
    private boolean mandatory;
    //Quest log - available quests, completed quests osv


    public Quest(String name, String description, Enum state, boolean mandatory){
        this.name = name;
        this.description = description;
        this.state = state;
        this.mandatory = mandatory;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Enum getState(){
        return state;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public abstract boolean startRequirementsFulfilled(Player player);

    public abstract void startQuest(Player player);

    public abstract boolean endRequirementsFulfilled(Player player);

    public abstract void questCompleted(Player player);


}