/*
 * https://leetcode.com/problems/the-knights-tour/
 */



/*
 * Approach from -> https://leetcode.com/problems/the-knights-tour/solutions/3485987/java-backtracking-solution/
 */
class Solution {

    private int rows, cols;
    private int[][] res, board, dirs = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {-1, 2}, {1, 2}};

    private void backtrack(int x, int y, int stepNum) {
        if (stepNum == rows * cols - 1) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++)
                    res[i][j] = board[i][j];
            }
            return;
        }
        for (int[] dir : dirs) {
            int x1 = x + dir[0], y1 = y + dir[1];
            if (x1 >= 0 && x1 < rows && y1 >= 0 && y1 < cols && board[x1][y1] == -1) {
                board[x1][y1] = ++stepNum;
                backtrack(x1, y1, stepNum);
                board[x1][y1] = -1;
                stepNum--;
            }
        }
    }

    public int[][] tourOfKnight(int m, int n, int r, int c) {
        rows = m;
        cols = n;
        res = new int[m][n];
        board = new int[m][n];
        for (int[] row : board)
            Arrays.fill(row, -1);
        board[r][c] = 0;
        backtrack(r, c, 0);
        return res;
    }
}
