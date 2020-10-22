package quest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuildMapTest {

    GuildMap guildMap = new GuildMap();

    @Test
    void testConstructor(){
        assertEquals("Guild Map", guildMap.getName());
        assertEquals("You need this map to find the Guild, and be able to to important stuff.", guildMap.getDescription());
    }

}