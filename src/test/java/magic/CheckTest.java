package magic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static magic.Check.numberCheck;
import static magic.Check.stringCheck;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckTest {

    @Test
    void NumberCheckTest() {
        int maxValue = Integer.MAX_VALUE;
        int maxValueMinusOne = maxValue - 1;
        // negative number test,
        Assertions.assertThrows (IllegalArgumentException.class, () -> numberCheck (-1));

        //maxValue test
        Assertions.assertThrows (IllegalThreadStateException.class, () -> numberCheck (maxValue));

        // allowed numbers test
        assertEquals (0, numberCheck (0));
        assertEquals (1, numberCheck (1));
        assertEquals (maxValueMinusOne, numberCheck (maxValueMinusOne));


    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void emptyStringCheckTest(final String testString) {
        Assertions.assertThrows (IllegalArgumentException.class, () -> stringCheck (testString));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aåäfdgsgö1230", " 12334sfgsgäåö", "ewåsfdgsdgsäAF34\t", "agagfgsfdgsdfgsdfg4t56\n"})
    void stringCheckTest(final String testString) {
        assertEquals (testString, stringCheck (testString));


    }
}