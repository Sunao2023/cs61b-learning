package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;
public class Board implements WorldState {
    /** Board(tiles): Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j
     tileAt(i, j): Returns value of tile at row i, column j (or 0 if blank)
     size():       Returns the board size N
     neighbors():  Returns the neighbors of the current board
     hamming():    Hamming estimate described below
     manhattan():  Manhattan estimate described below
     estimatedDistanceToGoal(): Estimated distance to goal. This method should
     simply return the results of manhattan() when submitted to
     Gradescope.
     equals(y):    Returns true if this board's tile values are the same
     position as y's
     toString():   Returns the string representation of the board. This
     method is provided in the skeleton
     */
    private final int N;
    private int[][] puzzle;
    private final int BLANK = 0;

    public Board(int[][] tiles) {
        N = tiles.length;
        puzzle = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(tiles[i], 0, puzzle[i], 0, N);
        }
    }
    public int tileAt(int i, int j) {
        if (i >= 0 && j >= 0 && i < N && j < N) {
            return puzzle[i][j];
        }
        throw new IndexOutOfBoundsException("Overflow!");
    }
    public int size() {
        return N;
    }

    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    private int xyToOne(int x, int y) {
        if (x == N - 1 && y == N - 1) {
            return 0;
        }
        return x * N + y + 1;
    }
    public int hamming() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (puzzle[i][j] != xyToOne(i, j) && puzzle[i][j] != BLANK) {
                    count++;
                }
            }
        }
        return count;
    }

    private int toX(int pos) {
        return (pos - 1) / N;
    }
    private int toY(int pos) {
        return (pos - 1) % N;
    }
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int pos = xyToOne(i, j);
                if (puzzle[i][j] != pos && puzzle[i][j] != BLANK) {
                    count += Math.abs(toX(pos) - i) + Math.abs(toY(pos) - j);
                }
            }
        }
        return count;
    }
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        return this.toString().equals(y.toString());
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    public int hashCode() {
        return this.toString().hashCode();
    }
}
