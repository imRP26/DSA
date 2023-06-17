/*
 * https://leetcode.com/problems/search-insert-position/
 */



/*
 * Refer to Striver's video on Binary Search (Lower / Upper Bounds)
 */
class Solution {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length, low = 0, high = n - 1, res = n;
        while (low <= high) {
            int mid = low + (high - low) / 2, val = nums[mid];
            if (val == target)
                return mid;
            if (val > target) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res;
    }
}
