/*
 * https://leetcode.com/problems/check-if-it-is-possible-to-split-array/
 */



/*
 * My Recursive DP Approach from -> 
 * https://leetcode.com/problems/check-if-it-is-possible-to-split-array/solutions/3870654/an-intuitive-solution-using-recursive-dp/
 */
class Solution {

    private int[] prefixSum;

    private boolean memoize(int low, int high, int m, Boolean[][] dp) {
        if (high - low < 2)
           return dp[low][high] = true;
        if (dp[low][high] != null)
            return dp[low][high];
        for (int i = low + 1; i <= high; i++) {
            int size1 = i - low, size2 = high - i + 1, sum1 = (low == 0) ? prefixSum[i - 1] : prefixSum[i - 1] - prefixSum[low - 1], sum2 = prefixSum[high] - prefixSum[i - 1];
            if ((size1 == 1 || sum1 >= m) && (size2 == 1 || sum2 >= m)) {
                if (memoize(low, i - 1, m, dp) && memoize(i, high, m, dp))
                    return dp[low][high] = true;
            }
        }   
        return dp[low][high] = false;
    }

    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        prefixSum = new int[n + 1];
        prefixSum[0] = nums.get(0);
        for (int i = 1; i < n; i++)
            prefixSum[i] = prefixSum[i - 1] + nums.get(i);
        return memoize(0, n - 1, m, new Boolean[n][n]);
    }
}



/*
 * Greedy AF Approach from ->
 * https://leetcode.com/problems/check-if-it-is-possible-to-split-array/solutions/3869991/explained-o-n-check-consecutive-sum-only/
 */
class Solution {
    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        if (n <= 2)
            return true;
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) + nums.get(i + 1) >= m)
                return true;
        }
        return false;
    }
}
