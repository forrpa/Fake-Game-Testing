package unit;

import java.util.ArrayList;
import java.util.List;
import quest.*;
import player.*;

public class Questgiver extends Villager {
    private ArrayList<Quest> quests = new ArrayList<>();
    public Questgiver(String name, Quest quest){
        this(name);
        checkQuest(quest);
        quests.add(quest);
    }
    public Questgiver(String name, List<Quest> quests){
        this(name);
        System.out.println(quests);
        checkQuest(quests);
        this.quests.addAll(quests);
    }
    private Questgiver(String name){
        super(name, 100,1, true, null, null);
    }
    public void takeAvailableQuests(Player player){
        ArrayList<Quest> attainedQuests = new ArrayList<>();
        for(Quest quest : quests){
            if(quest.startQuest(player)){
                attainedQuests.add(quest);
            }
        }
        quests.removeAll(attainedQuests);
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

    private void checkQuest(Quest quest){
        if(quest == null){
            throw new IllegalArgumentException("Quest can't be null");
        }
    }
    private void checkQuest(List<Quest> quest){
        if(quest == null){
            throw new IllegalArgumentException("Quest can't be null");
        }else if(quest.isEmpty()){
            throw new IllegalArgumentException("Questgiver needs quests");
        }
    }
}
