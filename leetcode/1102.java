/*
 * https://leetcode.com/problems/path-with-maximum-minimum-value/
 */



/*
 * Easy Approach of BFS + Binary Search, DFS + Binary Search
 */
class Solution {

    private int rows, cols;
    private int[][] grid, dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private boolean bfs(int val) {
        if (grid[0][0] < val)
            return false;
        boolean[][] vis = new boolean[rows][cols];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int x1 = cell[0], y1 = cell[1];
            if (x1 == rows - 1 && y1 == cols - 1)
                return true;
            if (vis[x1][y1])
                continue;
            vis[x1][y1] = true;
            for (int[] d : dirs) {
                int x2 = x1 + d[0], y2 = y1 + d[1];
                if (x2 >= 0 && x2 < rows && y2 >= 0 && y2 < cols && !vis[x2][y2] && grid[x2][y2] >= val)
                    q.offer(new int[] {x2, y2});
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, int val, boolean[][] vis) {
        if (x == 0 && y == 0 && grid[0][0] < val)
            return false;
        if (x == rows - 1 && y == cols - 1)
            return true;
        vis[x][y] = true;
        for (int[] d : dirs) {
            int x1 = x + d[0], y1 = y + d[1];
            if (x1 >= 0 && x1 < rows && y1 >= 0 && y1 < cols && !vis[x1][y1] && grid[x1][y1] >= val && dfs(x1, y1, val, vis))
                return true;
        }
        return false;
    }

    public int maximumMinimumPath(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        int low = 0, high = (int)1e9, res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (bfs(mid)) {
                res = mid;
                low = mid + 1;
            }
            /*
            if (dfs(0, 0, mid, new boolean[rows][cols])) {
                res = mid;
                low = mid + 1;
            }
            */
            else
                high = mid - 1;
        }
        return res;
    }
}



/*
 * Easy Approach of 
 */

