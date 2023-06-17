/*
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */



/*
 * Refer the approach from Striver's explanation!
 */
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length, low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target)
                return mid;
            // when the left half is sorted
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target <= nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            // when the right half is sorted
            else if (nums[mid] <= nums[high]) {
                if (nums[mid] <= target && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -1;
    }
}
