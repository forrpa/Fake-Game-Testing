package quest;

import java.util.ArrayList;

public class QuestLog {

    //endast finnas ett quest i varje

    private int completedQuestCount;
    private final ArrayList<Quest> availableQuests = new ArrayList<>();
    private final ArrayList<Quest> currentQuests = new ArrayList<>();
    private final ArrayList<Quest> completedQuests = new ArrayList<>();

    public int getCompletedQuestCount(){
        return completedQuestCount;
    }

    public boolean isInAvailableQuests(Quest quest){
        return availableQuests.contains(quest);
    }

    public boolean isInCurrentQuests(Quest quest){
        return currentQuests.contains(quest);
    }

    public boolean isInCompletedQuests(Quest quest){
        return completedQuests.contains(quest);
    }

    public void addQuestToQuestLog(Quest quest){
        if (quest.state == QuestState.UNLOCKED) {
            addQuestToAvailableQuests(quest);
        } else if (quest.state == QuestState.IN_PROGRESS){
            addQuestToCurrentQuests(quest);
            //removeQuest(quest);
            availableQuests.remove(quest);
        } else if (quest.state == QuestState.DONE){
            addQuestToCompletedQuests(quest);
            //removeQuest(quest);
            currentQuests.remove(quest);
        } else {
            //Problem
        }
    }

    /*
    public void removeQuest(Quest quest){
        if(availableQuests.isEmpty()) throw new NullPointerException("Quest Log is empty.");
        if(!availableQuests.contains(quest)) throw new NullPointerException("Item is not in Quest Log.");
        availableQuests.remove(quest);
    }*/

    public void addQuestToAvailableQuests(Quest quest){
        availableQuests.add(quest);
    }

    public void addQuestToCurrentQuests(Quest quest){
        currentQuests.add(quest);
    }

    public void addQuestToCompletedQuests(Quest quest){
        completedQuests.add(quest);
        completedQuestCount++;
    }

    //toString


}
