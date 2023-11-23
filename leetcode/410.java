/*
 * https://leetcode.com/problems/split-array-largest-sum/
 */



/*
 * My Approach of Binary Search!
 */
class Solution {

    private boolean isSplitPossible(int[] nums, int x, int k) {
        int splits = 1, currSum = 0;
        for (int num : nums) {
            if (num > x)
                return false;
            if (currSum + num > x) {
                splits++;
                currSum = num;
            }
            else
                currSum += num;
        }
        return splits <= k;
    }

    public int splitArray(int[] nums, int k) {
        int low = 0, high = (int)1e9, res = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isSplitPossible(nums, mid, k)) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res;
    }
}
