//@author Christoffer Öhman
package magic;

public class Check {
    // Assure that a number is: >=0 <= Integer.max
    protected static int numberCheck(int nr) {
        if (nr < 0) {
            throw new IllegalArgumentException ("Error: negative numbers are not allowed here");
        }
        if (nr <Integer.MAX_VALUE) {
            return (nr);
        }
        throw new IllegalThreadStateException ("Error: Buffer Overflow, the number is too big.");
    }

    // If i decide to not allow empty strings. add. else remove.
    // Check if Strings are not blank, empty or null.
    public static void stringCheck(String string) {
        if (string == null || string.trim ().isEmpty ()) {
            throw new IllegalArgumentException ("Error: a blank string is not allowed here");
        }

    }
}
