/*
 * https://leetcode.com/problems/handshakes-that-dont-cross/
 */



/*
 * Simple DP Approach from -> 
 * https://leetcode.com/problems/handshakes-that-dont-cross/solutions/3058204/handshakes-that-don-t-cross/?orderBy=most_votes
 */
class Solution {

    private long memoization(int i, long[] dp) {
        if (dp[i] != -1)
            return dp[i];
        dp[i] = 0;
        long mod = (long)1e9 + 7;
        for (int j = 0; j <= i - 1; j++) {
            long val = ((memoization(j, dp) % mod) * (memoization(i - j - 1, dp) % mod)) % mod;
            dp[i] += val;
            dp[i] %= mod;
        }
        return dp[i];
    }

    public int numberOfWays(int numPeople) {
        long[] dp = new long[numPeople / 2 + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return (int)memoization(numPeople / 2, dp);
    }
}
