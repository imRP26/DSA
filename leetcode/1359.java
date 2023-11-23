/*
 * https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/
 */



/*
 * Approach from -> 
 * https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/editorial/comments/1296791
 * Final Answer = Total no. of orderings - No. of orderings in which P1 comes before D1 - No. of orderings 
 *                in which P2 comes before D2 - ...
 *              = {(2 * n)!} / (2^n)
 * Now, how to understand this? Well, total number of orderings = (2 * n)!. Number of orderings in which P1 
 * comes before D1 => Consider (P1, D1) as an entity, now we can only select 1 ordering from the possible 2! 
 * orderings, i.e., we divide the numator by 2!, since we required only 1 ordering, i.e., when P1 comes before 
 * D1. Similarly, if P1 must come before P2 and P2 must come before P3, so in that case, out of the possible 
 * 3! orderings, we require only 1 ordering, (P1 - P2 - P3), hence, we divide the numerator by 3! 
 */
class Solution {

    private final long mod = (long)1e9 + 7;

    private long fastExpo(long base, long expo) {
        long ans = 1;
        while (expo > 0) {
            if ((expo & 1) != 0)
                ans = (ans * base) % mod;
            base = (base * base) % mod;
            expo >>= 1;
        }
        return ans;
    }

    private long computeFactorial(int n) {
        long ans = 1;
        for (int i = 2; i <= n; i++)
            ans = (ans * i) % mod;
        return ans;
    }

    public int countOrders(int n) {
        long numer = computeFactorial(2 * n), deno1 = fastExpo(2, n), deno2 = fastExpo(deno1, mod - 2);
        long res = (numer * deno2) % mod;
        return (int)res;
    }
}



/*
 * Approach of DP with Memoization and DP with Tabulation from LC Offical Editorial!
 */
class Solution {

    private long[][] dp;
    private final long mod = (long)1e9 + 7;

    /*
     * DP State :-
     * dp(i, j) = Number of ways of picking up 'i' unpicked items and delivering 'j' undelivered items.
     * 
     * DP Transitions :-
     * dp(i, j) = i * dp(i - 1, j) + (j - i) * dp(i, j - 1)
     * 
     * DP Base Case :-
     * dp(0, 0) = 1 => when all the items have been picked up and delivered!
     * dp(i, j) = 0, if i < 0 OR j < 0 OR i > j.
     * 
     * Final Answer :-
     * dp(n, n)
     */

    private long memoization(int unpicked, int undelivered) {
        if (unpicked == 0 && undelivered == 0)
            return 1;
        if (unpicked > undelivered || unpicked < 0 || undelivered < 0)
            return 0;
        if (dp[unpicked][undelivered] != -1)
            return dp[unpicked][undelivered];
        long ans = unpicked * memoization(unpicked - 1, undelivered);
        ans %= mod;
        ans += (undelivered - unpicked) * memoization(unpicked, undelivered - 1);
        ans %= mod;
        return dp[unpicked][undelivered] = ans;
    }

    public int countOrders(int n) {
        dp = new long[n + 1][n + 1];
        //for (long[] row : dp)
        //    Arrays.fill(row, -1);
        //return (int)memoization(n, n);
        for (int unpicked = 0; unpicked <= n; unpicked++) {
            for (int undelivered = 0; undelivered <= n; undelivered++) {
                if (unpicked == 0 && undelivered == 0)
                    dp[unpicked][undelivered] = 1;
                else if (unpicked > undelivered)
                    dp[unpicked][undelivered] = 0;
                else {
                    if (unpicked >= 1)
                        dp[unpicked][undelivered] = (unpicked * dp[unpicked - 1][undelivered]) % mod;
                    if (undelivered >= 1)
                        dp[unpicked][undelivered] = (dp[unpicked][undelivered] + (undelivered - unpicked) * dp[unpicked][undelivered - 1]) % mod;
                }
            }
        }
        return (int)dp[n][n];
    }
}



/*
 * 
 */