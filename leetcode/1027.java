/*
 * https://leetcode.com/problems/longest-arithmetic-subsequence/
 */



/*
 * Approach of DP from LC official Editorial!
 */
class Solution {
    public int longestArithSeqLength(int[] nums) {
        /*
         * dp[right][diff] = 1 + dp[left][diff], 
         * where (left < right) and diff = nums[right] - nums[left].
         */
        int maxLength = 0;
        int[][] dp = new int[1001][1001];
        for (int[] row : dp)
            Arrays.fill(row, 1);
        for (int rightIndex = 0; rightIndex < nums.length; rightIndex++) {
            for (int leftIndex = 0; leftIndex < rightIndex; leftIndex++) {
                int diff = nums[rightIndex] - nums[leftIndex] + 500;
                dp[rightIndex][diff] = 1 + dp[leftIndex][diff];
                maxLength = Math.max(maxLength, dp[rightIndex][diff]);
            }
        }
        return maxLength;
    }
}



/*
 * Ditto approach as above, but alternate syntax!
 */
class Solution {
    public int longestArithSeqLength(int[] nums) {
        int maxLength = 0, n = nums.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        for (int j = 0; j < n; j++) {
            dp[j] = new HashMap<>();
            for (int i = 0; i < j; i++) {
                int diff = nums[j] - nums[i];
                dp[j].put(diff, dp[i].getOrDefault(diff, 1) + 1);
                maxLength = Math.max(maxLength, dp[j].get(diff));
            }
        }
        return maxLength;
    }
}
