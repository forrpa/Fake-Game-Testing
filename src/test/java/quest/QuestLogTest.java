package quest;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

//Quest difficulty, baserar reward på level och difficulty, tre färger
//Required level för att starta questet
//Abandon quest leder till att den är incomplete
//Kontrollera att endast ett quest kan läggas till

class QuestLogTest {

    TalkToGuildLeader quest = new TalkToGuildLeader();
    Player player = new Player("Tank", "Human", 200, 1500);

    @Test
    void availableQuestShouldBeInAvailableQuestsList(){
        quest.startRequirementsFulfilled(player);
        assertTrue(player.isInAvailableQuests(quest));
    }

    //In progress-quest läggs i rätt lista
    @Test
    void currentQuestShouldBeInCurrentQuestsList() {
        quest.startQuest(player);
        assertTrue(player.isInCurrentQuests(quest));
    }

    @Test
    void completedQuestShouldBeInCompletedQuestsList(){
        quest.talkToGuildLeader();
        quest.completeQuest(player);
        assertTrue(player.isInCompletedQuests(quest));
    }

    //Quest går över från available till current, ska tas bort
    @Test
    void currentQuestGetsRemovedFromAvailableQuestsList(){
        quest.startQuest(player);
        assertFalse(player.isInAvailableQuests(quest));
    }

    //Quest går över från current till completed, ska tas bort
    @Test
    void completedQuestGetsRemovedFromCurrentQuestsList(){
        quest.startQuest(player);
        quest.talkToGuildLeader();
        quest.completeQuest(player);
        assertFalse(player.isInCurrentQuests(quest));
    }

    //Returnera rätt quests

    //Rätt antal quests
    @Test
    void correctNumberOfCompletedQuests(){
        quest.talkToGuildLeader();
        quest.completeQuest(player);
        assertEquals(1, player.getCompletedQuestCount(quest));
    }

    //Failed quests...?


}