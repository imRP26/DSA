/*
 * https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/
 */



/*
 * Approach 2 (Prefix Sums) from 
 * https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/solutions/278723/analysis-maximum-sum-of-two-non-overlapping-subarrays/
 */
class Solution1 {

    private int helper(int[] nums, int len1, int len2) {
        int result = 0, n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        for (int i = 0; i < n; i++) {
            for (int j = i + len1; j + len2 - 1 < n; j++) {
                int sum1 = prefixSum[i + len1] - prefixSum[i], sum2 = prefixSum[j + len2] - prefixSum[j];
                result = Math.max(result, sum1 + sum2);
            }
        }
        return result;
    }

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(helper(nums, firstLen, secondLen), helper(nums, secondLen, firstLen));
    }
}  



/*
 * Approach 3 from 
 * https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/solutions/278723/analysis-maximum-sum-of-two-non-overlapping-subarrays/
 */
class Solution2 {

    private int helper(int[] nums, int len1, int len2) {
        int result = Integer.MIN_VALUE, n = nums.length;
        int[] prefixSum = new int[n], suffixSum = new int[n], maxPrefixSum = new int[n], maxSuffixSum = new int[n];
        prefixSum[0] = nums[0];
        // prefixSum -> contains only the vanilla prefix sums
        for (int i = 1; i < n; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        maxPrefixSum[len1 - 1] = prefixSum[len1 - 1];
        for (int i = len1; i < n; i++)
            maxPrefixSum[i] = prefixSum[i] - prefixSum[i - len1];
        // maxPrefixSum -> contains the max prefix sum till index i
        for (int i = 1; i < n; i++)
            maxPrefixSum[i] = Math.max(maxPrefixSum[i - 1], maxPrefixSum[i]);
        suffixSum[n - 1] = nums[n - 1];
        // suffixSum -> contains only the vanilla suffix sums
        for (int i = n - 2; i >= 0; i--)
            suffixSum[i] = suffixSum[i + 1] + nums[i];
        maxSuffixSum[n - len2] = suffixSum[n - len2];
        for (int i = n - len2 - 1; i >= 0; i--)
            maxSuffixSum[i] = suffixSum[i] - suffixSum[i + len2];
        // prefixSum -> contains the max suffix sum from index i
        for (int i = n - 2; i >= 0; i--)
            maxSuffixSum[i] = Math.max(maxSuffixSum[i], maxSuffixSum[i + 1]);
        for (int i = len1 - 1; i <= n - len2 - 1; i++)
            result = Math.max(result, maxPrefixSum[i] + maxSuffixSum[i + 1]);
        return result;
    }

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(helper(nums, firstLen, secondLen), helper(nums, secondLen, firstLen));
    }
}
