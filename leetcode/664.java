/*
 * https://leetcode.com/problems/strange-printer/
 */



/*
 * Approach of Top-Down DP from LC Official Editorial! - Solve this question again please!!
 */
class Solution {

    private int[][] dp;

    private int memoization(String s, int low, int high) {
        if (dp[low][high] != -1)
            return dp[low][high];
        dp[low][high] = s.length();
        int j = -1;
        for (int i = low; i < high; i++) {
            if (s.charAt(i) != s.charAt(high) && j == -1)
                j = i;
            if (j != -1)
                dp[low][high] = Math.min(dp[low][high], 1 + memoization(s, j, i) + memoization(s, i + 1, high));
        }
        if (j == -1)
            dp[low][high] = 0;
        return dp[low][high];
    }

    public int strangePrinter(String s) {
        int n = s.length();
        dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return 1 + memoization(s, 0, n - 1);
    }
}



/*
 * Approach of Bottom-Up DP from LC Official Editorial! - Tough AF...
 */
class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int low = 0; low <= n - len; low++) {
                int high = low + len - 1, j = -1;
                dp[low][high] = n;
                for (int i = low; i < high; i++) {
                    if (s.charAt(i) != s.charAt(high) && j == -1)
                        j = i;
                    if (j != -1)
                        dp[low][high] = Math.min(dp[low][high], 1 + dp[j][i] + dp[i + 1][high]);
                }
                if (j == -1)
                    dp[low][high] = 0;
            }
        }
        return 1 + dp[0][n - 1];
    }
}
