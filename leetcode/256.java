/*
 * https://leetcode.com/problems/paint-house/
 */



// Simple DP
class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                for (int j = 0; j < 3; j++)
                    dp[i][j] = costs[i][j];
                continue;
            }
            for (int j = 0; j < 3; j++)
                dp[i][j] = costs[i][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
        }
        return Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
    }
}
