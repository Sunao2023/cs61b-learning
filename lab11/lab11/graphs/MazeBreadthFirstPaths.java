package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // Your code here. Don't forget to update distTo
        // , edgeTo, and marked, as well as call announce()
        marked[s] = true;
        announce();
        int v = s;
        Queue<Integer> q = new Queue<>();
        q.enqueue(s);

        while (!targetFound) {
            for (int i : maze.adj(v)) {
                if (!marked[i]) {
                    q.enqueue(i);
                    edgeTo[i] = v;
                    marked[i] = true;
                    distTo[i] = distTo[v] + 1;
                    announce();
                }
            }
            v = q.dequeue();
            if (v == t) {
                marked[v] = true;
                announce();
                targetFound = true;
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

