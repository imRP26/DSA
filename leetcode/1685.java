/*
 * https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/
 */



/*
 * Approach of computation of prefix and suffix sums from LC Official Editorial!
 */
class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n], suffixSum = new int[n], result = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        suffixSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            suffixSum[i] = suffixSum[i + 1] + nums[i];
        for (int i = 0; i < n; i++) {
            if (i == 0)
                result[i] = suffixSum[i + 1] - (n - i - 1) * nums[i];
            else if (i == n - 1)
                result[i] = i * nums[i] - prefixSum[i - 1];
            else
                result[i] = suffixSum[i + 1] - (n - i - 1) * nums[i] + i * nums[i] - prefixSum[i - 1];
        }
        return result;
    }
}



/*
 * Approach of computation of prefix and suffix sums on the fly from LC Official Editorial!
 */
class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length, totalSum = 0, prefixSum = 0;
        int[] result = new int[n];
        for (int num : nums)
            totalSum += num;
        for (int i = 0; i < n; i++) {
            int suffixSum = totalSum - prefixSum - nums[i];
            result[i] = i * nums[i] - prefixSum + suffixSum - (n - i - 1) * nums[i];
            prefixSum += nums[i];
        }
        return result;
    }
}
