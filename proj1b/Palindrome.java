public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new LinkedListDeque<>();
        char[] a = word.toCharArray();
        for (char i : a) {
            charDeque.addLast(i);
        }
        return charDeque;
    }

    private boolean isHelper(Deque<Character> charDeque) {
        if (charDeque.isEmpty() || charDeque.size() == 1) {
            return true;
        }
        char first = charDeque.removeFirst();
        char last = charDeque.removeLast();
        if (first == last) {
            return isHelper(charDeque);
        }
        return false;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> charDeque = wordToDeque(word);
        return isHelper(charDeque);
    }

    private boolean isHelper(Deque<Character> charDeque, CharacterComparator cc) {
        if (charDeque.isEmpty() || charDeque.size() == 1) {
            return true;
        }
        char first = charDeque.removeFirst();
        char last = charDeque.removeLast();
        if (cc.equalChars(first, last)) {
            return isHelper(charDeque, cc);
        }
        return false;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> charDeque = wordToDeque(word);
        return isHelper(charDeque, cc);
    }
}
