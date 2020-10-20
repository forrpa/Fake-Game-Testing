package unit;

import java.util.ArrayList;
import java.util.List;
import quest.*;
import player.*;

public class Questgiver extends NPC {
    private ArrayList<Quest> quests = new ArrayList<>();
    public Questgiver(String name, Quest quest){
        super(name);
        quests.add(quest);
    }
    public Questgiver(String name, List quest){
        super(name);
        quests.addAll(quest);
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

    public List getAllQuests(){
        ArrayList<String> allQuests = new ArrayList<>();
        for(Quest quest : quests){
            allQuests.add(quest.getName());
        }
        return allQuests;
    }
}
