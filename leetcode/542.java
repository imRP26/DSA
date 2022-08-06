import java.util.*;

/*
 * https://leetcode.com/problems/01-matrix/
 */



/*
 * Approach using BFS :-
 * Keep all the mat values containing 0 initially into the BFS queue, if a cell 
 * ain't 0, then keep its value as INT_MAX, which will denote that particular 
 * cell hasn't been visited.
 * Perform BFS from the cells that have been inserted into the queue.
 */
class Solution1 {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length, columns = mat[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mat[i][j] == 0)
                    queue.offer(new int[] {i, j});
                else
                    mat[i][j] = Integer.MAX_VALUE;
            }
        }
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int row = temp[0], column = temp[1], val = row * rows + column;
            for (int[] direction : directions) {
                int x = row + direction[0], y = column + direction[1];
                /*
                 * No need to explore any further if the new dimensions aren't 
                 * proper or if the cell to be newly explored already has a lower
                 * value.
                 */
                if (x < 0 || x >= rows || y < 0 || y >= columns || mat[x][y] <= mat[row][column])
                    continue;
                queue.offer(new int[] {x, y});
                mat[x][y] = mat[row][column] + 1;
            }
        }
        return mat;
    }
} 



/*
 * Approach using DP :-
 * The 1st iteration is from upper left corner to lower right. It updates 
 * result[i][j] to be the distance to the nearest 0 that's in the top left 
 * region relative to (i, j). If there's a nearer 0 to the right or to the bottom 
 * of (i, j), that won't get caught. Because of the direction of the double loop, 
 * its already in correct iterative order, meaning we must have dealt with 
 * result[i - 1][j] and result[i][j - 1] before result[i][j] is dealt with.
 * Then in the 2nd loop, traversal is done from the bottom right corner to the 
 * upper left corner. This and the above iterations will cover the nearest 0 
 * from all the directions.
 */
class Solution2 {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length, columns = mat[0].length, range = rows * columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mat[i][j] == 0)
                    continue;
                int upCell = (i > 0) ? mat[i - 1][j] : range;
                int leftCell = (j > 0) ? mat[i][j - 1] : range;
                mat[i][j] = Math.min(upCell, leftCell) + 1;
            }
        }
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = columns - 1; j >= 0; j--) {
                int downCell = (i + 1 < rows) ? mat[i + 1][j] : range;
                int rightCell = (j + 1 < columns) ? mat[i][j + 1] : range;
                mat[i][j] = Math.min(1 + Math.min(downCell, rightCell), mat[i][j]);
            }
        }
        return mat;
    }
}
