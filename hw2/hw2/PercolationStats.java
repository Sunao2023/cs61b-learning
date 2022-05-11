package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    Percolation[] p;
    double[] result;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Error");
        }
        p = new Percolation[T];
        result = new double[T];
        for (int i = 0; i < T; i++) {
            p[i] = pf.make(N);
            while (!p[i].percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                p[i].open(row, col);
            }
            result[i] = (double) p[i].opened / N * N;
        }
    }
    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(result);
    }
    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(result);
    }
    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return (this.mean() - this.stddev() * 1.96 / Math.pow(p.length, 0.5));
    }
    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return (this.mean() + this.stddev() * 1.96 / Math.pow(p.length, 0.5));
    }
}
