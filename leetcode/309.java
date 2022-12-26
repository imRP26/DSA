/*
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */



/*
 * DP with Memoization, Striver Rocks!!!
 */
class Solution1 {

    private int[][] dp;

    private int memoization(int[] prices, int index, int toBuy) {
        if (index >= prices.length)
            return 0;
        if (dp[index][toBuy] != Integer.MIN_VALUE)
            return dp[index][toBuy];
        int profit = 0;
        if (toBuy == 1)
            profit = Math.max(-prices[index] + memoization(prices, index + 1, 0), 
                              memoization(prices, index + 1, 1));
        else
            profit = Math.max(prices[index] + memoization(prices, index + 2, 1), 
                              memoization(prices, index + 1, 0));
        return dp[index][toBuy] = profit;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        dp = new int[n][2];
        for (int[] row : dp)
            Arrays.fill(row, Integer.MIN_VALUE);
        return memoization(prices, 0, 1);
    }
}



/*
 * DP with Tabulation
 */
class Solution2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 2; j++) {
                if (j == 1)
                    dp[i][j] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
                else
                    dp[i][j] = Math.max(prices[i] + dp[i + 2][1], dp[i + 1][0]);
            }
        }
        return dp[0][1];
    }
}



/*
 * Space-Optimized DP with Tabulation
 */
class Solution3 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] moreAhead = new int[2], ahead = new int[2], current = new int[2];
        for (int i = n - 1; i >= 0; i--) {
            current[1] = Math.max(-prices[i] + ahead[0], ahead[1]);
            current[0] = Math.max(prices[i] + moreAhead[1], ahead[0]);
            for (int j = 0; j < 2; j++) {
                moreAhead[j] = ahead[j];
                ahead[j] = current[j];
            }
        }
        return current[1];
    }
}
