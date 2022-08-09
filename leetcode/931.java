/*
 * https://leetcode.com/problems/minimum-falling-path-sum/
 */



// Simple Top-Down 2-D DP
class Solution1 {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length, result = Integer.MAX_VALUE;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            dp[0][i] = matrix[0][i];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Math.min(Integer.MAX_VALUE, matrix[i][j] + dp[i - 1][j]);
                if (j >= 1)
                    dp[i][j] = Math.min(dp[i][j], matrix[i][j] + dp[i - 1][j - 1]);
                if (j + 1 < n)
                    dp[i][j] = Math.min(dp[i][j], matrix[i][j] + dp[i - 1][j + 1]);
            }
        }
        for (int i = 0; i < n; i++)
            result = Math.min(result, dp[n - 1][i]);
        return result;
    }
}



// Space Optimized Version of the above Solution
class Solution2 {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length, index = 0, result = Integer.MAX_VALUE;
        int[][] dp = new int[2][n];
        for (int i = 0; i < n; i++)
            dp[0][i] = matrix[0][i];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                index = i % 2;
                dp[index][j] = Math.min(Integer.MAX_VALUE, matrix[i][j] + dp[1 - index][j]);
                if (j >= 1)
                    dp[index][j] = Math.min(dp[index][j], matrix[i][j] + dp[1 - index][j - 1]);
                if (j + 1 < n)
                    dp[index][j] = Math.min(dp[index][j], matrix[i][j] + dp[1 - index][j + 1]);
            }
        }
        for (int i = 0; i < n; i++)
            result = Math.min(result, dp[index][i]);
        return result;
    }
}
