package quest;

import player.Player;

public abstract class Quest {

    private final String name;
    protected String description;
    protected QuestState state;
    private boolean mandatory;
    //Quest log - available quests, completed quests osv


    public Quest(String name, String description, QuestState state, boolean mandatory){
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

    public QuestState getState(){
        return state;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public abstract boolean startRequirementsFulfilled(Player player);

    public abstract boolean startQuest(Player player);

    public abstract boolean endRequirementsFulfilled(Player player);

    public abstract boolean completeQuest(Player player);

    public abstract String toString();

}