/*
 * https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/
 */



/*
 * Approach of DP by Memoization!
 * Inspiration from -> 
 * https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/solutions/3801934/easy-dp-memoization-take-not-take-with-detailed-approach/
 */
class Solution {

    private long mod = (long)1e9 + 7;
    private long[][] dp;
    
    private long memoization(int i, int x, int n) {
        if (n == 0)
            return 1;
        int num = (int)Math.pow(i, x);
        if (n < 0 || n < num)
            return 0;
        if (dp[i][n] != -1)
            return dp[i][n];
        long ans = (memoization(i + 1, x, n) + memoization(i + 1, x, n - num)) % mod;
        return dp[i][n] = ans;
    }

    public int numberOfWays(int n, int x) {
        dp = new long[n + 1][n + 1];
        for (long[] row : dp)
            Arrays.fill(row, -1);
        return (int)memoization(1, x, n);
    }
}



/*
 * Approach of 1D DP by Tabulation from -> 
 * https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/solutions/3801737/java-python-3-bottom-up-dp-similar-to-coins-change/
 */
class Solution {
    public int numberOfWays(int n, int x) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            long val = 1;
            for (int j = 0; j < x; j++)
                val *= i;
            for (int j = n; j >= val; j--) {
                dp[j] += dp[j - (int)val];
                dp[j] %= 1_000_000_007;
            }
        }
        return dp[n];
    }
}
