public class OffByN implements CharacterComparator{

    int n;

    public OffByN(int x) {
        n = x;
    }
    @Override
    public boolean equalChars(char x, char y) {
        if ((x >= 97 && x <= 122) && (y >= 97 && y <= 122)) {
            int diff = Math.abs(x - y);
            if (diff == n) {
                return true;
            }
        }
        return false;
    }
}

