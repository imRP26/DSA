import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/max-area-of-island/
 */



// Simple BFS
class Solution1 {
    
    public int bfs(int i, int j, int[][] grid) {
        int rows = grid.length, columns = grid[0].length, area = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            i = cell[0];
            j = cell[1];
            if (grid[i][j] == 0)
                continue;
            grid[i][j] = 0;
            area++;
            if (i < rows - 1 && grid[i + 1][j] == 1)
                queue.offer(new int[] {i + 1, j});
            if (j < columns - 1 && grid[i][j + 1] == 1)
                queue.offer(new int[] {i, j + 1});
            if (i >= 1 && grid[i - 1][j] == 1)
                queue.offer(new int[] {i - 1, j});
            if (j >= 1 && grid[i][j - 1] == 1)
                queue.offer(new int[] {i, j - 1});
        }
        return area;
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    result = Math.max(result, bfs(i, j, grid));
            }
        }
        return result;
    }
}



// Simple DFS Solution
class Solution2 {
    
    public int dfs(int i, int j, int[][] grid) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            grid[i][j] = 0;
            return 1 + dfs(i + 1, j, grid) + dfs(i, j + 1, grid) + dfs(i - 1, j, grid) + dfs(i, j - 1, grid);
        }
        return 0;
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    result = Math.max(result, dfs(i, j, grid));
            }
        }
        return result;
    }
}
