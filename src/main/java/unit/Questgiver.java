package unit;

import java.util.ArrayList;
import java.util.List;
import quest.*;
import player.*;

public class Questgiver extends NPC {
    private ArrayList<Quest> quests = new ArrayList<>();
    public Questgiver(String name, Quest quest){
        this(name);
        quests.add(quest);
    }
    public Questgiver(String name, List quest){
        this(name);
        quests.addAll(quest);
    }
    private Questgiver(String name){
        super(name, 100, true, null, null);
    }
    public List getAvailableQuests(Player player){
        ArrayList<Quest> attainableQuests = new ArrayList<>();
        for(Quest quest : quests){
            if(quest.startRequirementsFulfilled(player)){
                attainableQuests.add(quest);
            }
        }
        quests.removeAll(attainableQuests);
        return attainableQuests;
    }

    public String getAllQuestNames(){
        String allQuestNames = "";
        if(quests.size() > 0){
            StringBuilder sb = new StringBuilder();
            for(Quest quest : quests){
                sb.append(quest.getName()).append(", ");
            }
            allQuestNames = sb.deleteCharAt(sb.length() - 2).toString().trim();
        }
        return allQuestNames;
    }
    @Override
    public String toString(){
        return String.format("Name: %s, Alive: %b, Quests available: %s.", getName(), isAlive(), getAllQuestNames());
    }
}
