//@author Christoffer Öhman
package magic;


public class ValueCheck {
    // Assure that a number is: >=0 <= Integer.max
    protected static int numberCheck(int nr) {
        if (nr < 0) {
            throw new IllegalArgumentException ("Error: negative numbers are not allowed here");
        }
        if (nr >= Integer.MAX_VALUE) {
            throw new IllegalThreadStateException ("Error: Buffer Overflow, the number is too big.");
        } else {
            return (nr);
        }
    }


    // Assure that a string is not blank, empty or null.
    public static String stringCheck(String string) {
        if (string == null || string.trim ().isEmpty ()) {
            throw new IllegalArgumentException ("Error: a blank string is not allowed here");
        }
        return string;

    }
}
