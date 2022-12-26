/*
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */



/*
 * Refer from Striver's DP playlist, video no. 37
 */ 
// Approach of Memoization DP, TC = O(n * 2), SC = O(n * 2) + O(n) [stack space]
class Solution1 {

    int[][] dp;

    private int memoization(int index, int toBuy, int[] prices) {
        if (index == prices.length)
            return 0;
        if (dp[index][toBuy] != Integer.MIN_VALUE)
            return dp[index][toBuy];
        int profit = 0;
        if (toBuy == 1)
            profit = Math.max(-prices[index] + memoization(index + 1, 0, prices), 
                              memoization(index + 1, 1, prices));
        else
            profit = Math.max(prices[index] + memoization(index + 1, 1, prices), 
                              memoization(index + 1, 0, prices));
        return dp[index][toBuy] = profit;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        dp = new int[n][2];
        for (int[] row : dp)
            Arrays.fill(row, Integer.MIN_VALUE);
        return memoization(0, 1, prices);
    }
}



/*
 * Approach of DP by Tabulation
 */
class Solution2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 2; j++) {
                if (j == 1)
                    dp[i][j] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
                else
                    dp[i][j] = Math.max(prices[i] + dp[i + 1][1], dp[i + 1][0]);
            }
        }
        return dp[0][1];
    }
}



/*
 * Space-Optimized Tabulation DP
 */
class Solution3 {
    public int maxProfit(int[] prices) {
        int[] ahead = new int[2];
        int[] current = new int[2];
        int n = prices.length;
        for (int i = n - 1; i >= 0; i--) {
            current[1] = Math.max(-prices[i] + ahead[0], ahead[1]);
            current[0] = Math.max(prices[i] + ahead[1], ahead[0]);
            ahead = current;
        }
        return current[1];
    }
}
