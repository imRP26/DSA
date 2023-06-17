/*
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */



/*
 * Refer to Striver's explanation!
 */
class Solution {
    public int findMin(int[] nums) {
        int n = nums.length, low = 0, high = n - 1, res = 5001;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            /*
             * Determine the left / right portion that's sorted.
             * Take the minimum value of the sorted portion and then eliminate this portion.
             * Recur on the remaining portion.
             */
            if (nums[low] <= nums[mid]) {
                res = Math.min(res, nums[low]);
                low = mid + 1;
            }
            else if (nums[mid] <= nums[high]) {
                res = Math.min(res, nums[mid]);
                high = mid - 1;
            }
        }
        return res;
    }
}
