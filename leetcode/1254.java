/*
 * https://leetcode.com/problems/number-of-closed-islands/
 */



/*
 * 1 of those questions that appear quite tricky in the 1st go, but become very easy when the hints
 * are referred to!
 * Simple BFS & a condition checking!!
 */
class Solution {

    private int rows, columns;
    private boolean[][] visited;

    private int bfs(int x, int y, int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});
        int returnValue = 1;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            x = node[0];
            y = node[1];
            if (visited[x][y])
                continue;
            visited[x][y] = true;
            if (x == 0 || x == rows - 1 || y == 0 || y == columns - 1)
                returnValue = 0;
            for (int[] direction : directions) {
                int x1 = x + direction[0], y1 = y + direction[1];
                if (x1 >= 0 && x1 < rows && y1 >= 0 && y1 < columns && !visited[x1][y1] && grid[x1][y1] == 0)
                    queue.offer(new int[] {x1, y1});
            }
        }
        return returnValue;
    }

    public int closedIsland(int[][] grid) {
        rows = grid.length;
        columns = grid[0].length;
        visited = new boolean[rows][columns];
        int result = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                if (!visited[i][j] && grid[i][j] == 0)
                    result += bfs(i, j, grid);
            }
        }
        return result;
    }
}



/*
 * Same Approach as above, but using DFS!!
 */
class Solution {

    private int rows, columns;
    private boolean isClosed;
    private boolean[][] visited;
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void dfs(int x, int y, int[][] grid) {
        if (x == 0 || y == 0 || x == rows - 1 || y == columns - 1)
            isClosed = false;
        if (visited[x][y])
            return;
        visited[x][y] = true;
        for (int[] direction : directions) {
            int x1 = x + direction[0], y1 = y + direction[1];
            if (x1 >= 0 && x1 < rows && y1 >= 0 && y1 < columns && !visited[x1][y1] && grid[x1][y1] == 0)
                dfs(x1, y1, grid);
        }
    }

    public int closedIsland(int[][] grid) {
        rows = grid.length;
        columns = grid[0].length;
        visited = new boolean[rows][columns];
        int result = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                if (!visited[i][j] && grid[i][j] == 0) {
                    isClosed = true;
                    dfs(i, j, grid);
                    if (isClosed)
                        result++;
                }
            }
        }
        return result;
    }
}
