/*
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */



/*
 * DP with memoization, refer to Striver's DP playlist, video no. 37
 */
class Solution1 {

    private long[][][] dp;

    private long memoization(int[] prices, int index, int toBuy, int transNum) {
        if (index == prices.length || transNum == 2)
            return 0;
        if (dp[index][toBuy][transNum] != Integer.MIN_VALUE)
            return dp[index][toBuy][transNum];
        long profit = 0;
        if (toBuy == 1)
            profit = Math.max(-prices[index] + memoization(prices, index + 1, 0, transNum), 
                              memoization(prices, index + 1, 1, transNum));
        else
            profit = Math.max(prices[index] + memoization(prices, index + 1, 1, transNum + 1), 
                              memoization(prices, index + 1, 0, transNum));
        return dp[index][toBuy][transNum] = profit;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        dp = new long[n][2][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++)
                    dp[i][j][k] = Integer.MIN_VALUE;
            }
        }
        return (int)memoization(prices, 0, 1, 0);
    }
}



/*
 * DP with Tabulation
 */
class Solution2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    if (j == 1)
                        dp[i][j][k] = Math.max(-prices[i] + dp[i + 1][0][k], dp[i + 1][1][k]);
                    else
                        dp[i][j][k] = Math.max(prices[i] + dp[i + 1][1][k + 1], dp[i + 1][0][k]);
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
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] ahead = new int[2][3];
        int[][] current = new int[2][3];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    if (j == 1)
                        current[j][k] = Math.max(-prices[i] + ahead[0][k], ahead[1][k]);
                    else
                        current[j][k] = Math.max(prices[i] + ahead[1][k + 1], ahead[0][k]);
                }
            }
            ahead = current;
        }
        return current[1][0];
    }
}
