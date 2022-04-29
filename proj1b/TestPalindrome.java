import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        //Test null and one character
        assertTrue(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome("a"));
        //Test true
        assertTrue(palindrome.isPalindrome("racecar"));
        //Test false
        assertFalse(palindrome.isPalindrome("cat"));
    }

    @Test
    public void isPalindromeTest() {
        CharacterComparator cc = new OffByOne();
        //Test null and one character
        assertTrue(palindrome.isPalindrome(null, cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        //Test true
        assertTrue(palindrome.isPalindrome("abab", cc));
        //Test false
        assertFalse(palindrome.isPalindrome("cat", cc));
    }
}
