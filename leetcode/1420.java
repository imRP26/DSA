/*
 * https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/
 */



/*
 * Approach of Top-Down and Bottom-Up DPs from LC Official Editorial!
 */
class Solution {

    /*
     * DP State :-
     * dp(i, maxSoFar, remain) = number of ways to build a valid array if we
     * have already placed 'i' elements, the maximum element placed so far is 
     * 'maxSoFar' and we need to place 'remain' more new maximums.
     * 
     * DP Transition :-
     * dp(i, maxSoFar, remain) = maxSoFar * dp(i + 1, maxSoFar, remain) + sigma{ dp(i + 1, num, remain - 1) }, where maxSoFar + 1 <= num <= m
     * 
     * Base Cases :-
     * dp(n, maxSoFar, remain) = 1 if i == n && remain == 0, else 0.
     * dp(i, maxSoFar, remain) = 0, if remain < 0
     * 
     * Final Answer :-
     * dp(0, 0, k)
     */

    private int[][][] dp;
    private int mod = (int)1e9 + 7;

    private int memoization(int i, int maxSoFar, int remain, int n, int m) {
        if (i == n)
            return remain == 0 ? 1 : 0;
        if (remain < 0)
            return 0;
        if (dp[i][maxSoFar][remain] != -1)
            return dp[i][maxSoFar][remain];
        int ans = 0;
        for (int j = 1; j <= maxSoFar; j++)
            ans = (ans + memoization(i + 1, maxSoFar, remain, n, m)) % mod;
        for (int j = maxSoFar + 1; j <= m; j++)
            ans = (ans + memoization(i + 1, j, remain - 1, n, m)) % mod;
        return dp[i][maxSoFar][remain] = ans;
    }

    public int numOfArrays(int n, int m, int k) {
        /*
        dp = new int[n][m + 1][k + 1];
        for (int[][] outer : dp) {
            for (int[] inner : outer)
                Arrays.fill(inner, -1);
        }
        return memoization(0, 0, k, n, m);
        */
        int[][][] dp = new int[n + 1][m + 1][k + 1];
        for (int i = n; i >= 0; i--) {
            for (int maxSoFar = m; maxSoFar >= 0; maxSoFar--) {
                for (int remain = 0; remain <= k; remain++) {
                    int ans = 0;
                    if (i == n)
                        ans = remain == 0 ? 1 : 0;
                    else {
                        for (int j = 1; j <= maxSoFar; j++)
                            ans = (ans + dp[i + 1][maxSoFar][remain]) % mod;
                        for (int j = maxSoFar + 1; j <= m; j++) {
                            if (remain > 0)
                                ans = (ans + dp[i + 1][j][remain - 1]) % mod;
                        }
                    }
                    dp[i][maxSoFar][remain] = ans;
                }
            }
        }
        return dp[0][0][k];
    }
}



/*
 * Approach of Space-Optimized DP from LC Official Editorial!
 */
class Solution {    
    public int numOfArrays(int n, int m, int k) {    
        int[][] prevDP = new int[m + 1][k + 1], currDP = new int[m + 1][k + 1];
        int mod = (int)1e9 + 7;
        for (int j = 0; j <= m; j++)
            prevDP[j][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            currDP = new int[m + 1][k + 1];
            for (int maxSoFar = m; maxSoFar >= 0; maxSoFar--) {
                for (int remain = 0; remain <= k; remain++) {
                    int ans = 0;
                    for (int j = 1; j <= maxSoFar; j++)
                        ans = (ans + prevDP[maxSoFar][remain]) % mod;
                    if (remain > 0) {
                        for (int j = maxSoFar + 1; j <= m; j++)
                            ans = (ans + prevDP[j][remain - 1]) % mod;
                    }
                    currDP[maxSoFar][remain] = ans;
                }
            }
            prevDP = currDP;
        }
        return currDP[0][k];
    }
}
