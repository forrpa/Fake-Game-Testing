package unit;

import java.util.ArrayList;
import java.util.List;

public class Questgiver extends NPC {
    private ArrayList<Quest> quests = new ArrayList<>();
    public Questgiver(String name, Quest quest){
        setName(name);
        quests.add(quest);
    }
    public Questgiver(String name, List quest){
        setName(name);
        quests.addAll(quest);
    }

    public List getAvailableQuests(Player player){
        ArrayList<Quest> attainableQuests = new ArrayList<>();
        for(Quest quest : quests){
            if(quest.startRequirementsFullfilled(player)){
                attainableQuests.add(quest);
            }
        }
        quests.removeAll(attainableQuests);
        return attainableQuests;
    }

    public List getAllQuests(){
        return quests;
    }
}
