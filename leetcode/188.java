/*
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 */



/*
 * DP with memoization
 */ 
class Solution1 {

    private int[][][] dp;

    private int memoization(int[] prices, int index, int toBuy, int transNum, int k) {
        if (index == prices.length || transNum == k)
            return 0;
        if (dp[index][toBuy][transNum] != Integer.MIN_VALUE)
            return dp[index][toBuy][transNum];
        int profit = 0;
        if (toBuy == 1)
            profit = Math.max(-prices[index] + memoization(prices, index + 1, 0, transNum, k), 
                              memoization(prices, index + 1, 1, transNum, k));
        else
            profit = Math.max(prices[index] + memoization(prices, index + 1, 1, transNum + 1, k), 
                              memoization(prices, index + 1, 0, transNum, k));
        return dp[index][toBuy][transNum] = profit;
    } 

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        dp = new int[n][2][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k1 = 0; k1 < k; k1++)
                    dp[i][j][k1] = Integer.MIN_VALUE;
            }
        }
        return memoization(prices, 0, 1, 0, k);
    }
}



/*
 * DP with Tabulation
 */
class Solution2 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 2; j++) {
                for (int k1 = 0; k1 < k; k1++) {
                    if (j == 1)
                        dp[i][j][k1] = Math.max(-prices[i] + dp[i + 1][0][k1], dp[i + 1][1][k1]);
                    else
                        dp[i][j][k1] = Math.max(prices[i] + dp[i + 1][1][k1 + 1], dp[i + 1][0][k1]);
                }
            }
        }
        return dp[0][1][0];
    }
}



/*
 * Space-Optimized Tabulation DP
 */
class Solution3 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] ahead = new int[2][k + 1];
        int[][] current = new int[2][k + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 2; j++) {
                for (int k1 = 0; k1 < k; k1++) {
                    if (j == 1)
                        current[j][k1] = Math.max(-prices[i] + ahead[0][k1], ahead[1][k1]);
                    else
                        current[j][k1] = Math.max(prices[i] + ahead[1][k1 + 1], ahead[0][k1]);
                }
            }
            ahead = current;
        }
        return current[1][0];
    }
}
