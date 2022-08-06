import java.util.*;

/*
 * https://leetcode.com/problems/walls-and-gates/
 */



// Approach of BFS
class Solution1 {
    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length, columns = rooms[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (rooms[i][j] == 0)
                    queue.offer(new int[] {i, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] direction : directions) {
                int x = cell[0] + direction[0], y = cell[1] + direction[1];
                if (x < 0 || x == rows || y < 0 || y == columns || rooms[x][y] == -1 || rooms[x][y] <= rooms[cell[0]][cell[1]])
                    continue;
                rooms[x][y] = 1 + rooms[cell[0]][cell[1]];
                queue.offer(new int[] {x, y});
            }
        }
    }
}
