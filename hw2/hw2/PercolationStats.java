package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private Percolation[] p;
    private double[] result;
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;
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
                int row = StdRandom.uniform(0, N );
                int col = StdRandom.uniform(0, N );
                p[i].open(row, col);
            }
            result[i] = (double) p[i].numberOfOpenSites() / (N * N);
        }
        mean = StdStats.mean(result);
        stddev = StdStats.stddev(result);
        confidenceLow = this.mean() - this.stddev() * 1.96 / Math.pow(p.length, 0.5);
        confidenceHigh = this.mean() + this.stddev() * 1.96 / Math.pow(p.length, 0.5);
    }
    public double mean() {
        // sample mean of percolation threshold
        return mean;
    }
    public double stddev() {
        // sample standard deviation of percolation threshold
        return stddev;
    }
    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return confidenceLow;
    }
    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return confidenceHigh;
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats p = new PercolationStats(10, 10, pf);
        double mean = p.mean();
        double std = p.stddev();
    }
}
