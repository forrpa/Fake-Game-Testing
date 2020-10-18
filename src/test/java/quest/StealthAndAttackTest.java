package quest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StealthAndAttackTest {

    //Behöver veta: Om man blir upptäckt, om HP blir noll, om man pratat med han som gav uppdraget eller den andra personen, om tiden gick ut
    //Attack eller pengar
    //Använda Guild Map till att hitta personen efter attacken

    @Test
    void testConstructor(){
        StealthAndAttack quest = new StealthAndAttack("Stealt and Attack", "You have to follow your enemy without being seen and then attack him", "pending", true, false, false, false);
        assertEquals("Stealt and Attack", quest.getName());
        assertEquals("You have to follow your enemy without being seen and then attack him", quest.getDescription());
        assertEquals("pending", quest.getState());
        assertTrue(quest.isMandatory());
        assertFalse(quest.isDiscovered());
        assertFalse(quest.isAttacked());
        assertFalse(quest.hasTalkedToQuestMaker());
    }

}