/*
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */



/*
 * DP with Memoization
 */
class Solution1 {

    private int[][] dp; 

    private int memoization(int[] prices, int index, int toBuy, int fee) {
        if (index == prices.length)
            return 0;
        if (dp[index][toBuy] != Integer.MIN_VALUE)
            return dp[index][toBuy];
        if (toBuy == 1)
            return dp[index][toBuy] = Math.max(-prices[index] + memoization(prices, index + 1, 0, fee), memoization(prices, index + 1, 1, fee));
        return dp[index][toBuy] = Math.max(prices[index] - fee + memoization(prices, index + 1, 1, fee), memoization(prices, index + 1, 0, fee));
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        dp = new int[n][2];
        for (int[] row : dp)
            Arrays.fill(row, Integer.MIN_VALUE);
        return memoization(prices, 0, 1, fee);
    }
} 



/*
 * DP with Tabulation
 */
class Solution2 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][1] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
            dp[i][0] = Math.max(prices[i] - fee + dp[i + 1][1], dp[i + 1][0]);
        }
        return dp[0][1];
    }
}



/*
 * Space-Optimized DP with Tabulation
 */
class Solution3 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] ahead = new int[2], current = new int[2];
        for (int i = n - 1; i >= 0; i--) {
            current[1] = Math.max(-prices[i] + ahead[0], ahead[1]);
            current[0] = Math.max(prices[i] - fee + ahead[1], ahead[0]);
            ahead[0] = current[0];
            ahead[1] = current[1];
        }
        return current[1];
    }
}
