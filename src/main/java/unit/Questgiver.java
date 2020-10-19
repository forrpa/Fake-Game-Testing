package unit;

import java.util.ArrayList;
import java.util.List;

public class Questgiver extends NPC implements Interactable {
    private ArrayList<Quest> quests = new ArrayList<>();
    public Questgiver(String name, Quest quest){
        setName(name);
        quests.add(quest);
    }
    public Questgiver(String name, List quest){
        setName(name);
        quests.addAll(quest);
    }

    public void interact(){
        
    }
}
