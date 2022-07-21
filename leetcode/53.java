/*
 * Question Link -> https://leetcode.com/problems/maximum-subarray/
 */



/*
 * For clear-cut explanation, refer to Striver's video on the same.
 * Kadane's Algo - TC = O(N), SC = O(1)
 */
class Solution1 {
    public int maxSubArray(int[] nums) {
        int currSum = 0, maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            if (currSum > maxSum)
                maxSum = currSum;
            if (currSum < 0)
                currSum = 0;
        }
        return maxSum;
    }
}



/*
 * Divide-and-Conquer based algo - TC = O(N log N), SC = O(N log N) 
 * A detailed solution can be found here -> 
 * https://leetcode.com/problems/maximum-subarray/solution/
 */
class Solution2 {

    public int findBestSubarray(int[] nums, int left, int right) {
        if (left > right)
            return Integer.MIN_VALUE;
        int mid = Math.floorDiv(left + right, 2);
        int currSum = 0, bestLeftSum = 0, bestRightSum = 0;
        for (int i = mid - 1; i >= left; i--) {
            currSum += nums[i];
            bestLeftSum = Math.max(bestLeftSum, currSum);
        }
        currSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            currSum += nums[i];
            bestRightSum = Math.max(bestRightSum, currSum);
        }
        int bestCombinedSum = nums[mid] + bestLeftSum + bestRightSum;
        int leftHalf = findBestSubarray(nums, left, mid - 1);
        int rightHalf = findBestSubarray(nums, mid + 1, right);
        return Math.max(bestCombinedSum, Math.max(leftHalf, rightHalf));
    }

    public int maxSubArray(int[] nums) {
        return findBestSubarray(nums, 0, nums.length - 1);
    }
}
