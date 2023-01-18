/*
 * https://leetcode.com/problems/maximum-sum-circular-subarray/
 */



/*
 * Concept of Prefix and Suffix sums from 
 * https://leetcode.com/problems/maximum-sum-circular-subarray/solutions/2868539/maximum-sum-circular-subarray/
 */
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
    	/*
    	 * Enumerating prefix sums and for each prefix sum, we add it with 
    	 * maximum suffix sum such that its non-overlapping.
    	 */
        int n = nums.length, suffixSum = nums[n - 1];
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
        	suffixSum += nums[i];
        	rightMax[i] = Math.max(rightMax[i + 1], suffixSum);
        }
        // Generic Kadane's algorithm
        int maxSum = nums[0], wrapAroundSum = nums[0], prefixSum = 0, currMax = 0;
        for (int i = 0; i < n; i++) {
        	currMax = Math.max(currMax, 0) + nums[i];
        	wrapAroundSum = Math.max(wrapAroundSum, currMax);
        	prefixSum += nums[i];
        	if (i + 1 < n)
        		wrapAroundSum = Math.max(wrapAroundSum, prefixSum + rightMax[i + 1]);
        }
        return Math.max(maxSum, wrapAroundSum);
    }
}



/*
 * Kadane's algorithm to compute the minimum sum subarray.
 * Then final result = sum of all array elements - sum of minimum subarray elements.
 * Approach from 
 * https://leetcode.com/problems/maximum-sum-circular-subarray/solutions/2868539/maximum-sum-circular-subarray/
 * Even better explanation of this same approach from 
 * https://leetcode.com/problems/maximum-sum-circular-subarray/solutions/178422/one-pass/
 */
class Solution2 {
	public int maxSubarraySumCircular(int[] nums) {
		int currMax = 0, currMin = 0, sum = 0, maxSum = nums[0], minSum = nums[0];
		for (int num : nums) {
			currMax = Math.max(currMax, 0) + num;
			maxSum = Math.max(maxSum, currMax);
			currMin = Math.min(currMin, 0) + num;
			minSum = Math.min(minSum, currMin);
			sum += num;
		}
		return sum == minSum ? maxSum : Math.max(maxSum, sum - minSum);
	}
}
