/*
 * https://leetcode.com/problems/unique-binary-search-trees/
 */



/*
 * Approach of Simple Memoization!
 */
class Solution {

    private int[] dp = new int[20];

    private int memoization(int n) {
        if (dp[n] != -1)
            return dp[n];
        int ans = 0;
        for (int i = 1; i <= n; i++)
            ans += memoization(i - 1) * memoization(n - i);
        return dp[n] = ans;
    }

    public int numTrees(int n) {
        Arrays.fill(dp, -1);
        for (int i = 0; i <= 2; i++)
            dp[i] = i;
        dp[0] = 1;
        return memoization(n);
    }
}



/*
 * Approach 1 of Bottom-Up DP from -> 
 * https://leetcode.com/problems/unique-binary-search-trees/editorial/
 */
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++)
                dp[i] += dp[j - 1] * dp[i - j];
        }
        return dp[n];
    }
}
