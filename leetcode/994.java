import java.util.*;

/*
 * https://leetcode.com/problems/rotting-oranges/
 */



// My Naive BFS Approach
class Solution1 {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, columns = grid[0].length, numFresh = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 2) {
                    grid[i][j] = 0;
                    queue.offer(new int[] {i, j});    
                }
                else if (grid[i][j] == 1) {
                    grid[i][j] = Integer.MAX_VALUE;
                    numFresh++;
                }
                else
                    grid[i][j] = -1;
            }
        }
        if (queue.isEmpty()) {
            if (numFresh > 0)
                return -1;
            return 0;
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] direction : directions) {
                int x = direction[0] + cell[0], y = direction[1] + cell[1];
                if (x < 0 || x == rows || y < 0 || y == columns || grid[x][y] == -1 || grid[x][y] <= grid[cell[0]][cell[1]])
                    continue;
                grid[x][y] = 1 + grid[cell[0]][cell[1]];
                queue.offer(new int[] {x, y});
            }
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                result = Math.max(result, grid[i][j]);
        }
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }
}



// Cleaner BFS Solution
class Solution2 {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int countFresh = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 2)
                    queue.offer(new int[] {i, j});
                else if (grid[i][j] == 1)
                    countFresh++;
            }
        }
        if (countFresh == 0)
            return 0;
        int count = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int[] direction : directions) {
                    int x = point[0] + direction[0], y = point[1] + direction[1];
                    if (x < 0 || x == rows || y < 0 || y == columns || grid[x][y] == 0 || grid[x][y] == 2)
                        continue;
                    grid[x][y] = 2;
                    queue.offer(new int[] {x, y});
                    countFresh--;
                }
            }
        }
        return countFresh == 0 ? count - 1 : -1;
    }
}
