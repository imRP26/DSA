import java.util.*;

/*
 * Question Link -> https://binarysearch.com/problems/Count-Exact-Sum
 */



/*
 * In the beginning, we're only able to reach a sum of 0. Every time we add a 
 * new number, we might reach new sums based on sums we have reached earlier. 
 * So if we add a number x and we were able to reach a sum of s before, we 
 * are now able to reach s + x. Thereby we are only interested in 0 ≤ s+x ≤ k.
 * We're not only interested in which sums are reachable, but also in the number 
 * of ways to reach them. Therefore we need to add reachability counts instead 
 * of just saving reachability booleans. 
 * The counts that are reachable by adding a new number are only dependant on 
 * the counts that were available after adding the previous number. Therefore, 
 * we have an optimal substructure and can use DP to solve this problem. 
 */
class Solution {

    long mod = 1000000007;

    public long memoize(int[] nums, int k, int i, long[][] dp) {
        // when sum has become k, then it means that we've reached at a solution
        if (k == 0)
            return 1;
        // if there are no further elements to pick from, then no sum can be constructed 
        if (i == 0)
            return 0;
        // if its already been computed, then just return it
        if (dp[i][k] != -1)
            return dp[i][k];
        if (k < nums[i - 1])
            return dp[i][k] = memoize(nums, k, i - 1, dp) % mod;
        return dp[i][k] = (memoize(nums, k, i - 1, dp) + memoize(nums, k - nums[i - 1], i - 1, dp)) % mod;
    }

    public int solve(int[] nums, int k) {
        int n = nums.length;
        long[][] dp = new long[n + 1][k + 1];
        for (long[] arr : dp)
            Arrays.fill(arr, -1);
        for (int i = 0; i <= n; i++) // no sum => don't pick any element
            dp[i][0] = 1;
        for (int i = 0; i <= k; i++) // no element => no sum is possible
            dp[0][i] = 0;
        memoize(nums, k, n, dp);
        return (int)dp[n][k];
    }
}
