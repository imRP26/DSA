/*
 * https://leetcode.com/problems/unique-paths-iii/
 */



/*
 * DFS + Backtracking (My Naive Approach)
 * https://leetcode.com/problems/unique-paths-iii/solutions/810640/unique-paths-iii/?orderBy=most_votes
 */ 
class Solution {

    int rows, columns, numSquares, destx, desty, result, numObstacles, srcx, srcy;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean isValidCell(int[][] grid, int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < columns && grid[x][y] != -1;
    }

    private void dfs(int[][] grid, int x, int y) {
        if (x == destx && y == desty) {
            if (numSquares == rows * columns - numObstacles - 1)
                result++;
            return;
        }
        grid[x][y] = -1;
        numSquares++;
        for (int[] direction : directions) {
            int x1 = x + direction[0], y1 = y + direction[1];
            if (isValidCell(grid, x1, y1))
                dfs(grid, x1, y1);
        }
        if (x == srcx && y == srcy)
            grid[x][y] = 1;
        else
            grid[x][y] = 0;
        numSquares--;
    }

    public int uniquePathsIII(int[][] grid) {
        rows = grid.length;
        columns = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 1) {
                    srcx = i;
                    srcy = j;
                }
                else if (grid[i][j] == 2) {
                    destx = i;
                    desty = j;
                }
                else if (grid[i][j] == -1) {
                    numObstacles++;
                }
            }
        }
        dfs(grid, srcx, srcy);
        return result;
    }
}
 