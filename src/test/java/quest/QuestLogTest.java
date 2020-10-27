package quest;

import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

//Quest difficulty, baserar reward på level och difficulty, tre färger
//Required level för att starta questet
//Abandon quest leder till att den är incomplete

class QuestLogTest {

    TalkToGuildLeader quest = new TalkToGuildLeader();
    Player player = new Player("Tank", "Human", 200, 1500);
    QuestLog questLog = new QuestLog();

    //Available quest läggs in i rätt lista
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

    //Completed quest läggs in i rätt lista
    @Test
    void completedQuestShouldBeInCompletedQuestsList(){
        quest.talkToGuildLeader();
        quest.completeQuest(player);
        assertTrue(player.isInCompletedQuests(quest));
    }

    //Lägga in mer en en quest ska ge exception
    @Test
    void sameQuestIsAddedMoreThanOnceThrowsException(){
        player.addQuestToAvailableQuests(quest);
        assertThrows(RuntimeException.class, () -> player.addQuestToAvailableQuests(quest));
    }

    //Quest går över från available till current, ska tas bort från available list
    @Test
    void currentQuestGetsRemovedFromAvailableQuestsList(){
        quest.startQuest(player);
        assertFalse(player.isInAvailableQuests(quest));
    }

    //Quest går över från current till completed, ska tas bort från current list
    @Test
    void completedQuestGetsRemovedFromCurrentQuestsList(){
        quest.startQuest(player);
        quest.talkToGuildLeader();
        quest.completeQuest(player);
        assertFalse(player.isInCurrentQuests(quest));
    }

    //Rätt antal klarade quests
    @Test
    void correctNumberOfCompletedQuests(){
        quest.talkToGuildLeader();
        quest.completeQuest(player);
        assertEquals(1, player.getCompletedQuestCount(quest));
    }

    //toString funkar med ett quest i Availabe Quests
    @Test
    void toStringMethodReturnsCorrectQuestWhenAvailableQuestsContainOneQuest(){
        questLog.addQuestToAvailableQuests(quest);
        assertEquals("{Talk to Guild Leader: You have to talk to the guild leader west of town.. PENDING, true, false}\nCurrent quests is empty!\nCompleted quests is empty!\n", questLog.toString());
    }

    //toString funkar med ett quest i Current Quests
    @Test
    void toStringMethodReturnsCorrectQuestsWhenCurrentQuestsContainOneQuest(){
        questLog.addQuestToCurrentQuests(quest);
        assertEquals("Available quests is empty!\n{Talk to Guild Leader: You have to talk to the guild leader west of town.. PENDING, true, false}\nCompleted quests is empty!\n", questLog.toString());
    }

    //toString funkar med ett quest i Completed Quests
    @Test
    void toStringMethodReturnsCorrectQuestsWhenCompletedQuestsContainOneQuest(){
        questLog.addQuestToCompletedQuests(quest);
        assertEquals("Available quests is empty!\nCurrent quests is empty!\n{Talk to Guild Leader: You have to talk to the guild leader west of town.. PENDING, true, false}\n", questLog.toString());
    }
}