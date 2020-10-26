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
}
