import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuestTest {

    @Test
    void testClass(){
        Quest q = new Quest();
        assertEquals(33, q.count);
    }
}