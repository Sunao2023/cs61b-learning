package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int opened, side;
    private WeightedQuickUnionUF WQU;

    private int[] check;
    private int checkIndex;

    public Percolation(int N) {   // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IllegalArgumentException("N should be bigger than 0");
        }
        side = N;
        opened = 0;
        WQU = new WeightedQuickUnionUF(N * N + 1);
        grid = new boolean[N][N];
        check = new int[N];
        checkIndex = 0;
        for (int i = 0; i < N; i++) {
            WQU.union(i, N * N);
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }

    private int xyToInt(int row, int col) {
        return row * side + col;
    }

    private int isBoundary(int row, int col) {
        if (row == 0) {
            return 1;
        } else if (row == side - 1) {
            return 2;
        } else if (col == 0) {
            return 3;
        } else if (col == side - 1) {
            return 4;
        } else {
            return 0;
        }
    }

    private void flow(int x1, int y1, int x2, int y2) {
        if (grid[x1][y1] && grid[x2][y2]) {
            WQU.union(xyToInt(x1, y1), xyToInt(x2, y2));
        }
    }

    private void openHelper(int row, int col) {
        grid[row][col] = true;
        opened += 1;
        int b = isBoundary(row, col);
        switch (b) {
            case 1: {
                if (col != 0) {
                    flow(row, col, row, col - 1);
                }
                if (col != side - 1) {
                    flow(row, col, row, col + 1);
                }
                if (side != 1) {
                    flow(row, col, row + 1, col);
                }
                if (side == 1) {
                    check[checkIndex] = xyToInt(row, col);
                    checkIndex++;
                }
                break;
            }
            case 2: {
                if (col != 0) {
                    flow(row, col, row, col - 1);
                }
                if (col != side - 1) {
                    flow(row, col, row, col + 1);
                }
                flow(row, col, row - 1, col);
                check[checkIndex] = xyToInt(row, col);
                checkIndex++;
                break;
            }
            case 3: {
                flow(row, col, row + 1, col);
                flow(row, col, row, col + 1);
                flow(row, col, row - 1, col);
                break;
            }
            case 4: {
                flow(row, col, row + 1, col);
                flow(row, col, row, col - 1);
                flow(row, col, row - 1, col);
                break;
            }
            case 0: {
                flow(row, col, row + 1, col);
                flow(row, col, row, col + 1);
                flow(row, col, row, col - 1);
                flow(row, col, row - 1, col);
                break;
            }
            default: {
            }
        }
    }

    public void open(int row, int col) {     // open the site (row, col) if it is not open already
        if (row < 0 || row > side - 1 || col < 0 || col > side - 1) {
            throw new IndexOutOfBoundsException("Error");
        }
        if (isOpen(row, col)) {
            return;
        }
        openHelper(row, col);
    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        if (row < 0 || row > side - 1 || col < 0 || col > side - 1) {
            throw new IndexOutOfBoundsException("Error");
        }
        return grid[row][col];
    }
    public boolean isFull(int row, int col) { // is the site (row, col) full?
        if (row < 0 || row > side - 1 || col < 0 || col > side - 1) {
            throw new IndexOutOfBoundsException("Error");
        }
        return WQU.connected(xyToInt(row, col), side * side) && isOpen(row, col);
    }
    public int numberOfOpenSites() {          // number of open sites
        return opened;
    }
    public boolean percolates() {             // does the system percolate?
        for (int i = 0; i < checkIndex; i++) {
            if (WQU.connected(check[i], side * side)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {

    }
}
