/*
 * https://leetcode.com/problems/integer-break/
 */



/*
 * Approach of Top-Down DP (Memoization) from LC Official Editorial!
 */
class Solution {

    /*
     * DP State :-
     * dp(n) = max product of constituent ints
     * 
     * DP Transition :-
     * for i = 1 to n:
     *     dp(n) = max{dp(n), i * dp(n - i)}
     * 
     * Base Cases :-
     * dp(1) = dp(2) = 1
     * dp(3) = 2
     * 
     * Final Answer :-
     * max{dp(n)}
     */

    private int[] dp;

    private int memoize(int n) {
        if (n <= 4) // but this isn't a base case - its a constituent int and it needn't be split further!
            return dp[n] = n;
        if (dp[n] != 0)
            return dp[n];
        int ans = n;
        for (int i = 1; i < n; i++)
            ans = Math.max(ans, i * memoize(n - i));
        return dp[n] = ans;
    }

    public int integerBreak(int n) {
        if (n <= 3) // base cases
            return n - 1;
        dp = new int[n + 1];
        return memoize(n);
    }
}



/*
 * Approach of Bottom-Up DP!
 */
class Solution {
    public int integerBreak(int n) {
        if (n <= 3)
            return n - 1;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= 4; i++)
            dp[i] = i;
        for (int i = 5; i <= n; i++) {
            for (int j = 1; j < n; j++) {
                if (i > j)
                    dp[i] = Math.max(dp[i], j * dp[i - j]);
            }
        }
        return dp[n];
    }
}



/*
 * Concept of AM-GM Inequality from LC Official Editorial!
 */
class Solution {
    public int integerBreak(int n) {
        if (n <= 3)
            return n - 1;
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        return res * n;
    }
}



/*
 * Just an extension of the above method!
 */
class Solution {
    public int integerBreak(int n) {
        if (n <= 3)
            return n - 1;
        int expo = n / 3, res = (int)Math.pow(3, expo);
        if (n % 3 == 0)
            return res;
        if (n % 3 == 1)
            return res / 3 * 4;
        return res * 2;
    }
}
