/*
 * https://leetcode.com/problems/maximum-profit-from-trading-stocks/
 */



/*
 * DP with Memoization
 */
class Solution1 {

    private int[][] dp;

    private int memoization(int[] present, int[] future, int index, int budget) {
        if (budget < 0 || index == present.length)
            return 0;
        if (dp[index][budget] != Integer.MIN_VALUE)
            return dp[index][budget];
        if (present[index] >= future[index] || present[index] > budget)
            return memoization(present, future, index + 1, budget);
        return dp[index][budget] = Math.max(memoization(present, future, index + 1, budget), 
            future[index] - present[index] + memoization(present, future, index + 1, budget - present[index]));
    }

    public int maximumProfit(int[] present, int[] future, int budget) {
        int n = present.length;
        dp = new int[n][budget + 1];
        for (int[] row : dp)
            Arrays.fill(row, Integer.MIN_VALUE);
        return memoization(present, future, 0, budget);
    }
}



/*
 * DP with Tabulation
 */
class Solution2 {
    public int maximumProfit(int[] present, int[] future, int budget) {
        int n = present.length;
        int[][] dp = new int[n + 1][budget + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= budget; j++) {
                if (present[i] >= future[i] || present[i] > j)
                    dp[i][j] = dp[i + 1][j];
                else 
                    dp[i][j] = Math.max(dp[i + 1][j], future[i] - present[i] + dp[i + 1][j - present[i]]);
            }
        }
        return dp[0][budget];
    }
}



/*
 * Space Optimized DP with Tabulation
 */
class Solution3 {
    public int maximumProfit(int[] present, int[] future, int budget) {
        int n = present.length;
        int[] ahead = new int[budget + 1], current = new int[budget + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= budget; j++) {
                if (present[i] >= future[i] || present[i] > j)
                    current[j] = ahead[j];
                else
                    current[j] = Math.max(ahead[j], future[i] - present[i] + ahead[j - present[i]]);
            }
            for (int j = 0; j <= budget; j++)
                ahead[j] = current[j];
        }
        return current[budget];
    }
}
