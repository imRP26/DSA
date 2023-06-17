/*
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */



/*
 * Refer to Striver's video on Lower and Upper Bounds!!
 */
class Solution {

    private int lowerBound(int[] nums, int x) {
        int n = nums.length, low = 0, high = n - 1, lb = n;
        while (low <= high) {
            int mid = low + (high - low) / 2, val = nums[mid];
            if (val >= x) {
                lb = mid;
                high = mid - 1;
            }
            else // if (val < x)
                low = mid + 1;
        }
        return lb;
    }

    private int upperBound(int[] nums, int x) {
        int n = nums.length, low = 0, high = n - 1, ub = n;
        while (low <= high) {
            int mid = low + (high - low) / 2, val = nums[mid];
            if (val > x) {
                ub = mid;
                high = mid - 1;
            }
            else // if (val <= x)
                low = mid + 1;
        }
        return ub;
    }

    public int[] searchRange(int[] nums, int target) {
        int lb = lowerBound(nums, target), ub = upperBound(nums, target), n = nums.length;
        // when target != nums[i] for every i
        if (lb == ub)
            return new int[] {-1, -1};
        // when target == nums[i] for atleast 1 i
        return new int[] {lb, ub - 1};
    }
}
