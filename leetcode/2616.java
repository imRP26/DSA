/*
 * https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/
 */



/*
 * Approach of Binary Search + Greedy from LC Official Editorial 
 */
class Solution {

    private int n;

    private int countValidPairs(int[] nums, int thresh) {
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] <= thresh) {
                count++;
                i++;
            }
        }
        return count;
    }

    public int minimizeMax(int[] nums, int p) {
        n = nums.length;
        Arrays.sort(nums);
        int low = 0, high = nums[n - 1] - nums[0], res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countValidPairs(nums, mid) >= p) {
                res = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return res;
    }
}
