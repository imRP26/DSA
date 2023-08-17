/*
 * https://leetcode.com/problems/the-maze/
 */



/*
 * Approach of DFS from LC Official Editorial!
 * Here, the key thing is the cells that are to be inserted into the queue.
 */
class Solution {

    private int rows, cols;
    private boolean[][] vis;
    private int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private boolean dfs(int[][] maze, int[] cell, int[] dest) {
        if (vis[cell[0]][cell[1]])
            return false;
        if (cell[0] == dest[0] && cell[1] == dest[1])
            return true;
        vis[cell[0]][cell[1]] = true;
        for (int[] dir : dirs) {
            int x = cell[0], y = cell[1];
            while (x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
            }
            if (dfs(maze, new int[] {x - dir[0], y - dir[1]}, dest))
                return true;
        }
        return false;
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        rows = maze.length;
        cols = maze[0].length;
        vis = new boolean[rows][cols];
        return dfs(maze, start, destination);
    }
}



/*
 * Approach of BFS from LC Official Editorial!
 */
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length, cols = maze[0].length;
        boolean[][] vis = new boolean[rows][cols];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        vis[start[0]][start[1]] = true;
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int x = cell[0], y = cell[1];
            if (x == destination[0] && y == destination[1])
                return true;
            for (int[] dir : dirs) {
                int x1 = x, y1 = y;
                while (x1 >= 0 && x1 < rows && y1 >= 0 && y1 < cols && maze[x1][y1] == 0) {
                    x1 += dir[0];
                    y1 += dir[1];
                }
                x1 -= dir[0];
                y1 -= dir[1];
                if (!vis[x1][y1]) {
                    vis[x1][y1] = true;
                    q.offer(new int[] {x1, y1});
                }
            }
        }
        return false;
    }
}
