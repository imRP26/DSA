/*
 * https://leetcode.com/problems/the-maze-iii/
 */



/*
 * Approach of Dijkstra's algo from LC Official Editorial!
 */
class Solution {

    class State {
        int row, col, dist;
        String path;
        public State(int row, int col, int dist, String path) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.path = path;
        }
    }

    private int rows, cols;
    String[] textDirs = new String[] {"r", "l", "d", "u"};
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private List<State> getNeighbors(int row, int col, int[][] maze, int[] hole) {
        List<State> neighbors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int currRow = row, currCol = col, count = 0;
            while (currRow + dirs[i][0] >= 0 && currRow + dirs[i][0] < rows && currCol + dirs[i][1] >= 0 && currCol + dirs[i][1] < cols && maze[currRow + dirs[i][0]][currCol + dirs[i][1]] == 0) {
                currRow += dirs[i][0];
                currCol += dirs[i][1];
                count++;
                if (currRow == hole[0] && currCol == hole[1])
                    break;
            }
            neighbors.add(new State(currRow, currCol, count, textDirs[i]));
        }
        return neighbors;
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        rows = maze.length;
        cols = maze[0].length;
        PriorityQueue<State> minPQ = new PriorityQueue<>((a, b) -> (a.dist == b.dist) ? a.path.compareTo(b.path) : a.dist - b.dist);
        boolean[][] vis = new boolean[rows][cols];
        minPQ.offer(new State(ball[0], ball[1], 0, ""));
        while (!minPQ.isEmpty()) {
            State curr = minPQ.poll();
            int row = curr.row, col = curr.col;
            if (vis[row][col])
                continue;
            if (row == hole[0] && col == hole[1])
                return curr.path;
            vis[row][col] = true;
            for (State nextState : getNeighbors(row, col, maze, hole))
                minPQ.offer(new State(nextState.row, nextState.col, curr.dist + nextState.dist, curr.path + nextState.path));
        }
        return "impossible";
    }
}
