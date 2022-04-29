import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void equalCharsTest() {
        //In order
        assertTrue(offByN.equalChars('a', 'f'));
        //Reverse
        assertTrue(offByN.equalChars('f', 'a'));
        //False test
        assertFalse(offByN.equalChars('a', 'z'));
        assertFalse(offByN.equalChars('a', 'a'));
        assertFalse(offByN.equalChars('e', 'v'));
        assertFalse(offByN.equalChars('&', '%'));
    }
}
