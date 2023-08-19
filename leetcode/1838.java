/*
 * https://leetcode.com/problems/frequency-of-the-most-frequent-element/
 */



/*
 * Approach of Sliding Window (heck man, how easy all of it was actually!) from NeetCode -> 
 * https://www.youtube.com/watch?v=vgBrQ0NM5vE
 */
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, low = 0, high = 0, res = 0;
        long currSum = 0;
        while (high < n) {
            currSum += nums[high];
            while (nums[high] * (high - low + 1) > currSum + k)
                currSum -= nums[low++];
            res = Math.max(res, high - low + 1);
            high++;
        }
        return res;
    }
}
