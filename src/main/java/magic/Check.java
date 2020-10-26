//@author Christoffer Öhman
package magic;

public class Check {
    // Assure that a number is: >=0
    protected static int numberCheck(int nr) {
        if (nr >= 0) {
            return nr;
        } else {
            throw new IllegalArgumentException ("Error: negative numbers are not allowed here");
        }
    }

    // If i decide to not allow empty strings. add. else remove.
    // Check if Strings are not blank, empty or null.
    private static void stringCheck(String string) {
        if (string == null || string.trim ().isEmpty ()) {
            throw new IllegalArgumentException ("Error: a blank string is not allowed here");
        }

    }
}
