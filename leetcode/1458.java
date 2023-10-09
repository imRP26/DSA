/*
 * https://leetcode.com/problems/max-dot-product-of-two-subsequences/
 */



/*
 * Approach of Top-Down, Bottom-Up and Space-Optimized DP from LC Official Editorial!
 */
class Solution {

    /*
     * DP State :-
     * dp(i, j) = maximum dot product possible when considering the suffix of nums1 starting at index i and the suffix of nums2 starting at index j.
     * 
     * DP Transition :-
     * dp(i, j) = max{ dp(i + 1, j), dp(i, j + 1), nums1[i] * nums2[j] + dp(i + 1, j + 1) }
     * 
     * Base Case :-
     * dp(i, j) = 0, when i = nums1.length or j = nums2.length
     * 
     * Final Answer :-
     * dp(0, 0)
     */

    private int[][] dp;

    private int memoize(int i, int[] nums1, int j, int[] nums2) {
        if (i == nums1.length || j == nums2.length)
            return 0;
        if (dp[i][j] != 0)
            return dp[i][j];
        int leave = Math.max(memoize(i + 1, nums1, j, nums2), memoize(i, nums1, j + 1, nums2));
        int take = nums1[i] * nums2[j] + memoize(i + 1, nums1, j + 1, nums2);
        return dp[i][j] = Math.max(leave, take);
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length, min1 = Integer.MAX_VALUE, max1 = Integer.MIN_VALUE, min2 = min1, max2 = max1;
        for (int num : nums1) {
            min1 = Math.min(min1, num);
            max1 = Math.max(max1, num);
        }
        for (int num : nums2) {
            min2 = Math.min(min2, num);
            max2 = Math.max(max2, num);
        }
        if (max1 < 0 && max2 > 0)
            return max1 * min2;
        if (max1 > 0 && max2 < 0)
            return min1 * max2;
        /*
        dp = new int[n1][n2];
        return memoize(0, nums1, 0, nums2);
        */
        /*
        dp = new int[n1 + 1][n2 + 1];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--)
                dp[i][j] = Math.max(Math.max(dp[i + 1][j], dp[i][j + 1]), nums1[i] * nums2[j] + dp[i + 1][j + 1]);
        }
        */
        int[] prevDP = new int[n2 + 1], currDP = new int[n2 + 1];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--)
                currDP[j] = Math.max(Math.max(prevDP[j], currDP[j + 1]), nums1[i] * nums2[j] + prevDP[j + 1]);
            for (int j = n2 - 1; j >= 0; j--)
                prevDP[j] = currDP[j];
        }
        return currDP[0];
    }
}
