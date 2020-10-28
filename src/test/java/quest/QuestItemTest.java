package quest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestItemTest {

    QuestItem questItem = new QuestItem("Apple", "That is red");

    @Test
    void toStringReturnsCorrectString(){
        assertEquals("Apple: That is red", questItem.toString());
    }

}