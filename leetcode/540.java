/*
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 */



/*
 * Binary Search, Simple!! - Need to think about edge cases properly though...
 */
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length, low = 0, high = n - 1, lowerIndex, higherIndex;
        if (n == 1)
            return nums[0];
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid >= 1 && nums[mid] != nums[mid - 1] && mid + 1 < n && nums[mid] != nums[mid + 1])
                return nums[mid];
            lowerIndex = mid;
            higherIndex = mid;
            if (mid + 1 < n && nums[mid] == nums[mid + 1])
                higherIndex = mid + 1;
            if (mid >= 1 && nums[mid] == nums[mid - 1]) {
                lowerIndex = mid - 1;
                higherIndex = mid;
            }
            if (higherIndex % 2 == 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return nums[low];
    }
}
