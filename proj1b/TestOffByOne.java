import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void equalCharsTest() {
        //In order
        assertTrue(offByOne.equalChars('a', 'b'));
        //Reverse
        assertTrue(offByOne.equalChars('b', 'a'));
        //False test
        assertFalse(offByOne.equalChars('a', 'z'));
        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('e', 'v'));
        assertFalse(offByOne.equalChars('&', '%'));
    }

}
