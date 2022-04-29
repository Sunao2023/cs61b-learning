public class OffByN implements CharacterComparator {

    private int n;

    public OffByN(int x) {
        n = x;
    }
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }
}

