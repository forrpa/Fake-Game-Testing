package quest;

import java.util.ArrayList;

public class QuestLog {

    private int completedQuestCount;
    private final ArrayList<Quest> availableQuests = new ArrayList<>();
    private final ArrayList<Quest> currentQuests = new ArrayList<>();
    private final ArrayList<Quest> completedQuests = new ArrayList<>();

    public int getCompletedQuestCount(){
        return completedQuestCount;
    }

    public ArrayList<Quest> getAvailableQuests() {
        return availableQuests;
    }

    public ArrayList<Quest> getCurrentQuests() {
        return currentQuests;
    }

    public ArrayList<Quest> getCompletedQuests() {
        return completedQuests;
    }

    public boolean isInQuestLog(Quest quest) {return availableQuests.contains(quest) || currentQuests.contains(quest) || completedQuests.contains(quest);}

    public boolean isInAvailableQuests(Quest quest){
        return availableQuests.contains(quest);
    }

    public boolean isInCurrentQuests(Quest quest){
        return currentQuests.contains(quest);
    }

    public boolean isInCompletedQuests(Quest quest){
        return completedQuests.contains(quest);
    }

    public void removeQuestFromAvailableQuests(Quest quest){
        if (isInAvailableQuests(quest)){
            availableQuests.remove(quest);
        }
    }

    public void removeQuestFromCurrentQuests(Quest quest){
        if (isInCurrentQuests(quest)){
            currentQuests.remove(quest);
        }
    }

    public void controlIfQuestIsInQuestLog(Quest quest){
        if (isInQuestLog(quest)){
            throw new RuntimeException("There can only be one of the same quest in Quest Log");
        }
    }

    public void addQuestToAvailableQuests(Quest quest){
        controlIfQuestIsInQuestLog(quest);
        availableQuests.add(quest);
    }

    public void addQuestToCurrentQuests(Quest quest){
        removeQuestFromAvailableQuests(quest);
        controlIfQuestIsInQuestLog(quest);
        currentQuests.add(quest);
    }

    public void addQuestToCompletedQuests(Quest quest){
        removeQuestFromCurrentQuests(quest);
        controlIfQuestIsInQuestLog(quest);
        completedQuests.add(quest);
        completedQuestCount++;
    }

    @Override
    public final String toString() {
        String result = "";
        if(availableQuests.isEmpty()) result+="Available quests is empty!\n";
        else {
            for(Quest q : availableQuests) {
                result += "{" + q.toString() + "}\n";
            }
        }
        if(currentQuests.isEmpty()) result+="Current quests is empty!\n";
        else {
            for(Quest q : currentQuests) {
                result += "{" + q.toString() + "}\n";
            }
        }
        if(completedQuests.isEmpty()) result+="Completed quests is empty!\n";
        else {
            for(Quest q : completedQuests) {
                result += "{" + q.toString() + "}\n";
            }
        }
        return result;
    }

}
