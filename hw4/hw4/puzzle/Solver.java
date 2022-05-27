package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    /**
     * Solver(initial): Constructor which solves the puzzle, computing
     *                  everything necessary for moves() and solution() to
     *                  not have to solve the problem again. Solves the
     *                  puzzle using the A* algorithm. Assumes a solution exists.
     * moves():         Returns the minimum number of moves to solve the puzzle starting
     *                  at the initial WorldState.
     * solution():      Returns a sequence of WorldStates from the initial WorldState
     *                  to the solution.
     */
    private MinPQ<Node> pq;
    private Queue<WorldState> sol;
    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        sol = new Queue<>();
        pq.insert(new Node(initial, 0, null));

        while (!pq.min().state.isGoal()) {
            Node min = pq.delMin();
            for (WorldState w : min.state.neighbors()) {
                if (min.par == null || !w.equals(min.par.state)) {
                    pq.insert(new Node(w, min.step + 1, min));
                }
            }
        }
    }
    public int moves() {
        return pq.min().step;
    }

    public Iterable<WorldState> solution() {
        Stack<WorldState> s = new Stack<>();
        Node result = pq.min();
        while (result != null) {
            s.push(result.state);
            result = result.par;
        }
        while (!s.isEmpty()) {
            sol.enqueue(s.pop());
        }
        return sol;
    }

    private class Node implements Comparable<Node> {
        WorldState state;
        int step;
        Node par;
        int pri;
        Node(WorldState start, int s, Node p) {
            state = start;
            step = s;
            par = p;
            pri = step + state.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(Node o) {
            return this.pri - o.pri;
        }
    }
}
