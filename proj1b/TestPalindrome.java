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
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        //Test true
        assertTrue(palindrome.isPalindrome("aba"));
        assertTrue(palindrome.isPalindrome("aabbcccbbaa"));
        //Test false
        assertFalse(palindrome.isPalindrome("ab"));
        assertFalse(palindrome.isPalindrome("ababba"));
    }

    @Test
    public void isPalindromeTest() {
        CharacterComparator cc = new OffByOne();
        //Test true
        assertTrue(palindrome.isPalindrome("flake", cc));
        //Test false
        assertFalse(palindrome.isPalindrome("aba", cc));
    }
}
