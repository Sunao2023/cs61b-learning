public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y) {
        if ((x >= 97 && x <= 122) && (y >= 97 && y <= 122)) {
            int diff = Math.abs(x - y);
            if (diff == 1) {
                return true;
            }
        }
        return false;
    }
}
