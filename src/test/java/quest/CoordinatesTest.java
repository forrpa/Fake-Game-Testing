package quest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    Coordinates coordinates = new Coordinates(5, 6);

    @Test
    void testConstructor(){
        assertEquals(5, coordinates.getX());
        assertEquals(6, coordinates.getY());
    }

    @Test
    void toStringReturnsCorrectString(){
        assertEquals("5,6", coordinates.toString());
    }

}