/*
 * https://leetcode.com/problems/unique-paths-ii/
 */



// Naive 2-D DP
class Solution1 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, columns = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[rows - 1][columns - 1] == 1)
            return 0;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < columns; i++) {
            if (obstacleGrid[0][i] == 1)
                break;
            dp[0][i] = 1;
        }
        for (int i = 0; i < rows; i++) {
            if (obstacleGrid[i][0] == 1)
                break;
            dp[i][0] = 1;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[rows - 1][columns - 1];
    }
}



// 
