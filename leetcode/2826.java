/*
 * https://leetcode.com/problems/sorting-three-groups/
 */



/*
 * Try to think about problems in alternate ways! - Simple LIS!
 * Number of indices that would need to be swapped will be those that don't participate 
 * to being in the LIS!
 */
class Solution {
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size(), res = 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums.get(j) <= nums.get(i))
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            res = Math.max(res, dp[i]);
        }
        return n - res;
    }
}
